package org.openstack4j.model.container.builder;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.container.ContainerCreate;

/**
 * @author xx
 * @date 2025-04-10
 */
public interface ContainerCreateBuilder extends Buildable.Builder<ContainerCreateBuilder, ContainerCreate> {
    ContainerCreateBuilder name(String name);

    ContainerCreateBuilder image(String image);

    ContainerCreateBuilder command(List<String> command);

    ContainerCreateBuilder command(String command); // Convenience

    ContainerCreateBuilder memory(String memory); // e.g., "512M", "1G"

    ContainerCreateBuilder cpu(Float cpu);

    ContainerCreateBuilder environment(Map<String, String> environment);

    ContainerCreateBuilder addEnvironment(String key, String value);

    ContainerCreateBuilder workdir(String workdir);

    ContainerCreateBuilder labels(Map<String, String> labels);

    ContainerCreateBuilder addLabel(String key, String value);

    ContainerCreateBuilder imagePullPolicy(String policy); // always, ifnotpresent, never

    ContainerCreateBuilder restartPolicy(String name, int maxRetryCount); // e.g., "no", "on-failure", "always"

    ContainerCreateBuilder interactive(boolean interactive);

    ContainerCreateBuilder imageDriver(String imageDriver);

    ContainerCreateBuilder securityGroups(List<String> securityGroups);

    ContainerCreateBuilder addSecurityGroup(String securityGroupIdOrName);

    ContainerCreateBuilder hints(Map<String, String> hints);

    ContainerCreateBuilder addHint(String key, String value);

    ContainerCreateBuilder nets(List<Map<String, String>> nets); // e.g., [{"network": "uuid"}, {"port": "uuid"}]

    ContainerCreateBuilder addNet(String networkId);

    ContainerCreateBuilder addNetPort(String portId);

    // Add mounts if needed
    ContainerCreateBuilder autoRemove(boolean autoRemove);

    ContainerCreateBuilder runtime(String runtime); // e.g., "runc"

    ContainerCreateBuilder hostname(String hostname);
}
