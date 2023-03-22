package com.lyy.stock.plugin.common.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @Author:
 * @createTime: 2023/03/22 15:49:39
 * @version:
 * @Description:
 */
public class FutureResolver {
    public static <T> T call(ExecutorService executorService, FutureCallback<T> discoveryFutureCallback) throws InterruptedException, ExecutionException {
        Future<T> future = executorService.submit(discoveryFutureCallback::callback);
        return future.get();
    }
}
