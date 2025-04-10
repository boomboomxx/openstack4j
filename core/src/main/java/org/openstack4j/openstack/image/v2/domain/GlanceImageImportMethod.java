package org.openstack4j.openstack.image.v2.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.image.v2.ImageImportMethod;
import org.openstack4j.model.image.v2.ImportMethod;
import org.openstack4j.model.image.v2.builder.ImageImportMethodBuilder;

/**
 * @author xx
 * @date 2025-04-01
 */
public class GlanceImageImportMethod implements ImportMethod {
    private String name;
    private String uri;
    @JsonProperty("glance_image_id")
    private String glanceImageId;
    @JsonProperty("glance_region")
    private String glanceRegion;
    @JsonProperty("glance_service_interface")
    private String glanceServiceInterface;

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public String getGlanceImageId() {
        return glanceImageId;
    }

    @Override
    public String getGlanceRegion() {
        return glanceRegion;
    }

    @Override
    public String getGlanceServiceInterface() {
        return glanceServiceInterface;
    }

    @Override
    public ImageImportMethodBuilder toBuilder() {
        return new GlanceImageImportMethodConcreteBuilder(this);
    }

    public static ImageImportMethodBuilder builder() {
        return new GlanceImageImportMethodConcreteBuilder();
    }

    @Override
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setGlanceImageId(String glanceImageId) {
        this.glanceImageId = glanceImageId;
    }

    public void setGlanceRegion(String glanceRegion) {
        this.glanceRegion = glanceRegion;
    }

    public void setGlanceServiceInterface(String glanceServiceInterface) {
        this.glanceServiceInterface = glanceServiceInterface;
    }

    public static class GlanceImageImportMethodConcreteBuilder implements ImageImportMethodBuilder {
        private GlanceImageImportMethod m;

        public GlanceImageImportMethodConcreteBuilder(GlanceImageImportMethod method) {
            this.m = method;
        }

        public GlanceImageImportMethodConcreteBuilder() {
            this.m = new GlanceImageImportMethod();
        }


        @Override
        public ImageImportMethodBuilder name(ImageImportMethod method) {
            m.setName(method.getName());
            return this;
        }

        @Override
        public ImageImportMethodBuilder uri(String uri) {
            m.setUri(uri);
            return this;
        }

        @Override
        public ImageImportMethodBuilder glanceImageId(String glanceImageId) {
            m.setGlanceImageId(glanceImageId);
            return this;
        }

        @Override
        public ImageImportMethodBuilder glanceRegion(String glanceRegion) {
            m.setGlanceRegion(glanceRegion);
            return this;
        }

        @Override
        public ImageImportMethodBuilder glanceServiceInterface(String glanceServiceInterface) {
            m.setGlanceServiceInterface(glanceServiceInterface);
            return this;
        }

        @Override
        public ImportMethod build() {
            return m;
        }

        @Override
        public ImageImportMethodBuilder from(ImportMethod in) {
            this.m = (GlanceImageImportMethod) in;
            return this;
        }
    }
}
