package org.openstack4j.openstack.container.domain;

import org.openstack4j.model.container.Container;
import org.openstack4j.model.container.Mounts;
import org.openstack4j.model.container.builder.MountsBuilder;

/**
 * @author xx
 * @date 2025-04-26
 */
public class ZunContainerMounts implements Mounts {
    Container.MountType type;
    String source;
    String destination;
    Integer size;

    @Override
    public Container.MountType getType() {
        return type;
    }

    @Override
    public String getSource() {
        return "";
    }

    @Override
    public String getDestination() {
        return "";
    }

    @Override
    public Integer getSize() {
        return 0;
    }

    @Override
    public MountsBuilder toBuilder() {
        return new ConcreteContainerMountsBuilder(this);
    }

    public static MountsBuilder builder() {
        return new ConcreteContainerMountsBuilder();
    }


    public static class ConcreteContainerMountsBuilder implements MountsBuilder {

        private ZunContainerMounts model;

        public ConcreteContainerMountsBuilder() {
            this.model = new ZunContainerMounts();
        }

        public ConcreteContainerMountsBuilder(ZunContainerMounts model) {
            this.model = model;
        }


        @Override
        public MountsBuilder type(Container.MountType type) {
            model.type = type;
            return this;
        }

        @Override
        public MountsBuilder source(String source) {
            model.source = source;
            return this;
        }

        @Override
        public MountsBuilder destination(String destination) {
            model.destination = destination;
            return this;
        }

        @Override
        public MountsBuilder size(Integer size) {
            model.size = size;
            return this;
        }

        @Override
        public Mounts build() {
            return model;
        }

        @Override
        public MountsBuilder from(Mounts in) {
            model = (ZunContainerMounts) in;
            return this;
        }
    }
}
