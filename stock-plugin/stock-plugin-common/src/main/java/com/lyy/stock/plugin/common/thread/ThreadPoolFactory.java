package com.lyy.stock.plugin.common.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author:
 * @createTime: 2023/03/22 15:39:02
 * @version:
 * @Description:
 */
public class ThreadPoolFactory {
    public static ExecutorService getExecutorService(String name) {
        return new ThreadPoolExecutor(2, 6, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1), new NamedThreadFactory(name), new ThreadPoolExecutor.DiscardOldestPolicy());
    }
}
