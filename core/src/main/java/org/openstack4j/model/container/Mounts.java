package org.openstack4j.model.container;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.container.builder.MountsBuilder;

/**
 * <a href="https://docs.openstack.org/api-ref/application-container/#create-new-container">API Reference</a>
 *
 * @author xx
 * @date 2025-04-26
 */
public interface Mounts extends Buildable<MountsBuilder> {
    /**
     * If this attribute is not specified, the default is Container.MountType#VOLUME
     *
     * @return Container mount type
     * @see Container.MountType
     */
    Container.MountType getType();

    /**
     * To provision a container with pre-existing Cinder volumes bind-mounted, specify the UUID or name of the volume in the source attribute.
     * <p/>
     * Alternatively, Cinder volumes can be dynamically created. In this case, the size of the volume needs to be specified in the size attribute.
     *
     * @return Container mount source
     */
    String getSource();

    /**
     * @return Container mount destination
     */
    String getDestination();

    /**
     * @return The size of mount volume to be created when you want to create a volume mount
     */
    Integer getSize();

}
