package org.openstack4j.model.image.v2.builder;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.image.v2.ImageImport;
import org.openstack4j.model.image.v2.ImportMethod;

/**
 * 镜像导入Builder
 *
 * @author xx
 * @date 2025-04-01
 */
public interface ImageImportBuilder extends Buildable.Builder<ImageImportBuilder, ImageImport> {

    ImageImportBuilder method(ImportMethod method);

    ImageImportBuilder stores(List<String> stores);

    ImageImportBuilder imageId(String imageId);

    ImageImportBuilder allStores(boolean allStores);

    ImageImportBuilder allStoresMustSucceed(boolean allStoresMustSucceed);


}
