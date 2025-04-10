package org.openstack4j.openstack.container.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.openstack4j.model.container.LogsParameters;
import org.openstack4j.model.container.builder.LogsParametersBuilder;

/**
 * @author xx
 * @date 2025-04-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZunLogsParameters implements LogsParameters {
    Boolean stdout;
    Boolean stderr;
    Boolean timestamps;
    String tail;
    String since;

    public static LogsParametersBuilder builder() {
        return new ConcreteLogsParametersBuilder();
    }

    @Override public Boolean getStdout() {
        return stdout;
    }

    @Override public Boolean getStderr() {
        return stderr;
    }

    @Override public Boolean getTimestamps() {
        return timestamps;
    }

    @Override public String getTail() {
        return tail;
    }

    @Override public String getSince() {
        return since;
    }

    @Override
    public Map<String, Object> getQueryParameters() {
        Map<String, Object> params = new java.util.HashMap<>();
        if (stdout != null) params.put("stdout", stdout);
        if (stderr != null) params.put("stderr", stderr);
        if (timestamps != null) params.put("timestamps", timestamps);
        if (tail != null) params.put("tail", tail);
        if (since != null) params.put("since", since);
        return params;
    }

    @Override
    public LogsParametersBuilder toBuilder() {
        return new ConcreteLogsParametersBuilder(this);
    }

    public static class ConcreteLogsParametersBuilder implements LogsParametersBuilder {
        ZunLogsParameters model = new ZunLogsParameters();

        public ConcreteLogsParametersBuilder() {
        }

        public ConcreteLogsParametersBuilder(ZunLogsParameters model) {
            this.model = model;
        }


        @Override public LogsParametersBuilder stdout(boolean val) {
            model.stdout = val;
            return this;
        }

        @Override public LogsParametersBuilder stderr(boolean val) {
            model.stderr = val;
            return this;
        }

        @Override public LogsParametersBuilder timestamps(boolean val) {
            model.timestamps = val;
            return this;
        }

        @Override public LogsParametersBuilder tail(int lines) {
            model.tail = String.valueOf(lines);
            return this;
        }

        @Override public LogsParametersBuilder tailAll() {
            model.tail = "all";
            return this;
        }

        @Override public LogsParametersBuilder since(String timestampOrDuration) {
            model.since = timestampOrDuration;
            return this;
        }

        @Override public LogsParameters build() {
            return model;
        }

        @Override public LogsParametersBuilder from(LogsParameters in) {
            model = (ZunLogsParameters) in;
            return this;
        }
    }
}
