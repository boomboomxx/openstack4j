package org.openstack4j.openstack.container.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.container.ExecParameters;
import org.openstack4j.model.container.builder.ExecParametersBuilder;

/**
 * @author xx
 * @date 2025-04-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZunExecParameters implements ExecParameters {
    String command;
    Boolean interactive;
    Boolean run;
    String execId;
    @JsonProperty("h")
    String height;
    @JsonProperty("w")
    String width;

    public static ExecParametersBuilder builder() {
        return new ConcreteExecParametersBuilder();
    }

    @Override public String getCommand() {
        return command;
    }

    @Override public Boolean isInteractive() {
        return interactive;
    }

    @Override
    public Boolean isRun() {
        return run;
    }

    @Override
    public Map<String, Object> getQueryParameters() {
        Map<String, Object> params = new java.util.HashMap<>();
        if (run != null) params.put("run", run);
        if (execId != null) params.put("exec_id", execId);
        if (height != null) params.put("h", height);
        if (width != null) params.put("w", width);
        if (command != null) params.put("command", command);
        if (interactive != null) {
            params.put("interactive", interactive);
            if (interactive) params.put("run", false);
        }
        return params; // Check API: command/interactive might be in POST body, not query params
    }

    @Override
    public String getExecId() {
        return execId;
    }

    @Override
    public String getHeight() {
        return height;
    }

    @Override
    public String getWidth() {
        return width;
    }

    @Override
    public ExecParametersBuilder toBuilder() {
        return new ConcreteExecParametersBuilder(this);
    }

    public static class ConcreteExecParametersBuilder implements ExecParametersBuilder {
        ZunExecParameters model = new ZunExecParameters();

        public ConcreteExecParametersBuilder() {
        }

        public ConcreteExecParametersBuilder(ZunExecParameters model) {
            this.model = model;
        }

        @Override public ExecParametersBuilder command(String command) {
            model.command = command;
            return this;
        }

        @Override public ExecParametersBuilder interactive(boolean interactive) {
            model.interactive = interactive;
            return this;
        }

        @Override
        public ExecParametersBuilder run(boolean run) {
            model.run = run;
            return this;
        }

        @Override
        public ExecParametersBuilder execId(String execId) {
            model.execId = execId;

            return this;
        }

        @Override
        public ExecParametersBuilder width(String width) {
            model.width = width;
            return this;
        }

        @Override
        public ExecParametersBuilder height(String height) {
            model.height = height;
            return this;
        }

        @Override public ExecParameters build() {
            return model;
        }

        @Override public ExecParametersBuilder from(ExecParameters in) {
            model = (ZunExecParameters) in;
            return this;
        }
    }
}
