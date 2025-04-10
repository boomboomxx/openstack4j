package org.openstack4j.model.container;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.container.builder.ContainerUpdateBuilder;

/**
 * 使用 JSON Patch 表示容器的更新选项。
 *
 * @author xx
 * @date 2025-04-10
 */
public interface ContainerUpdate extends ModelEntity, Buildable<ContainerUpdateBuilder> {

    /**
     * @return 内存, 单位为MB
     */
    Integer getMemory();

    /**
     * @return 虚拟的CPU数量
     */
    Float getCpu();

    String getName();
}
