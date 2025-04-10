package org.openstack4j.model.image.v2.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.image.v2.ImageImportMethod;
import org.openstack4j.model.image.v2.ImportMethod;

/**
 * @author xx
 * @date 2025-04-01
 */
public interface ImageImportMethodBuilder extends Buildable.Builder<ImageImportMethodBuilder, ImportMethod> {

    /**
     * @see ImportMethod#getName()
     */
    ImageImportMethodBuilder name(ImageImportMethod method);

    /**
     * @see ImportMethod#getUri()
     */
    ImageImportMethodBuilder uri(String uri);

    /**
     * @see ImportMethod#getGlanceImageId()
     */
    ImageImportMethodBuilder glanceImageId(String glanceImageId);

    /**
     * @see ImportMethod#getGlanceRegion()
     */
    ImageImportMethodBuilder glanceRegion(String glanceRegion);

    /**
     * @see ImportMethod#getGlanceServiceInterface()
     */
    ImageImportMethodBuilder glanceServiceInterface(String glanceServiceInterface);

}
