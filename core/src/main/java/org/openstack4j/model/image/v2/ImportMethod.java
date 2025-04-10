package org.openstack4j.model.image.v2;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.image.v2.builder.ImageImportMethodBuilder;

/**
 * @author xx
 * @date 2025-04-01
 */
public interface ImportMethod extends ModelEntity, Buildable<ImageImportMethodBuilder> {

    /**
     * @return the name of the import method
     * @see ImageImportMethod#getName()
     */
    String getName();

    /**
     * if ImportMethod#getName() is "url", then ImportMethod#getUri() is the url of the image.
     *
     * @return the uri of the image
     */
    String getUri();

    /**
     * if ImportMethod#getName() is "glance-download", then ImportMethod#getGlanceImageId() is the id of the image.
     *
     * @return the id of the image
     */
    String getGlanceImageId();

    /**
     * if ImportMethod#getName() is "glance-download", then ImportMethod#getGlanceRegion() is the region of the image.
     *
     * @return the region of the image
     */
    String getGlanceRegion();

    /**
     * if ImportMethod#getName() is "glance-download", then ImportMethod#getGlanceServiceInterface() is the service interface of the image.
     *
     * @return the service interface of the image
     */
    String getGlanceServiceInterface();


}
