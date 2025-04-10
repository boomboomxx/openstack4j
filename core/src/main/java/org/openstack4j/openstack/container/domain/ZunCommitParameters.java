package org.openstack4j.openstack.container.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.openstack4j.model.container.CommitParameters;
import org.openstack4j.model.container.builder.CommitParametersBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author xx
 * @date 2025-04-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZunCommitParameters implements CommitParameters {
    String repository;
    String tag;

    public static CommitParametersBuilder builder() {
        return new ConcreteCommitParametersBuilder();
    }

    @Override public String getRepository() {
        return repository;
    }

    @Override public String getTag() {
        return tag;
    }

    @Override
    public Map<String, Object> getQueryParameters() {
        Map<String, Object> params = new java.util.HashMap<>();
        checkNotNull(repository, "Repository must be set for commit");
        params.put("repository", repository);
        if (tag != null) params.put("tag", tag);
        return params;
    }

    @Override
    public CommitParametersBuilder toBuilder() {
        return new ConcreteCommitParametersBuilder(this);
    }

    public static class ConcreteCommitParametersBuilder implements CommitParametersBuilder {
        ZunCommitParameters model = new ZunCommitParameters();

        public ConcreteCommitParametersBuilder() {
        }

        public ConcreteCommitParametersBuilder(ZunCommitParameters model) {
            this.model = model;
        }

        @Override public CommitParametersBuilder repository(String repository) {
            model.repository = repository;
            return this;
        }

        @Override public CommitParametersBuilder tag(String tag) {
            model.tag = tag;
            return this;
        }

        @Override public CommitParameters build() {
            checkNotNull(model.repository, "Repository must be set");
            return model;
        }

        @Override public CommitParametersBuilder from(CommitParameters in) {
            model = (ZunCommitParameters) in;
            return this;
        }
    }
}
