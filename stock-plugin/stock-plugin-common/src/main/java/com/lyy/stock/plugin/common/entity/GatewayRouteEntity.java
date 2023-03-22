package com.lyy.stock.plugin.common.entity;

import java.io.Serializable;
import java.util.*;

/**
 * @Author:
 * @createTime: 2023/03/22 15:41:44
 * @version:
 * @Description:
 */
public class GatewayRouteEntity implements Serializable {
    private static final long serialVersionUID = 8753685181409614365L;

    private String id;
    private String uri;
    private List<String> predicates = new ArrayList<>();
    private List<String> filters = new ArrayList<>();
    private int order = 0;
    private Map<String, Object> metadata = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<String> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<String> predicates) {
        this.predicates = predicates;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        if (metadata != null) {
            this.metadata = metadata;
        }
    }

    public static class Predicate extends Clause {
        private static final long serialVersionUID = -5020870935530926132L;
    }

    public static class Filter extends Clause {
        private static final long serialVersionUID = 1537210609621458021L;
    }

    public static class Clause implements Serializable {
        private static final long serialVersionUID = 1478260933880659272L;
        private String name;
        private Map<String, String> args = new LinkedHashMap<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, String> getArgs() {
            return args;
        }

        public void setArgs(Map<String, String> args) {
            this.args = args;
        }

        public void addArg(String key, String value) {
            this.args.put(key, value);
        }
    }
}
