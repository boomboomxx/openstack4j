package org.openstack4j.openstack.container.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.container.NetworkAttachOptions;

/**
 * @author xx
 * @date 2025-04-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZunNetworkAttachOptions implements NetworkAttachOptions {
    @JsonProperty("network") String network;
    @JsonProperty("port") String port;
    @JsonProperty("fixed_ip") String fixedIp;

    @Override public String getNetwork() {
        return network;
    }

    @Override public String getPort() {
        return port;
    }

    @Override public String getFixedIp() {
        return fixedIp;
    }

    // Static constructor or builder recommended
    public static ZunNetworkAttachOptions createWithNetwork(String networkId) {
        ZunNetworkAttachOptions opts = new ZunNetworkAttachOptions();
        opts.network = networkId;
        return opts;
    }

    public static ZunNetworkAttachOptions createWithPort(String portId) {
        ZunNetworkAttachOptions opts = new ZunNetworkAttachOptions();
        opts.port = portId;
        return opts;
    }
}
