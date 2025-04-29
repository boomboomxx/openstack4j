package org.openstack4j.openstack.container.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.openstack4j.model.container.Healthcheck;
import org.openstack4j.model.container.builder.HealthCheckBuilder;

/**
 * @author xx
 * @date 2025-04-26
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZunContainerHealthcheck implements Healthcheck {
    private static final long serialVersionUID = 1L;
    String cmd;
    Integer interval;
    Integer retries;
    Integer timeout;

    @Override
    public String getCmd() {
        return cmd;
    }

    @Override
    public Integer getInterval() {
        return interval;
    }

    @Override
    public Integer getTimeout() {
        return timeout;
    }

    @Override
    public Integer getRetries() {
        return retries;
    }

    @Override
    public HealthCheckBuilder toBuilder() {
        return new ConcreteHealthCheckBuilder(this);
    }

    public static HealthCheckBuilder builder() {
        return new ConcreteHealthCheckBuilder();
    }

    public static class ConcreteHealthCheckBuilder implements HealthCheckBuilder {
        private ZunContainerHealthcheck model;

        public ConcreteHealthCheckBuilder() {
            this.model = new ZunContainerHealthcheck();
        }

        public ConcreteHealthCheckBuilder(ZunContainerHealthcheck model) {
            this.model = model;
        }


        @Override
        public HealthCheckBuilder cmd(String cmd) {
            return this;
        }

        @Override
        public HealthCheckBuilder interval(Integer interval) {
            return this;
        }

        @Override
        public HealthCheckBuilder timeout(Integer timeout) {
            return this;
        }

        @Override
        public HealthCheckBuilder retries(Integer retries) {
            return this;
        }

        @Override
        public Healthcheck build() {
            return model;
        }

        @Override
        public HealthCheckBuilder from(Healthcheck in) {
            model = (ZunContainerHealthcheck) in;
            return this;
        }
    }
}
