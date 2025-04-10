package org.openstack4j.model.container.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.container.ContainerUpdate;

/**
 * @author xx
 * @date 2025-04-10
 */
public interface ContainerUpdateBuilder extends Buildable.Builder<ContainerUpdateBuilder, ContainerUpdate> {

    ContainerUpdateBuilder memory(Integer memory);

    ContainerUpdateBuilder cpu(Float cpu);

    ContainerUpdateBuilder name(String name);
}
