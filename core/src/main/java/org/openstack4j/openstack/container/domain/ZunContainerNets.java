package org.openstack4j.openstack.container.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.openstack4j.model.container.ContainerNets;
import org.openstack4j.model.container.builder.ContainerNetsBuilder;

/**
 * @author xx
 * @date 2025-04-26
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZunContainerNets implements ContainerNets {
    String network;
    String port;

    @Override
    public String getNetwork() {
        return network;
    }

    @Override
    public String getPort() {
        return port;
    }

    @Override
    public ContainerNetsBuilder toBuilder() {
        return new ConcreteContainerNetsBuilder(this);
    }

    public static ContainerNetsBuilder builder() {
        return new ConcreteContainerNetsBuilder();
    }

    public static class ConcreteContainerNetsBuilder implements ContainerNetsBuilder {

        ZunContainerNets model;

        public ConcreteContainerNetsBuilder() {
            this.model = new ZunContainerNets();
        }

        public ConcreteContainerNetsBuilder(ZunContainerNets model) {
            this.model = model;
        }

        @Override
        public ContainerNetsBuilder network(String network) {
            model.network = network;
            return this;
        }

        @Override
        public ContainerNetsBuilder port(String port) {
            model.port = port;
            return this;
        }

        @Override
        public ContainerNets build() {
            return model;
        }

        @Override
        public ContainerNetsBuilder from(ContainerNets in) {
            this.model = (ZunContainerNets) in;
            return this;
        }
    }
}
