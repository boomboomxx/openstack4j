package org.openstack4j.model.container.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.container.Healthcheck;

/**
 * @author xx
 * @date 2025-04-26
 */
public interface HealthCheckBuilder extends Buildable.Builder<HealthCheckBuilder, Healthcheck> {
    HealthCheckBuilder cmd(String cmd);

    HealthCheckBuilder interval(Integer interval);

    HealthCheckBuilder timeout(Integer timeout);

    HealthCheckBuilder retries(Integer retries);
}
