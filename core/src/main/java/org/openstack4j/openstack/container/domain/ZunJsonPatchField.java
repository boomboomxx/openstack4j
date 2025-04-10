package org.openstack4j.openstack.container.domain;

import org.openstack4j.model.container.JsonPatchField;

/**
 * JSON Patch 字段的具体实现。
 *
 * @author xx
 * @date 2025-04-10
 */
public class ZunJsonPatchField implements JsonPatchField {
    String op;
    String path;
    Object value; // Can be null for 'remove'

    ZunJsonPatchField(String op, String path, Object value) {
        this.op = op;
        this.path = path;
        this.value = value;
    }

    @Override public String getOp() {
        return op;
    }

    @Override public String getPath() {
        return path;
    }

    @Override public Object getValue() {
        return value;
    }

    public static ZunJsonPatchField replace(String path, Object value) {
        return new ZunJsonPatchField("replace", path, value);
    }

    public static ZunJsonPatchField add(String path, Object value) {
        return new ZunJsonPatchField("add", path, value);
    }

    public static ZunJsonPatchField remove(String path) {
        return new ZunJsonPatchField("remove", path, null);
    }
}
