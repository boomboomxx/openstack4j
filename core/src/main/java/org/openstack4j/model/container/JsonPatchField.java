package org.openstack4j.model.container;

import org.openstack4j.model.ModelEntity;

/**
 * 表示单个 JSON Patch 操作的辅助类。
 *
 * @author xx
 * @date 2025-04-10
 */
public interface JsonPatchField extends ModelEntity {
    String getOp(); // e.g., "replace", "add", "remove"

    String getPath(); // e.g., "/cpu", "/memory", "/name"

    Object getValue(); // Value for "replace" or "add"
}
