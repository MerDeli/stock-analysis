package com.lyy.stock.plugin.common.future;

public interface FutureCallback<T> {
    T callback() throws Exception;
}
