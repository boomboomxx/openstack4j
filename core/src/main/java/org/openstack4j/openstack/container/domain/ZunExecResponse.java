package org.openstack4j.openstack.container.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.container.ExecResponse;

/**
 * @author xx
 * @date 2025-04-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZunExecResponse implements ExecResponse {
    // Field names depend on actual Zun API response for execute
    @JsonProperty("output_url") // Example field name
            String url;
    @JsonProperty("attach_token") // Example field name
    String attachToken;

    @Override public String getUrl() {
        return url;
    }

    @Override public String getAttachToken() {
        return attachToken;
    }
}
