package org.openstack4j.model.container;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.container.builder.ContainerCreateBuilder;

/**
 * @author xx
 * @date 2025-04-10
 */
public interface ContainerCreate extends ModelEntity, Buildable<ContainerCreateBuilder> {
    String getName();

    String getImage();

    List<String> getCommand();

    String getMemory();

    Float getCpu();

    Map<String, String> getEnvironment();

    String getWorkdir();

    Map<String, String> getLabels();

    String getImagePullPolicy();

    Map<String, Object> getRestartPolicy();

    Boolean isInteractive();

    ImageDriver getImageDriver();

    List<String> getSecurityGroups();

    Map<String, String> getHints();

    List<ContainerNets> getNets();

    Boolean isAutoRemove();

    String getRuntime();

    String getHostname();


    /**
     * The flag indicating whether to heal non-existent containers in Docker (optional).
     *
     * @return the autoHeal value
     */
    Boolean isAutoHeal();

    /**
     * The availability zone to run the container, providing physical isolation and redundancy.
     * Containers can be distributed across availability zones for high availability (optional).
     *
     * @return the availabilityZone value
     */
    String getAvailabilityZone();

    /**
     * List of volume mount configurations specifying how volumes are mounted into the container (optional).
     * Each entry can define type (volume/bind), source, destination, and other volume parameters.
     *
     * @return the mounts configuration
     */
    Mounts getMounts();

    /**
     * Whether to give extended privileges to the container (optional).
     *
     * @return the privileged flag
     */
    Boolean isPrivileged();

    /**
     * Health check configuration for the container, including test command, interval, retries, and timeout (optional).
     *
     * @return the healthcheck configuration (e.g., a Map containing test, interval, retries, timeout)
     */
    Healthcheck getHealthcheck();

    /**
     * Ports to expose from the container, formatted as {"<port>/<protocol>": {}} (optional).
     *
     * @return the exposed ports configuration
     */
    Map<String, Object> getExposedPorts();

    /**
     * The name of the host where the container should be created (optional, admin-only).
     *
     * @return the host name
     */
    String getHost();

    /**
     * The entrypoint that overwrites the default ENTRYPOINT of the image (optional).
     *
     * @return the entrypoint command as a list of strings
     */
    List<String> getEntrypoint();

    enum ImageDriver {
        DOCKER,
        GLANCE,
        UNKNOWN;

        public static ImageDriver value(String value) {
            if (value == null) {
                return UNKNOWN;
            }
            for (ImageDriver driver : values()) {
                if (driver.name().equalsIgnoreCase(value)) {
                    return driver;
                }
            }
            return UNKNOWN;
        }

        public String value() {
            return name().toLowerCase();
        }

    }
}
