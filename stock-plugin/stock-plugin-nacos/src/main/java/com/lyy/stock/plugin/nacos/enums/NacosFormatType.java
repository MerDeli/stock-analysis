package com.lyy.stock.plugin.nacos.enums;

public enum NacosFormatType {
    XML_FORMAT("xml"),
    JSON_FORMAT("json"),
    YAML_FORMAT("yaml"),
    PROPERTIES_FORMAT("properties"),
    HTML_FORMAT("html"),
    TEXT_FORMAT("text");

    private String value;

    NacosFormatType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static NacosFormatType toFormatType(String value) {
        for (NacosFormatType type : NacosFormatType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}
