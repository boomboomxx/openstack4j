package org.openstack4j.openstack.container.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.container.ContainerAddress;
import org.openstack4j.model.container.builder.AddressBuilder;

/**
 * @author xx
 * @date 2025-04-25
 */
public class ZunContainerAddress implements ContainerAddress {

    @JsonProperty("subnet_id")
    private String subnetId;
    private String addr;
    private String port;
    @JsonProperty("preserve_on_delete")
    private String preserveOnDelete;
    private Integer version;

    @Override
    public String getSubnetId() {
        return subnetId;
    }

    @Override
    public String getAddr() {
        return addr;
    }

    @Override
    public String getPort() {
        return port;
    }

    @Override
    public String getPreserveOnDelete() {
        return preserveOnDelete;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public AddressBuilder toBuilder() {
        return new AddressBuilderImpl(this);
    }

    public static AddressBuilder builder() {
        return new AddressBuilderImpl();
    }

    public static class AddressBuilderImpl implements AddressBuilder {
        private ZunContainerAddress model;

        public AddressBuilderImpl() {
            this(new ZunContainerAddress());
        }

        public AddressBuilderImpl(ZunContainerAddress model) {
            this.model = model;
        }

        @Override
        public AddressBuilder subnetId(String subnetId) {
            model.subnetId = subnetId;
            return this;
        }

        @Override
        public AddressBuilder addr(String addr) {
            model.addr = addr;
            return this;
        }

        @Override
        public AddressBuilder port(String port) {
            model.port = port;
            return this;
        }

        @Override
        public AddressBuilder preserveOnDelete(Boolean preserveOnDelete) {
            model.preserveOnDelete = preserveOnDelete.toString();
            return this;
        }

        @Override
        public AddressBuilder version(Integer version) {
            model.version = version;
            return this;
        }

        @Override
        public ContainerAddress build() {
            return model;
        }

        @Override
        public AddressBuilder from(ContainerAddress in) {
            model = (ZunContainerAddress) in;
            return this;
        }
    }
}
