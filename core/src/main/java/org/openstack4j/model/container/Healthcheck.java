package org.openstack4j.model.container;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.container.builder.HealthCheckBuilder;

/**
 * A dict of health check for the container. Specify a test command to perform to check that the container is healthy. Four parameters are supported:
 *
 * @author xx
 * @date 2025-04-26
 */
public interface Healthcheck extends ModelEntity, Buildable<HealthCheckBuilder> {
    /**
     * @return Command to run to check health.
     */
    String getCmd();

    /**
     * @return Time between running the check in seconds.
     */
    Integer getInterval();

    /**
     * @return Consecutive failures needed to report unhealthy.
     */
    Integer getTimeout();

    /**
     * @return Consecutive failures needed to report unhealthy.
     */
    Integer getRetries();

}
