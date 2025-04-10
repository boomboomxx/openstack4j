package org.openstack4j.openstack.container.domain;

import org.openstack4j.model.container.ContainerUpdate;
import org.openstack4j.model.container.builder.ContainerUpdateBuilder;

/**
 * ContainerUpdate 的具体实现。
 *
 * @author xx
 * @date 2025-04-10
 */
public class ZunContainerUpdate implements ContainerUpdate {

    private Integer memory;
    private Float cpu;
    private String name;

    public static ContainerUpdateBuilder builder() {
        return new ConcreteContainerUpdateBuilder();
    }

    @Override public ContainerUpdateBuilder toBuilder() {
        return new ConcreteContainerUpdateBuilder(this);
    }

    @Override
    public Integer getMemory() {
        return memory;
    }

    @Override
    public Float getCpu() {
        return cpu;
    }

    @Override
    public String getName() {
        return name;
    }

    public static class ConcreteContainerUpdateBuilder implements ContainerUpdateBuilder {
        ZunContainerUpdate model;

        ConcreteContainerUpdateBuilder() {
            this(new ZunContainerUpdate());
        }

        ConcreteContainerUpdateBuilder(ZunContainerUpdate model) {
            this.model = model;
        }


        @Override
        public ContainerUpdate build() {
            return model;
        }

        @Override
        public ContainerUpdateBuilder from(ContainerUpdate in) {
            model = (ZunContainerUpdate) in;
            return this;
        }

        @Override
        public ContainerUpdateBuilder memory(Integer memory) {
            model.memory = memory;
            return this;
        }

        @Override
        public ContainerUpdateBuilder cpu(Float cpu) {
            model.cpu = cpu;
            return this;
        }

        @Override
        public ContainerUpdateBuilder name(String name) {
            model.name = name;
            return this;
        }
    }
}
