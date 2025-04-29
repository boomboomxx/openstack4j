package org.openstack4j.openstack.container.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.common.functions.EnforceVersionToURL;
import org.openstack4j.openstack.internal.MicroVersion;
import org.openstack4j.openstack.internal.MicroVersionedOpenStackService;

/**
 * @author xx
 * @date 2025-04-10
 */
public class BaseContainerService extends MicroVersionedOpenStackService {
    private final static String API_VERSION_HEADER = "OpenStack-API-Version";
    private static MicroVersion MAX_VERSION = new MicroVersion(1, 40);

    public BaseContainerService() {
        super(ServiceType.CONTAINER, MAX_VERSION, EnforceVersionToURL.instance("/v1", true));
    }

    @Override
    protected String getApiVersionHeader() {
        return API_VERSION_HEADER;
    }

}
