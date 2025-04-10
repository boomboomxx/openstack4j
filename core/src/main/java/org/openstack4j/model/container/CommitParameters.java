package org.openstack4j.model.container;

import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.container.builder.CommitParametersBuilder;

/**
 * 将容器提交为镜像的参数。
 *
 * @author xx
 * @date 2025-04-10
 */
public interface CommitParameters extends ModelEntity, Buildable<CommitParametersBuilder> {
    String getRepository();

    String getTag();

    Map<String, Object> getQueryParameters();
}
