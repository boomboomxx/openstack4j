package org.openstack4j.model.container.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.container.LogsParameters;

/**
 * @author xx
 * @date 2025-04-10
 */
public interface LogsParametersBuilder extends Buildable.Builder<LogsParametersBuilder, LogsParameters> {
    LogsParametersBuilder stdout(boolean val);

    LogsParametersBuilder stderr(boolean val);

    LogsParametersBuilder timestamps(boolean val);

    LogsParametersBuilder tail(int lines);

    LogsParametersBuilder tailAll();

    LogsParametersBuilder since(String timestampOrDuration);
}
