package org.openstack4j.openstack.internal;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.ActionResponse;

/**
 * Base class for OpenStack services which use micro-versions.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public abstract class MicroVersionedOpenStackService extends BaseOpenStackService {
    private final MicroVersion microVersion;
    // map to store the micro-versions for each service type
    private final Map<ServiceType, MicroVersion> serviceMicroVersions = new ConcurrentHashMap<>();


    protected MicroVersionedOpenStackService(MicroVersion microVersion) {
        this.microVersion = microVersion;
    }

    protected MicroVersionedOpenStackService(ServiceType serviceType, MicroVersion microVersion) {
        super(serviceType);
        this.microVersion = microVersion;
    }

    protected MicroVersionedOpenStackService(ServiceType serviceType, MicroVersion microVersion,
            Function<String, String> endpointFunc) {
        super(serviceType, endpointFunc);
        this.microVersion = microVersion;
    }

    protected MicroVersion getMicroVersion() {
        return microVersion;
    }

    protected String getApiVersionStr() {
        MicroVersion mc = getMicroServiceCurrentVersion();
        return String.format("%s %s", ServiceType.CONTAINER.getType(), Optional.ofNullable(mc).orElse(getMicroVersion()).toString());
    }

    protected abstract String getApiVersionHeader();

    @Override
    protected <R> Invocation<R> get(Class<R> returnType, String... path) {
        return super.get(returnType, path).header(getApiVersionHeader(), getApiVersionStr());
    }

    /**
     * get the micro-versions from the service endpoint without version info (e.g. /v2.0/).
     *
     * @return micro-version from service
     */
    Invocation<MicroVersions> getWithMicroVersion() {
        OSClientSession<?, ?> ses = OSClientSession.getCurrent();
        Objects.requireNonNull(ses, "Unable to retrieve current session. Please verify thread has a current session available.");
        String endpoint = ses.getEndpoint(getServiceType()).replaceAll("/v\\d.*", "");
        HttpRequest.RequestBuilder<MicroVersions> req = HttpRequest.builder(MicroVersions.class).endpoint(endpoint).method(HttpMethod.GET).path("/");
        Map<String, String> headers = ses.getHeaders();
        if (headers != null && !headers.isEmpty()) {
            return new Invocation<>(req, getServiceType(), null).headers(headers);
        } else {
            return new Invocation<>(req, getServiceType(), null);
        }
    }

    protected MicroVersion getMicroServiceCurrentVersion() {
        ServiceType serviceType = getServiceType();
        if (!getServiceType().isMicroVersionService()) return null;
        if (serviceMicroVersions.containsKey(serviceType)) return serviceMicroVersions.get(serviceType);

        MicroVersions res = getWithMicroVersion().execute();
        if (res == null || res.getVersions() == null || res.getVersions().isEmpty()) return null;

        MicroVersion microVersion;
        if (res.getDefaultVersion() != null) {
            microVersion = new MicroVersion(res.getDefaultVersion().getMaxVersion());
        } else {
            microVersion = res.getVersions()
                    .stream()
                    .filter(v -> v.getStatus().equals(MicroVersionStatus.CURRENT))
                    .findFirst()
                    .map(MicroVersionInfo::getMaxVersion)
                    .map(MicroVersion::new)
                    .orElse(null);
        }
        serviceMicroVersions.put(serviceType, microVersion);
        return microVersion;
    }


    @Override
    protected <R> Invocation<R> post(Class<R> returnType, String... path) {
        return super.post(returnType, path).header(getApiVersionHeader(), getApiVersionStr());
    }

    @Override
    protected <R> Invocation<R> put(Class<R> returnType, String... path) {
        return super.put(returnType, path).header(getApiVersionHeader(), getApiVersionStr());
    }

    @Override
    protected <R> Invocation<R> patch(Class<R> returnType, String... path) {
        return super.patch(returnType, path).header(getApiVersionHeader(), getApiVersionStr());
    }

    @Override
    protected <R> Invocation<R> delete(Class<R> returnType, String... path) {
        return super.delete(returnType, path).header(getApiVersionHeader(), getApiVersionStr());
    }

    @Override
    protected Invocation<ActionResponse> deleteWithResponse(String... path) {
        return super.deleteWithResponse(path).header(getApiVersionHeader(), getApiVersionStr());
    }

    @Override
    protected <R> Invocation<R> head(Class<R> returnType, String... path) {
        return super.head(returnType, path).header(getApiVersionHeader(), getApiVersionStr());
    }

    @Override
    protected <R> Invocation<R> request(HttpMethod method, Class<R> returnType, String path) {
        return super.request(method, returnType, path).header(getApiVersionHeader(), getApiVersionStr());
    }

    public static class MicroVersions implements ModelEntity {
        private String name;
        private String description;
        private List<MicroVersionInfo> versions;
        @JsonProperty("default_version") private MicroVersionInfo defaultVersion;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<MicroVersionInfo> getVersions() {
            return versions;
        }

        public void setVersions(List<MicroVersionInfo> versions) {
            this.versions = versions;
        }

        public MicroVersionInfo getDefaultVersion() {
            return defaultVersion;
        }

        public void setDefaultVersion(MicroVersionInfo defaultVersion) {
            this.defaultVersion = defaultVersion;
        }
    }

    public static class MicroVersionInfo implements ModelEntity {
        private String id;
        private MicroVersionStatus status;
        private List<MicroVersionLink> links;
        @JsonProperty("max_version") private String maxVersion;
        @JsonProperty("min_version") private String minVersion;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public MicroVersionStatus getStatus() {
            return status;
        }

        public void setStatus(MicroVersionStatus status) {
            this.status = status;
        }

        public List<MicroVersionLink> getLinks() {
            return links;
        }

        public void setLinks(List<MicroVersionLink> links) {
            this.links = links;
        }

        public String getMaxVersion() {
            return maxVersion;
        }

        public void setMaxVersion(String maxVersion) {
            this.maxVersion = maxVersion;
        }

        public String getMinVersion() {
            return minVersion;
        }

        public void setMinVersion(String minVersion) {
            this.minVersion = minVersion;
        }

    }

    public static class MicroVersionLink implements ModelEntity {
        private String href;
        private String rel;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getRel() {
            return rel;
        }

        public void setRel(String rel) {
            this.rel = rel;
        }
    }

    public enum MicroVersionStatus {
        CURRENT,
        DEPRECATED,
        EXPERIMENTAL,
        STABLE,
        SUPPORTED,
        UNKNOWN;

        public static MicroVersionStatus fromValue(String value) {
            if (value == null) {
                return UNKNOWN;
            }
            for (MicroVersionStatus status : values()) {
                if (status.name().equalsIgnoreCase(value)) {
                    return status;
                }
            }
            return UNKNOWN;
        }

        public String toValue() {
            return name();
        }
    }
}
