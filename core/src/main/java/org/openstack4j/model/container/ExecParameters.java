package org.openstack4j.model.container;

import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.container.builder.ExecParametersBuilder;

/**
 * @author xx
 * @date 2025-04-10
 */
public interface ExecParameters extends ModelEntity, Buildable<ExecParametersBuilder> {
    String getCommand();

    Boolean isInteractive(); // Whether to allocate a TTY

    Boolean isRun();

    Map<String, Object> getQueryParameters();

    String getExecId();

    String getHeight();

    String getWidth();
}
