package org.openstack4j.model.container;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.container.builder.ContainerNetsBuilder;

/**
 * Container create nets
 *
 * @author xx
 * @date 2025-04-26
 */
public interface ContainerNets extends ModelEntity, Buildable<ContainerNetsBuilder> {
    /**
     * @return the UUID or name of the network
     */
    String getNetwork();

    /**
     * @return the UUID or name of the port
     */
    String getPort();
}
