package org.openstack4j.model.container;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.container.builder.ContainerCreateBuilder;

/**
 * @author xx
 * @date 2025-04-10
 */
public interface ContainerCreate extends ModelEntity, Buildable<ContainerCreateBuilder> {
    String getName();

    String getImage();

    List<String> getCommand();

    String getMemory();

    Float getCpu();

    Map<String, String> getEnvironment();

    String getWorkdir();

    Map<String, String> getLabels();

    String getImagePullPolicy();

    Map<String, Object> getRestartPolicy();

    Boolean isInteractive();

    String getImageDriver();

    List<String> getSecurityGroups();

    Map<String, String> getHints();

    List<Map<String, String>> getNets();

    // mounts
    Boolean isAutoRemove();

    String getRuntime();

    String getHostname();
}
