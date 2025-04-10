package org.openstack4j.model.container;

import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.container.builder.LogsParametersBuilder;

/**
 * @author xx
 * @date 2025-04-10
 */
public interface LogsParameters extends ModelEntity, Buildable<LogsParametersBuilder> {
    Boolean getStdout();

    Boolean getStderr();

    Boolean getTimestamps();

    String getTail(); // "all" or number

    String getSince(); // Timestamp or duration (e.g., "2023-01-01T10:00:00Z", "10m")

    Map<String, Object> getQueryParameters(); // Helper to build query string
}
