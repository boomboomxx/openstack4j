package org.openstack4j.openstack.container.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.container.NetworkDetachOptions;

/**
 * @author xx
 * @date 2025-04-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZunNetworkDetachOptions implements NetworkDetachOptions {
    @JsonProperty("network") String network;
    @JsonProperty("port") String port;

    @Override public String getNetwork() {
        return network;
    }

    @Override public String getPort() {
        return port;
    }

    // Static constructor or builder recommended
    public static ZunNetworkDetachOptions createWithNetwork(String networkId) {
        ZunNetworkDetachOptions opts = new ZunNetworkDetachOptions();
        opts.network = networkId;
        return opts;
    }

    public static ZunNetworkDetachOptions createWithPort(String portId) {
        ZunNetworkDetachOptions opts = new ZunNetworkDetachOptions();
        opts.port = portId;
        return opts;
    }
}
