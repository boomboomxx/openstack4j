package org.openstack4j.model.container.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.container.ContainerNets;

/**
 * @author xx
 * @date 2025-04-26
 */
public interface ContainerNetsBuilder extends Buildable.Builder<ContainerNetsBuilder, ContainerNets> {
    ContainerNetsBuilder network(String network);

    ContainerNetsBuilder port(String port);
}
