package org.openstack4j.model.container;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.Link;

/**
 * Container
 *
 * @author xx
 * @date 2025-04-10
 */
public interface Container extends ModelEntity {
    /**
     * @return The unique identifier (UUID) of the container. 容器的唯一标识符 (UUID)。
     */
    String getUuid();

    /**
     * @return The user-defined name of the container. 用户定义的容器名称。
     */
    String getName();

    /**
     * @return The image used to create the container. 用于创建容器的镜像。
     */
    String getImage();

    /**
     * @return The image driver used for the container. 容器使用的镜像驱动程序。
     */
    String getImageDriver();

    /**
     * @return The command executed when the container starts. 容器启动时执行的命令。
     */
    List<String> getCommand(); // Or String depending on API response format

    /**
     * @return The current status of the container (e.g., Running, Stopped, Error). 容器的当前状态。
     */
    String getStatus();

    /**
     * @return The reason for the current status, if applicable. 当前状态的原因（如果适用）。
     */
    String getStatusReason();

    /**
     * @return The task state of the container during operations (e.g., creating, deleting). 操作期间容器的任务状态。
     */
    String getTaskState();

    /**
     * @return The amount of CPU allocated to the container. 分配给容器的 CPU 数量。
     */
    Float getCpu();

    /**
     * @return The amount of memory allocated to the container (in MiB). 分配给容器的内存量 (以 MiB 为单位)。
     */
    Integer getMemoryMb(); // Renamed from memory for clarity

    /**
     * @return The working directory inside the container. 容器内的工作目录。
     */
    String getWorkdir();

    /**
     * @return A list of port mappings for the container. 容器的端口映射列表。
     */
    List<Map<String, Object>> getPorts(); // Structure might vary, needs specific mapping

    /**
     * @return The hostname of the compute node running the container. 运行容器的计算节点的主机名。
     */
    String getHost();

    /**
     * @return Environment variables set in the container. 在容器中设置的环境变量。
     */
    Map<String, String> getEnvironment();

    /**
     * @return Labels associated with the container. 与容器关联的标签。
     */
    Map<String, String> getLabels();

    /**
     * @return Addresses assigned to the container (network details). 分配给容器的地址（网络详细信息）。
     */
    Map<String, List<Map<String, Object>>> getAddresses(); // Complex structure

    /**
     * @return Security groups associated with the container. 与容器关联的安全组。
     */
    List<String> getSecurityGroups();

    /**
     * @return The restart policy for the container. 容器的重启策略。
     */
    Map<String, Object> getRestartPolicy(); // e.g., {"Name": "no", "MaximumRetryCount": 0}

    /**
     * @return The status detail of the container. 容器的状态详细信息。
     */
    String getStatusDetail();

    /**
     * @return Indicates if the container runs in interactive mode. 指示容器是否以交互模式运行。
     */
    Boolean isInteractive();

    /**
     * @return Indicates if the container should be automatically removed on exit. 指示容器退出时是否应自动移除。
     */
    Boolean isAutoRemove();

    /**
     * @return The image pull policy (e.g., always, ifnotpresent, never). 镜像拉取策略。
     */
    String getImagePullPolicy();

    /**
     * @return The timestamp when the container was created. 容器创建的时间戳。
     */
    Date getCreatedAt();

    /**
     * @return The timestamp when the container was last updated. 容器上次更新的时间戳。
     */
    Date getUpdatedAt();

    /**
     * @return Links related to the container (e.g., self, bookmark). 与容器相关的链接。
     */
    List<Link> getLinks();

    // Add methods for other fields like runtime, hostname, volumes_attached, etc. if needed
    // 如果需要，添加其他字段的方法，如 runtime, hostname, volumes_attached 等。
}
