package org.openstack4j.model.container.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.container.Container;
import org.openstack4j.model.container.Mounts;

/**
 * @author xx
 * @date 2025-04-26
 */
public interface MountsBuilder extends Buildable.Builder<MountsBuilder, Mounts> {
    MountsBuilder type(Container.MountType type);

    MountsBuilder source(String source);

    MountsBuilder destination(String destination);

    MountsBuilder size(Integer size);
}
