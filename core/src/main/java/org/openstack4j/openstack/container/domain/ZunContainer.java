package org.openstack4j.openstack.container.domain;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.container.Container;
import org.openstack4j.openstack.common.GenericLink;
import org.openstack4j.openstack.common.ListResult;

/**
 * @author xx
 * @date 2025-04-10
 */
public class ZunContainer implements Container {
    private static final long serialVersionUID = 1L;

    String uuid;
    String name;
    String image;
    @JsonProperty("image_driver")
    String imageDriver;
    List<String> command; // Check API response format, might be a single string
    String status;
    @JsonProperty("status_reason")
    String statusReason;
    @JsonProperty("task_state")
    String taskState;
    Float cpu;
    String memory; // API often returns as string like "512M" or "1G"
    String workdir;
    List<String> ports;
    String host;
    Map<String, String> environment;
    Map<String, String> labels;
    Map<String, List<ZunContainerAddress>> addresses;
    @JsonProperty("security_groups")
    List<String> securityGroups;
    @JsonProperty("restart_policy")
    Map<String, Object> restartPolicy;
    @JsonProperty("status_detail")
    String statusDetail;
    Boolean interactive;
    @JsonProperty("auto_remove")
    Boolean autoRemove;
    @JsonProperty("image_pull_policy")
    String imagePullPolicy;
    @JsonProperty("created_at")
    String createdAt;
    @JsonProperty("updated_at")
    String updatedAt;
    List<GenericLink> links;
    Boolean tty;
    String hostname;
    @JsonProperty("auto_heal")
    Boolean autoHeal;
    Boolean privileged;
    @JsonProperty("user_id")
    String userId;
    @JsonProperty("project_id")
    String projectId;
    Integer disk;
    ZunContainerHealthcheck healthcheck;
    @JsonProperty("cpu_policy")
    Container.CpuPolicy cpuPolicy;
    @JsonProperty("registry_id")
    String registryId;
    List<String> entrypoint;


    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public String getImageDriver() {
        return imageDriver;
    }

    @Override
    public List<String> getCommand() {
        return command;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getStatusReason() {
        return statusReason;
    }

    @Override
    public String getTaskState() {
        return taskState;
    }

    @Override
    public Float getCpu() {
        return cpu;
    }

    /**
     * Parses the memory string (e.g., "512M", "1G") into MiB.
     * 将内存字符串（例如 "512M", "1G"）解析为 MiB。
     *
     * @return Memory in MiB, or null if parsing fails. 以 MiB 为单位的内存，如果解析失败则为 null。
     */
    @Override
    public Integer getMemory() {
        if (memory == null || memory.isEmpty()) {
            return null;
        }
        try {
            String memUpper = memory.toUpperCase();
            if (memUpper.endsWith("G")) {
                return Integer.parseInt(memUpper.substring(0, memUpper.length() - 1)) * 1024;
            } else if (memUpper.endsWith("M")) {
                return Integer.parseInt(memUpper.substring(0, memUpper.length() - 1));
            } else if (memUpper.endsWith("K")) {
                // Handle KB if necessary, converting to MB (might be lossy)
                return Math.round(Integer.parseInt(memUpper.substring(0, memUpper.length() - 1)) / 1024.0f);
            } else {
                // Assume bytes if no suffix, convert to MB
                return Integer.parseInt(memory);
            }
        } catch (NumberFormatException e) {
            // Log error or handle appropriately
            System.err.println("Failed to parse memory string: " + memory);
            return null;
        }
    }

    @Override
    public String getWorkdir() {
        return workdir;
    }

    @Override
    public List<String> getPorts() {
        return ports;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public Map<String, String> getEnvironment() {
        return environment;
    }

    @Override
    public Map<String, String> getLabels() {
        return labels;
    }

    @Override
    public Map<String, List<ZunContainerAddress>> getAddresses() {
        return addresses;
    }

    @Override
    public List<String> getSecurityGroups() {
        return securityGroups;
    }

    @Override
    public Map<String, Object> getRestartPolicy() {
        return restartPolicy;
    }

    @Override
    public String getStatusDetail() {
        return statusDetail;
    }

    @Override
    public Boolean isInteractive() {
        return interactive != null && interactive;
    }

    @Override
    public Boolean isAutoRemove() {
        return autoRemove != null && autoRemove;
    }

    @Override
    public String getImagePullPolicy() {
        return imagePullPolicy;
    }

    @Override
    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public List<GenericLink> getLinks() {
        return links;
    }

    @Override
    public Boolean getTty() {
        return tty;
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public Boolean getAutoHeal() {
        return autoHeal;
    }

    @Override
    public Boolean getPrivileged() {
        return privileged;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public Integer getDisk() {
        return disk;
    }

    @Override
    public ZunContainerHealthcheck getHealthcheck() {
        return healthcheck;
    }

    @Override
    public String getRegistryId() {
        return registryId;
    }

    @Override
    public CpuPolicy getCpuPolicy() {
        return cpuPolicy;
    }

    @Override
    public List<String> getEntrypoint() {
        return entrypoint;
    }

    @Override
    public String toString() {
        return "ZunContainer{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", host='" + host + '\'' +
                ", cpu=" + cpu +
                ", memory='" + memory + '\'' + // Show raw memory string
                ", taskState='" + taskState + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    /**
     * Wrapper class for listing containers.
     * 用于列出容器的包装类。
     */
    public static class ZunContainers extends ListResult<ZunContainer> {
        private static final long serialVersionUID = 1L;
        @JsonProperty("containers") // JSON key for the list
        List<ZunContainer> list;

        @Override
        protected List<ZunContainer> value() {
            return list;
        }
    }
}
