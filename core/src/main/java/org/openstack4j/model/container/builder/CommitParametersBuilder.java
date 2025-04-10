package org.openstack4j.model.container.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.container.CommitParameters;

/**
 * @author xx
 * @date 2025-04-10
 */
public interface CommitParametersBuilder extends Buildable.Builder<CommitParametersBuilder, CommitParameters> {
    CommitParametersBuilder repository(String repository);

    CommitParametersBuilder tag(String tag);
}
