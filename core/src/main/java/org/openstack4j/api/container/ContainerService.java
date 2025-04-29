package org.openstack4j.api.container;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.container.*;

/**
 * Container Service
 *
 * @author xx
 * @date 2025-04-10
 */
public interface ContainerService extends RestService {
    /**
     * List all containers accessible by the current project.
     * 列出当前项目可访问的所有容器。
     * Corresponds to {@code GET /v1/containers}
     *
     * @return A list of container summaries. 返回容器摘要列表。
     */
    List<? extends Container> list();

    /**
     * List containers matching the specified filters.
     * 列出符合指定过滤条件的容器。
     * Corresponds to {@code GET /v1/containers?{filter_key}={filter_value}}
     *
     * @param filteringParams A map of query parameters to filter the list (e.g., "name", "image", "status").
     * 用于过滤列表的查询参数映射 (例如："name", "image", "status")。
     * @return A list of filtered container summaries. 返回过滤后的容器摘要列表。
     */
    List<? extends Container> list(Map<String, String> filteringParams);

    /**
     * Get detailed information about a specific container.
     * 获取特定容器的详细信息。
     * Corresponds to {@code GET /v1/containers/{container_id}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container. 容器的唯一标识符 (UUID) 或名称。
     * @return The detailed Container object or {@code null} if not found. 返回详细的容器对象，如果未找到则返回 {@code null}。
     */
    Container get(String containerIdOrName);

    /**
     * Create a new container based on the provided configuration.
     * 根据提供的配置创建新容器。
     * Corresponds to {@code POST /v1/containers}
     *
     * @param container The configuration object describing the container to create. 描述要创建的容器的配置对象。
     * @return The newly created Container object. 返回新创建的容器对象。
     */
    Container create(ContainerCreate container);

    /**
     * Delete a container.
     * 删除容器。
     * Corresponds to {@code DELETE /v1/containers/{container_id}?force={force}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container to delete. 要删除的容器的唯一标识符 (UUID) 或名称。
     * @param force Whether to force delete the container (optional, defaults to false). 是否强制删除容器 (可选，默认为 false)。
     * @param stop Whether to stop the container before delete. 是否在删除前停止容器
     * @return An ActionResponse indicating success or failure. 返回指示成功或失败的 ActionResponse。
     */
    ActionResponse delete(String containerIdOrName, boolean force, boolean stop);

    /**
     * Start a container.
     * 启动容器。
     * Corresponds to {@code POST /v1/containers/{container_id}/start} or {@code POST /v1/containers/{container_id}/action {"start": null}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container to start. 要启动的容器的唯一标识符 (UUID) 或名称。
     * @return An ActionResponse indicating success or failure. 返回指示成功或失败的 ActionResponse。
     */
    ActionResponse start(String containerIdOrName);

    /**
     * Stop a container.
     * 停止容器。
     * Corresponds to {@code POST /v1/containers/{container_id}/stop?timeout={timeout}} or {@code POST /v1/containers/{container_id}/action {"stop": {"timeout": timeout}}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container to stop. 要停止的容器的唯一标识符 (UUID) 或名称。
     * @param timeout Seconds to wait for the container to stop before killing it (optional, defaults vary). 在杀死容器前等待其停止的秒数 (可选，默认值可能不同)。
     * @return An ActionResponse indicating success or failure. 返回指示成功或失败的 ActionResponse。
     */
    ActionResponse stop(String containerIdOrName, Integer timeout);

    /**
     * Restart a container.
     * 重启容器。
     * Corresponds to {@code POST /v1/containers/{container_id}/restart?timeout={timeout}} or {@code POST /v1/containers/{container_id}/action {"restart": {"timeout": timeout}}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container to restart. 要重启的容器的唯一标识符 (UUID) 或名称。
     * @param timeout Seconds to wait for the container to stop before starting it again (optional, defaults vary). 在再次启动容器前等待其停止的秒数 (可选，默认值可能不同)。
     * @return An ActionResponse indicating success or failure. 返回指示成功或失败的 ActionResponse。
     */
    ActionResponse restart(String containerIdOrName, Integer timeout);

    /**
     * Pause a container.
     * 暂停容器。
     * Corresponds to {@code POST /v1/containers/{container_id}/pause} or {@code POST /v1/containers/{container_id}/action {"pause": null}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container to pause. 要暂停的容器的唯一标识符 (UUID) 或名称。
     * @return An ActionResponse indicating success or failure. 返回指示成功或失败的 ActionResponse。
     */
    ActionResponse pause(String containerIdOrName);

    /**
     * Unpause a container.
     * 取消暂停容器。
     * Corresponds to {@code POST /v1/containers/{container_id}/unpause} or {@code POST /v1/containers/{container_id}/action {"unpause": null}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container to unpause. 要取消暂停的容器的唯一标识符 (UUID) 或名称。
     * @return An ActionResponse indicating success or failure. 返回指示成功或失败的 ActionResponse。
     */
    ActionResponse unpause(String containerIdOrName);

    /**
     * Get logs from a container.
     * 获取容器的日志。
     * Corresponds to {@code GET /v1/containers/{container_id}/logs?{params}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container. 容器的唯一标识符 (UUID) 或名称。
     * @param params Parameters to control log output (stdout, stderr, timestamps, tail, since). 控制日志输出的参数。
     * @return The container logs as a String. 容器日志字符串。
     */
    String logs(String containerIdOrName, LogsParameters params);

