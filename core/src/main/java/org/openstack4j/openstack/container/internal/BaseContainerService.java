package org.openstack4j.openstack.container.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.common.functions.EnforceVersionToURL;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * @author xx
 * @date 2025-04-10
 */
public class BaseContainerService extends BaseOpenStackService {
    public BaseContainerService() {
        super(ServiceType.CONTAINER, EnforceVersionToURL.instance("/v1", true));
    }
}
