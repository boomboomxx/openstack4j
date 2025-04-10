package org.openstack4j.openstack.image.v2.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.image.v2.ImageImport;
import org.openstack4j.model.image.v2.ImportMethod;
import org.openstack4j.model.image.v2.builder.ImageImportBuilder;

/**
 * @author xx
 * @date 2025-04-01
 */
public class GlanceImageImport implements ImageImport {
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setMethod(ImportMethod method) {
        this.method = method;
    }

    public void setAllStores(Boolean allStores) {
        this.allStores = allStores;
    }

    public void setAllStoresMustSucceed(Boolean allStoresMustSucceed) {
        this.allStoresMustSucceed = allStoresMustSucceed;
    }

    public void setStores(List<String> stores) {
        this.stores = stores;
    }

    @JsonProperty("image_id")
    private String imageId;
    @JsonProperty("method")
    private ImportMethod method;

    @JsonProperty("all_stores")
    private Boolean allStores;
    @JsonProperty("all_stores_must_succeed")
    private Boolean allStoresMustSucceed;

    @JsonProperty("stores")
    private List<String> stores;


    @Override
    public ImportMethod getMethod() {
        return method;
    }

    @Override
    public String getImageId() {
        return imageId;
    }

    @Override
    public Boolean getAllStoresMustSucceed() {
        return allStoresMustSucceed;
    }

    @Override
    public Boolean getAllStores() {
        return allStores;
    }

    @Override
    public List<String> getStores() {
        return stores;
    }

    @Override
    public ImageImportBuilder toBuilder() {
        return new ImageImportConcreteBuilder(this);
    }

    public static ImageImportBuilder builder() {
        return new ImageImportConcreteBuilder();
    }

    public static class ImageImportConcreteBuilder implements ImageImportBuilder {
        private GlanceImageImport m;


        public ImageImportConcreteBuilder(GlanceImageImport m) {
            this.m = m;
        }

        public ImageImportConcreteBuilder() {
            this.m = new GlanceImageImport();
        }

        @Override
        public ImageImportBuilder method(ImportMethod method) {
            m.setMethod(method);
            return this;
        }

        @Override
        public ImageImportBuilder stores(List<String> stores) {
            m.setStores(stores);
            return this;
        }

        @Override
        public ImageImportBuilder imageId(String imageId) {
            m.setImageId(imageId);
            return this;
        }

        @Override
        public ImageImportBuilder allStores(boolean allStores) {
            m.setAllStores(allStores);
            return this;
        }

        @Override
        public ImageImportBuilder allStoresMustSucceed(boolean allStoresMustSucceed) {
            m.setAllStoresMustSucceed(allStoresMustSucceed);
            return this;
        }

        @Override
        public ImageImport build() {
            return m;
        }

        @Override
        public ImageImportBuilder from(ImageImport in) {
            m = (GlanceImageImport) in;
            return this;
        }
    }
}
