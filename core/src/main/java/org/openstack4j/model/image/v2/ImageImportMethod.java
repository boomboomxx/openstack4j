package org.openstack4j.model.image.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author xx
 * @date 2025-04-01
 */
public enum ImageImportMethod {
    GLANCE_DIRECT("glance-direct"),
    WEB_DOWNLOAD("web-download"),
    COPY_IMAGE("copy-image"),
    GLANCE_DOWNLOAD("glance-download"),
    ;
    private String name;

    private ImageImportMethod(String name) {
        this.name = name;
    }

    @JsonCreator
    public static ImageImportMethod value(String df) {
        if (df == null || df.isEmpty()) return null;
        try {
            return valueOf(df.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @JsonValue
    public String value() {
        return name;
    }

    public String getName() {
        return name;
    }
}
