package org.openstack4j.openstack.container.domain;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import org.openstack4j.model.container.NetworkAttachOptions;
import org.openstack4j.model.container.NetworkDetachOptions;

/**
 * 表示容器操作（如启动、停止等）的有效负载。
 *
 * @author xx
 * @date 2025-04-10
 */
public class ContainerAction {
    // Use nested maps/objects to represent the action payload
    // e.g., for stop: {"stop": {"timeout": 10}}
    // e.g., for start: {"start": null}
    private Map<String, Object> action;

    private ContainerAction(String actionName, Object params) {
        this.action = ImmutableMap.of(actionName, params);
    }

    public static ContainerAction start() {
        return new ContainerAction("start", null);
    }

    public static ContainerAction stop(Integer timeout) {
        return new ContainerAction("stop", timeout == null ? null: ImmutableMap.of("timeout", timeout));
    }

    public static ContainerAction restart(Integer timeout) {
        return new ContainerAction("restart", timeout == null ? null: ImmutableMap.of("timeout", timeout));
    }

    public static ContainerAction pause() {
        return new ContainerAction("pause", null);
    }

    public static ContainerAction unpause() {
        return new ContainerAction("unpause", null);
    }

    public static ContainerAction networkAttach(NetworkAttachOptions options) {
        return new ContainerAction("network_attach", options);
    }

    public static ContainerAction networkDetach(NetworkDetachOptions options) {
        return new ContainerAction("network_detach", options);
    }

    public static ContainerAction addSecurityGroup(String name) {
        return new ContainerAction("add_security_group", ImmutableMap.of("name", name));
    }

    public static ContainerAction removeSecurityGroup(String name) {
        return new ContainerAction("remove_security_group", ImmutableMap.of("name", name));
    }
    // Add other actions as needed

    // Getter might not be needed if only used for serialization
    public Map<String, Object> getAction() {
        return action;
    }
}
