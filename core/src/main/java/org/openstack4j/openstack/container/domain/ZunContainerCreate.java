package org.openstack4j.openstack.container.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import org.openstack4j.model.container.ContainerCreate;
import org.openstack4j.model.container.ContainerNets;
import org.openstack4j.model.container.Healthcheck;
import org.openstack4j.model.container.Mounts;
import org.openstack4j.model.container.builder.ContainerCreateBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author xx
 * @date 2025-04-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZunContainerCreate implements ContainerCreate {

    private static final long serialVersionUID = 1L;

    // Required fields
    @JsonProperty("image")
    String image;

    // Optional fields
    @JsonProperty("name")
    String name;
    @JsonProperty("command")
    List<String> command;
    @JsonProperty("memory")
    String memory; // Keep as String for API ("512M")
    @JsonProperty("cpu")
    Float cpu;
    @JsonProperty("environment")
    Map<String, String> environment;
    @JsonProperty("workdir")
    String workdir;
    @JsonProperty("labels")
    Map<String, String> labels;
    @JsonProperty("image_pull_policy")
    String imagePullPolicy;
    @JsonProperty("restart_policy")
    Map<String, Object> restartPolicy;
    @JsonProperty("interactive")
    Boolean interactive;
    @JsonProperty("image_driver")
    ImageDriver imageDriver;
    @JsonProperty("security_groups")
    List<String> securityGroups;
    @JsonProperty("hints")
    Map<String, String> hints;
    @JsonProperty("nets")
    List<ContainerNets> nets;
    @JsonProperty("runtime")
    String runtime;
    @JsonProperty("hostname")
    String hostname;
    @JsonProperty("auto_remove")
    Boolean autoRemove;
    @JsonProperty("auto_heal")
    Boolean autoHeal;
    @JsonProperty("availability_zone")
    String availabilityZone;
    String host;
    ZunContainerMounts mounts;
    Boolean privileged;
    ZunContainerHealthcheck healthcheck;
    @JsonProperty("exposed_ports")
    Map<String, Object> exposedPorts;
    List<String> entrypoint;


    // --- Getters ---
    @Override public String getName() {
        return name;
    }

    @Override public String getImage() {
        return image;
    }

    @Override public List<String> getCommand() {
        return command;
    }

    @Override public String getMemory() {
        return memory;
    }

    @Override public Float getCpu() {
        return cpu;
    }

    @Override public Map<String, String> getEnvironment() {
        return environment;
    }

    @Override public String getWorkdir() {
        return workdir;
    }

    @Override public Map<String, String> getLabels() {
        return labels;
    }

    @Override public String getImagePullPolicy() {
        return imagePullPolicy;
    }

    @Override public Map<String, Object> getRestartPolicy() {
        return restartPolicy;
    }

    @Override public Boolean isInteractive() {
        return interactive;
    }

    @Override public ImageDriver getImageDriver() {
        return imageDriver;
    }

    @Override public List<String> getSecurityGroups() {
        return securityGroups;
    }

    @Override public Map<String, String> getHints() {
        return hints;
    }

    @Override public List<ContainerNets> getNets() {
        return nets;
    }

    @Override public Boolean isAutoRemove() {
        return autoRemove;
    }

    @Override public String getRuntime() {
        return runtime;
    }

    @Override public String getHostname() {
        return hostname;
    }

    @Override
    public Boolean isAutoHeal() {
        return autoHeal;
    }

    @Override
    public String getAvailabilityZone() {
        return availabilityZone;
    }

    @Override
    public ZunContainerMounts getMounts() {
        return mounts;
    }

    @Override
    public Boolean isPrivileged() {
        return privileged;
    }

    @Override
    public ZunContainerHealthcheck getHealthcheck() {
        return healthcheck;
    }

    @Override
    public Map<String, Object> getExposedPorts() {
        return exposedPorts;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public List<String> getEntrypoint() {
        return entrypoint;
    }

    /**
     * Returns a builder for creating ZunContainerCreate instances.
     * 返回用于创建 ZunContainerCreate 实例的构建器。
     *
     * @return ContainerCreateBuilder instance. 构建器实例。
     */
    public static ContainerCreateBuilder builder() {
        return new ConcreteContainerCreateBuilder();
    }

    @Override
    public ContainerCreateBuilder toBuilder() {
        return new ConcreteContainerCreateBuilder(this);
    }

    /**
     * Concrete builder implementation.
     * 构建器的具体实现。
     */
    public static class ConcreteContainerCreateBuilder implements ContainerCreateBuilder {
        private ZunContainerCreate model;

        ConcreteContainerCreateBuilder() {
            this(new ZunContainerCreate());
        }

        ConcreteContainerCreateBuilder(ZunContainerCreate model) {
            this.model = model;
            // Initialize lists/maps if null
            if (this.model.environment == null) this.model.environment = new java.util.HashMap<>();
            if (this.model.labels == null) this.model.labels = new java.util.HashMap<>();
            if (this.model.securityGroups == null) this.model.securityGroups = new java.util.ArrayList<>();
            if (this.model.hints == null) this.model.hints = new java.util.HashMap<>();
            if (this.model.nets == null) this.model.nets = new java.util.ArrayList<>();
        }

        @Override public ContainerCreateBuilder name(String name) {
            model.name = name;
            return this;
        }

        @Override public ContainerCreateBuilder image(String image) {
            model.image = image;
            return this;
        }

        @Override public ContainerCreateBuilder command(List<String> command) {
            model.command = command;
            return this;
        }

        @Override public ContainerCreateBuilder command(String command) {
            if (model.command == null) model.command = new java.util.ArrayList<>();
            // Simple split by space, might need more sophisticated parsing for complex commands
            model.command.addAll(Arrays.asList(command.split("\\s+")));
            return this;
        }

        @Override public ContainerCreateBuilder memory(String memory) {
            model.memory = memory;
            return this;
        }

        @Override public ContainerCreateBuilder cpu(Float cpu) {
            model.cpu = cpu;
            return this;
        }

        @Override public ContainerCreateBuilder environment(Map<String, String> environment) {
            model.environment = environment;
            return this;
        }

        @Override public ContainerCreateBuilder addEnvironment(String key, String value) {
            if (model.environment == null) model.environment = new java.util.HashMap<>();
            model.environment.put(key, value);
            return this;
        }

        @Override public ContainerCreateBuilder workdir(String workdir) {
            model.workdir = workdir;
            return this;
        }

        @Override public ContainerCreateBuilder labels(Map<String, String> labels) {
            model.labels = labels;
            return this;
        }

        @Override public ContainerCreateBuilder addLabel(String key, String value) {
            if (model.labels == null) model.labels = new java.util.HashMap<>();
            model.labels.put(key, value);
            return this;
        }

        @Override public ContainerCreateBuilder imagePullPolicy(String policy) {
            model.imagePullPolicy = policy;
            return this;
        }

        @Override public ContainerCreateBuilder restartPolicy(String name, int maxRetryCount) {
            model.restartPolicy = ImmutableMap.of("Name", name, "MaximumRetryCount", maxRetryCount);
            return this;
        }

        @Override public ContainerCreateBuilder interactive(boolean interactive) {
            model.interactive = interactive;
            return this;
        }

        @Override public ContainerCreateBuilder imageDriver(ImageDriver imageDriver) {
            model.imageDriver = imageDriver;
            return this;
        }

        @Override public ContainerCreateBuilder securityGroups(List<String> securityGroups) {
            model.securityGroups = securityGroups;
            return this;
        }

        @Override public ContainerCreateBuilder addSecurityGroup(String securityGroupIdOrName) {
            if (model.securityGroups == null) model.securityGroups = new java.util.ArrayList<>();
            model.securityGroups.add(securityGroupIdOrName);
            return this;
        }

        @Override public ContainerCreateBuilder hints(Map<String, String> hints) {
            model.hints = hints;
            return this;
        }

        @Override public ContainerCreateBuilder addHint(String key, String value) {
            if (model.hints == null) model.hints = new java.util.HashMap<>();
            model.hints.put(key, value);
            return this;
        }

        @Override public ContainerCreateBuilder nets(List<ContainerNets> nets) {
            model.nets = nets;
            return this;
        }

        @Override public ContainerCreateBuilder addNet(String networkId) {
            if (model.nets == null) model.nets = new java.util.ArrayList<>();
            model.nets.add(ZunContainerNets.builder().network(networkId).build());
            return this;
        }

        @Override public ContainerCreateBuilder addNetPort(String portId) {
            if (model.nets == null) model.nets = new java.util.ArrayList<>();
            model.nets.add(ZunContainerNets.builder().port(portId).build());
            return this;
        }

        @Override public ContainerCreateBuilder autoRemove(boolean autoRemove) {
            model.autoRemove = autoRemove;
            return this;
        }

        @Override public ContainerCreateBuilder runtime(String runtime) {
            model.runtime = runtime;
            return this;
        }

        @Override public ContainerCreateBuilder hostname(String hostname) {
            model.hostname = hostname;
            return this;
        }

        @Override
        public ContainerCreateBuilder autoHeal(boolean autoHeal) {
            model.autoHeal = autoHeal;
            return this;
        }

        @Override
        public ContainerCreateBuilder availabilityZone(String availabilityZone) {
            model.availabilityZone = availabilityZone;
            return this;
        }

        @Override
        public ContainerCreateBuilder mounts(Mounts mounts) {
            model.mounts = (ZunContainerMounts) mounts;
            return this;
        }

        @Override
        public ContainerCreateBuilder privileged(boolean privileged) {
            model.privileged = privileged;
            return this;
        }

        @Override
        public ContainerCreateBuilder healthcheck(Healthcheck healthcheck) {
            model.healthcheck = (ZunContainerHealthcheck) healthcheck;
            return this;
        }

        @Override
        public ContainerCreateBuilder exposedPorts(Map<String, Object> exposedPorts) {
            model.exposedPorts = exposedPorts;
            return this;
        }

        @Override
        public ContainerCreateBuilder host(String host) {
            model.host = host;
            return this;
        }

        @Override
        public ContainerCreateBuilder entrypoint(List<String> entrypoint) {
            model.entrypoint = entrypoint;
            return this;
        }

        @Override
        public ContainerCreate build() {
            checkNotNull(model.image, "Image must be set");
            return model;
        }

        @Override
        public ContainerCreateBuilder from(ContainerCreate in) {
            model = (ZunContainerCreate) in;
            return this;
        }
    }
}
