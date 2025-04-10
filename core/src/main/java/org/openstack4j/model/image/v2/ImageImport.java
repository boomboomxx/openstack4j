package org.openstack4j.model.image.v2;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.image.v2.builder.ImageImportBuilder;

/**
 * 镜像导入
 *
 * @author xx
 * @date 2025-04-01
 */
public interface ImageImport extends ModelEntity, Buildable<ImageImportBuilder> {
    /**
     * the method of image import
     *
     * @see ImportMethod
     */
    ImportMethod getMethod();

    /**
     * the image id to import
     */
    String getImageId();

    /**
     * A boolean parameter indicating the behavior of the import workflow when an error occurs.
     * When set to True (default),
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;if an error occurs during the upload in at least one store,z
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;the worfklow fails, the data is deleted from stores where copying is done (not staging),
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;and the state of the image is unchanged.
     * <p>
     * When set to False,
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;the workflow will fail (data deleted from stores, …) only if the import fails on all stores specified by the user.
     * <p>
     * In case of a partial success, the locations added to the image will be the stores where the data has been correctly uploaded.
     * <p>
     * Default is True.
     */
    Boolean getAllStoresMustSucceed();

    /**
     * When set to True the data will be imported to the set of stores you may consume from this particular deployment of Glance (ie: the same set of stores returned to a call to /v2/info/stores on the glance-api the request hits).
     * This can’t be used simultaneously with the stores parameter.
     */
    Boolean getAllStores();

    /**
     * If present contains the list of store id to import the image binary data to.
     */
    List<String> getStores();
}
