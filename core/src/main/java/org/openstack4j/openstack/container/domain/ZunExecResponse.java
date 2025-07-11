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
    @JsonProperty("output")
    String output;
    @JsonProperty("proxy_url")
    String url;
    @JsonProperty("exit_code")
    String exitCode;
    @JsonProperty("exec_id")
    String execId;

    @Override public String getUrl() {
        return url;
    }

    @Override
    public String getOutput() {
        return output;
    }

    @Override
    public String getExitCode() {
        return exitCode;
    }

    @Override
    public String getExecId() {
        return execId;
    }

}
