package org.openstack4j.openstack.container.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import org.openstack4j.api.container.ContainerService;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.container.*;
import org.openstack4j.openstack.container.domain.ContainerAction;
import org.openstack4j.openstack.container.domain.ZunContainer;
import org.openstack4j.openstack.container.domain.ZunContainerUpdate;
import org.openstack4j.openstack.container.domain.ZunExecResponse;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 实现用于 Zun 容器操作的 {@link org.openstack4j.api.container.ContainerService} 接口。
 *
 * @author xx
 * @date 2025-04-10
 */
public class ContainerServiceImpl extends BaseContainerService implements ContainerService {

    private static final String CONTAINER_PATH = "containers"; // Base path
    private static final String ACTION_PATH = "/action";       // Action subpath
    private static final String CONTAINER_API_VERSION = "container 1.12";

    @Override
    public List<? extends Container> list() {
        return list(null); // Call list with null filters
    }

    @Override
    public List<? extends Container> list(Map<String, String> filteringParams) {
        Invocation<ZunContainer.ZunContainers> req = get(ZunContainer.ZunContainers.class, uri(CONTAINER_PATH));
        if (filteringParams != null && !filteringParams.isEmpty()) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                req = req.param(entry.getKey(), entry.getValue());
            }
        }
        return req.execute().getList();
    }

    @Override
    public Container get(String containerIdOrName) {
        checkNotNull(containerIdOrName);
        return get(ZunContainer.class, uri("%s/%s", CONTAINER_PATH, containerIdOrName)).execute();
    }

    @Override
    public Container create(ContainerCreate container) {
        checkNotNull(container);
        checkNotNull(container.getImage(), "Image must be set in ContainerCreate config");
        return post(ZunContainer.class, uri(CONTAINER_PATH))
                .entity(container)
                .execute();
    }

    @Override
    public ActionResponse delete(String containerIdOrName, boolean force, boolean stop) {
        checkNotNull(containerIdOrName);
        Map<String, String> params = new HashMap<>();
        if (force) {
            params.put("force", "True");
        }
        if (stop) {
            params.put("stop", "True");
        }
        return deleteWithResponse(uri("%s/%s", CONTAINER_PATH, containerIdOrName))
                .params(params)
                .execute();
    }

    private ActionResponse performAction(String containerIdOrName, ContainerAction action) {
        checkNotNull(containerIdOrName);
        checkNotNull(action);
        return post(ActionResponse.class, uri("%s/%s%s", CONTAINER_PATH, containerIdOrName, ACTION_PATH))
                .params(action.getAction()) // Send {"action_name": {params...}}
                .execute();
    }

    private ActionResponse performActionWithQueryParams(String containerIdOrName, String actionName, Map<String, Object> queryParams) {
        checkNotNull(containerIdOrName);
        checkNotNull(actionName);
        // Assumes dedicated endpoint like /stop?timeout=10
        Invocation<ActionResponse> req = post(ActionResponse.class, uri("%s/%s/%s", CONTAINER_PATH, containerIdOrName, actionName));
        if (queryParams != null && !queryParams.isEmpty()) {
            queryParams.forEach((k, v) -> req.param(k, String.valueOf(v)));
        }
        return req.execute();
    }

    @Override
    public ActionResponse start(String containerIdOrName) {
        return performActionWithQueryParams(containerIdOrName, "start", null); // Using /start
    }

    @Override
    public ActionResponse stop(String containerIdOrName, Integer timeout) {
        Map<String, Object> params = (timeout != null) ? ImmutableMap.of("timeout", timeout): null;
        return performActionWithQueryParams(containerIdOrName, "stop", params); // Using /stop
    }

    @Override
    public ActionResponse restart(String containerIdOrName, Integer timeout) {
        Map<String, Object> params = (timeout != null) ? ImmutableMap.of("timeout", timeout): null;
        return performActionWithQueryParams(containerIdOrName, "reboot", params); // Using /restart
    }

    @Override
    public ActionResponse pause(String containerIdOrName) {
        // return performAction(containerIdOrName, ContainerAction.pause()); // Using /action
        return performActionWithQueryParams(containerIdOrName, "pause", null); // Using /pause
    }

    @Override
    public ActionResponse unpause(String containerIdOrName) {
        // return performAction(containerIdOrName, ContainerAction.unpause()); // Using /action
        return performActionWithQueryParams(containerIdOrName, "unpause", null); // Using /unpause
    }

    @Override
    public String logs(String containerIdOrName, LogsParameters params) {
        checkNotNull(containerIdOrName);
        checkNotNull(params);
        Invocation<String> req = get(String.class, uri("%s/%s/logs", CONTAINER_PATH, containerIdOrName));
        params.getQueryParameters().forEach((k, v) -> req.param(k, String.valueOf(v)));
        return req.execute();
    }

    @Override
    public ExecResponse execute(String containerIdOrName, ExecParameters params) {
        checkNotNull(containerIdOrName);
        checkNotNull(params);
        checkNotNull(params.getCommand(), "Command must be provided for execute");
        return post(ZunExecResponse.class, uri("%s/%s/execute", CONTAINER_PATH, containerIdOrName))
                .params(ImmutableMap.of("command", params.getCommand(), "interactive", params.isInteractive(), "run", params.isRun()))
                .execute();
    }

    @Override
    public ActionResponse resize(String containerIdOrName, ExecParameters params) {
        checkNotNull(containerIdOrName);
        checkNotNull(params);
        checkNotNull(params.getWidth(), "width must be provided for resize");
        checkNotNull(params.getHeight(), "width must be provided for resize");
        return post(ActionResponse.class, uri("%s/%s/resize", CONTAINER_PATH, containerIdOrName))
                .params(params.getQueryParameters())
                .execute();
    }

    @Override
    public ActionResponse executeResize(String containerIdOrName, ExecParameters params) {
        checkNotNull(containerIdOrName);
        checkNotNull(params.getExecId(), "exec_id must be provided for resize");
        checkNotNull(params.getWidth(), "width must be provided for resize");
        checkNotNull(params.getHeight(), "width must be provided for resize");

        return post(ActionResponse.class, uri("%s/%s/execute_resize", CONTAINER_PATH, containerIdOrName))
                .params(params.getQueryParameters())
                .execute();
    }

    @Override
    public String attach(String containerIdOrName) {
        checkNotNull(containerIdOrName);
        AttachResponse response = get(AttachResponse.class, uri("%s/%s/attach", CONTAINER_PATH, containerIdOrName)).execute();
        return response != null ? response.getUrl(): null;
    }

    // Helper class for Attach response
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class AttachResponse {
        @JsonProperty("url")
        String url;

        public String getUrl() {
            return url;
        }
    }


    @Override
    public ActionResponse commit(String containerIdOrName, CommitParameters params) {
        checkNotNull(containerIdOrName);
        checkNotNull(params);
        checkNotNull(params.getRepository(), "Repository must be provided for commit");
        Invocation<ActionResponse> req = post(ActionResponse.class, uri("%s/%s/commit", CONTAINER_PATH, containerIdOrName));
        params.getQueryParameters().forEach((k, v) -> req.param(k, String.valueOf(v)));
        return req.execute();
    }

    @Override
    public Container rename(String containerIdOrName, String newName) {
        checkNotNull(containerIdOrName);
        checkNotNull(newName);
        ContainerUpdate update = ZunContainerUpdate.builder().name(newName).build();
        return update(containerIdOrName, update);
    }

    @Override
    public Container update(String containerIdOrName, ContainerUpdate options) {
        checkNotNull(containerIdOrName);
        checkNotNull(options);
        return patch(ZunContainer.class, uri("%s/%s", CONTAINER_PATH, containerIdOrName))
                .entity(options)
                .execute();
    }

    @Override
    public ActionResponse networkAttach(String containerIdOrName, NetworkAttachOptions options) {
        checkNotNull(options);
        if (options.getNetwork() == null && options.getPort() == null) {
            throw new IllegalArgumentException("Either network or port must be specified for networkAttach");
        }
        return performActionWithPayload(containerIdOrName, "network_attach", options);
    }

    @Override
    public ActionResponse networkDetach(String containerIdOrName, NetworkDetachOptions options) {
        checkNotNull(options);
        if (options.getNetwork() == null && options.getPort() == null) {
            throw new IllegalArgumentException("Either network or port must be specified for networkDetach");
        }
        return performActionWithPayload(containerIdOrName, "network_detach", options);
    }

    private ActionResponse performActionWithPayload(String containerIdOrName, String actionName, Object payload) {
        checkNotNull(containerIdOrName);
        checkNotNull(actionName);
        checkNotNull(payload);
        // Assumes dedicated endpoint like /network_attach with payload
        Invocation<ActionResponse> req = post(ActionResponse.class, uri("%s/%s/%s", CONTAINER_PATH, containerIdOrName, actionName));
        if (payload instanceof Map) req.params((Map) payload);
        else if (payload instanceof ModelEntity) req.entity((ModelEntity) payload);
        return req.execute();
    }


    @Override
    public ActionResponse addSecurityGroup(String containerIdOrName, String securityGroupName) {
        checkNotNull(securityGroupName);
        return performActionWithPayload(containerIdOrName, "add_security_group", ImmutableMap.of("security_group", securityGroupName)); // Using dedicated endpoint
    }

    @Override
    public ActionResponse removeSecurityGroup(String containerIdOrName, String securityGroupName) {
        checkNotNull(securityGroupName);
        return performActionWithPayload(containerIdOrName, "remove_security_group", ImmutableMap.of("security_group", securityGroupName)); // Using dedicated endpoint
    }
}
