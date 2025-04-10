package org.openstack4j.openstack.container.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.common.Link;
import org.openstack4j.model.container.Container;
import org.openstack4j.openstack.common.ListResult;

/**
 * @author xx
 * @date 2025-04-10
 */
public class ZunContainer implements Container {
    private static final long serialVersionUID = 1L;

    private String uuid;
    private String name;
    private String image;
    @JsonProperty("image_driver")
    private String imageDriver;
    private List<String> command; // Check API response format, might be a single string
    private String status;
    @JsonProperty("status_reason")
    private String statusReason;
    @JsonProperty("task_state")
    private String taskState;
    private Float cpu;
    private String memory; // API often returns as string like "512M" or "1G"
    private String workdir;
    private List<Map<String, Object>> ports;
    private String host;
    private Map<String, String> environment;
    private Map<String, String> labels;
    private Map<String, List<Map<String, Object>>> addresses;
    @JsonProperty("security_groups")
    private List<String> securityGroups;
    @JsonProperty("restart_policy")
    private Map<String, Object> restartPolicy;
    @JsonProperty("status_detail")
    private String statusDetail;
    private Boolean interactive;
    @JsonProperty("auto_remove")
    private Boolean autoRemove;
    @JsonProperty("image_pull_policy")
    private String imagePullPolicy;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated_at")
    private Date updatedAt;
    private List<Link> links;

    // --- Getters ---

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
    public Integer getMemoryMb() {
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
                return Math.round(Long.parseLong(memUpper) / (1024.0f * 1024.0f));
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
    public List<Map<String, Object>> getPorts() {
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
    public Map<String, List<Map<String, Object>>> getAddresses() {
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
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public List<Link> getLinks() {
        return links;
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
        private List<ZunContainer> list;

        @Override
        protected List<ZunContainer> value() {
            return list;
        }
    }
}
