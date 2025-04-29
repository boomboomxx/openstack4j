package org.openstack4j.model.container.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.container.ContainerAddress;

/**
 * @author xx
 * @date 2025-04-25
 */
public interface AddressBuilder extends Buildable.Builder<AddressBuilder, ContainerAddress> {
    AddressBuilder subnetId(String subnetId);

    AddressBuilder addr(String addr);

    AddressBuilder port(String port);

    AddressBuilder preserveOnDelete(Boolean preserveOnDelete);

    AddressBuilder version(Integer version);
}
