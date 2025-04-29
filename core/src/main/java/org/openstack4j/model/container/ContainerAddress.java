package org.openstack4j.model.container;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.container.builder.AddressBuilder;

/**
 * @author xx
 * @date 2025-04-25
 */
public interface ContainerAddress extends ModelEntity, Buildable<AddressBuilder> {

    /**
     * @return the ID of the subnet
     */
    String getSubnetId();

    /**
     * @return the IP addr of the container
     */
    String getAddr();

    /**
     * @return the ID of the port
     */
    String getPort();

    /**
     *
     */
    String getPreserveOnDelete();

    /**
     * @return the version of the address, 4/6
     */
    Integer getVersion();
}
