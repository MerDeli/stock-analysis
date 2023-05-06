package com.lyy.stock.flowable.mbg.service;

import java.io.InputStream;
import java.util.List;

/**
 * @Author:
 * @createTime: 2023/05/04 18:16:10
 * @version:
 * @Description:
 */
public interface FlowableService {

    void deployment(String name, InputStream in);

    List<String> list();
}
