package org.openstack4j.openstack.container.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.openstack4j.model.container.ExecParameters;
import org.openstack4j.model.container.builder.ExecParametersBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author xx
 * @date 2025-04-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZunExecParameters implements ExecParameters {
    String command;
    Boolean interactive;

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
    public Map<String, Object> getQueryParameters() {
        Map<String, Object> params = new java.util.HashMap<>();
        checkNotNull(command, "Command must be set for execute");
        params.put("command", command); // Command might be part of payload, check API doc
        if (interactive != null) params.put("interactive", interactive); // This might also be payload
        return params; // Check API: command/interactive might be in POST body, not query params
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

        @Override public ExecParameters build() {
            checkNotNull(model.command, "Command must be set");
            return model;
        }

        @Override public ExecParametersBuilder from(ExecParameters in) {
            model = (ZunExecParameters) in;
            return this;
        }
    }
}