    /**
     * Execute a command inside a running container.
     * 在运行中的容器内执行命令。
     * Corresponds to {@code POST /v1/containers/{container_id}/execute?command={command}}
     * Note: This typically returns connection details (URL, token) for an interactive session, not the command output directly.
     * 注意：这通常返回交互式会话的连接详细信息（URL、令牌），而不是直接返回命令输出。
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container. 容器的唯一标识符 (UUID) 或名称。
     * @param params Parameters for the command execution (command, interactive). 命令执行的参数。
     * @return An ExecResponse containing the URL and potentially a token for the interactive session. 包含交互式会话 URL 和可能令牌的 ExecResponse。
     */
    ExecResponse execute(String containerIdOrName, ExecParameters params);

    /**
     * Get a URL to attach to a running container's TTY.
     * 获取附加到运行中容器 TTY 的 URL。
     * Corresponds to {@code GET /v1/containers/{container_id}/attach}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container. 容器的唯一标识符 (UUID) 或名称。
     * @return A String representing the WebSocket URL for attaching. 表示用于附加的 WebSocket URL 的字符串。
     */
    String attach(String containerIdOrName);

    /**
     * Create a new image from a container's changes.
     * 从容器的更改创建新镜像。
     * Corresponds to {@code POST /v1/containers/{container_id}/commit?repository={repo}&tag={tag}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container. 容器的唯一标识符 (UUID) 或名称。
     * @param params Parameters for the commit operation (repository name, tag). 提交操作的参数（仓库名称、标签）。
     * @return An ActionResponse or potentially Image details upon success. 成功时返回 ActionResponse 或可能的镜像详细信息。
     */
    ActionResponse commit(String containerIdOrName, CommitParameters params);
    // Image commit(String containerIdOrName, CommitParameters params); // Alternative if it returns Image details

    /**
     * Rename a container.
     * 重命名容器。
     * Corresponds to {@code PATCH /v1/containers/{container_id}} with JSON Patch payload.
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container to rename. 要重命名的容器的唯一标识符 (UUID) 或名称。
     * @param newName The new name for the container. 容器的新名称。
     * @return The updated Container object. 返回更新后的容器对象。
     */
    Container rename(String containerIdOrName, String newName);

    /**
     * Update attributes of a container (e.g., CPU, memory).
     * 更新容器的属性（例如 CPU、内存）。
     * Corresponds to {@code PATCH /v1/containers/{container_id}} with JSON Patch payload.
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container to update. 要更新的容器的唯一标识符 (UUID) 或名称。
     * @param options The update options containing fields to modify. 包含要修改字段的更新选项。
     * @return The updated Container object. 返回更新后的容器对象。
     */
    Container update(String containerIdOrName, ContainerUpdate options);

    /**
     * Attach a network to a container.
     * 将网络附加到容器。
     * Corresponds to {@code POST /v1/containers/{container_id}/network_attach} or {@code POST /v1/containers/{container_id}/action {"network_attach": ...}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container. 容器的唯一标识符 (UUID) 或名称。
     * @param options Options specifying the network to attach. 指定要附加的网络选项。
     * @return An ActionResponse indicating success or failure. 返回指示成功或失败的 ActionResponse。
     */
    ActionResponse networkAttach(String containerIdOrName, NetworkAttachOptions options);

    /**
     * Detach a network from a container.
     * 从容器分离网络。
     * Corresponds to {@code POST /v1/containers/{container_id}/network_detach} or {@code POST /v1/containers/{container_id}/action {"network_detach": ...}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container. 容器的唯一标识符 (UUID) 或名称。
     * @param options Options specifying the network to detach. 指定要分离的网络选项。
     * @return An ActionResponse indicating success or failure. 返回指示成功或失败的 ActionResponse。
     */
    ActionResponse networkDetach(String containerIdOrName, NetworkDetachOptions options);

    /**
     * Add a security group to a container.
     * 向容器添加安全组。
     * Corresponds to {@code POST /v1/containers/{container_id}/add_security_group} or {@code POST /v1/containers/{container_id}/action {"add_security_group": ...}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container. 容器的唯一标识符 (UUID) 或名称。
     * @param securityGroupName The name or ID of the security group to add. 要添加的安全组的名称或 ID。
     * @return An ActionResponse indicating success or failure. 返回指示成功或失败的 ActionResponse。
     */
    ActionResponse addSecurityGroup(String containerIdOrName, String securityGroupName);

    /**
     * Remove a security group from a container.
     * 从容器移除安全组。
     * Corresponds to {@code POST /v1/containers/{container_id}/remove_security_group} or {@code POST /v1/containers/{container_id}/action {"remove_security_group": ...}}
     *
     * @param containerIdOrName The unique identifier (UUID) or name of the container. 容器的唯一标识符 (UUID) 或名称。
     * @param securityGroupName The name or ID of the security group to remove. 要移除的安全组的名称或 ID。
     * @return An ActionResponse indicating success or failure. 返回指示成功或失败的 ActionResponse。
     */
    ActionResponse removeSecurityGroup(String containerIdOrName, String securityGroupName);
}
