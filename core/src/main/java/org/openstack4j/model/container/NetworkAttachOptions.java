package org.openstack4j.model.container;

import org.openstack4j.model.ModelEntity;

/**
 * 将网络附加到容器的选项。
 *
 * @author xx
 * @date 2025-04-10
 */
public interface NetworkAttachOptions extends ModelEntity {
    /**
     * @return Network UUID or name. 网络 UUID 或名称。
     */
    String getNetwork();

    /**
     * @return Port UUID. 端口 UUID。
     */
    String getPort();

    /**
     * @return Fixed IP address. 固定 IP 地址。
     */
    String getFixedIp();
}
