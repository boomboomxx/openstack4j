package org.openstack4j.model.container;

import org.openstack4j.model.ModelEntity;

/**
 * 从容器分离网络的选项。
 *
 * @author xx
 * @date 2025-04-10
 */
public interface NetworkDetachOptions extends ModelEntity {
    /**
     * @return Network UUID or name (required if port not specified). 网络 UUID 或名称（如果未指定端口则为必需）。
     */
    String getNetwork();

    /**
     * @return Port UUID (required if network not specified). 端口 UUID（如果未指定网络则为必需）。
     */
    String getPort();
}
