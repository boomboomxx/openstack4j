package org.openstack4j.model.container.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.container.ExecParameters;

/**
 * @author xx
 * @date 2025-04-10
 */
public interface ExecParametersBuilder extends Buildable.Builder<ExecParametersBuilder, ExecParameters> {
    ExecParametersBuilder command(String command);

    ExecParametersBuilder interactive(boolean interactive);

    ExecParametersBuilder run(boolean run);

    ExecParametersBuilder execId(String execId);

    ExecParametersBuilder width(String width);

    ExecParametersBuilder height(String height);
}
