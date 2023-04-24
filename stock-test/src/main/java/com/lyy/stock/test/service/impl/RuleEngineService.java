package com.lyy.stock.test.service.impl;

import com.lyy.stock.test.entity.po.StockTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author:
 * @createTime: 2023/04/24 10:09:07
 * @version:
 * @Description:
 */
@Slf4j
@Service
public class RuleEngineService {

    /**
     * 插入规则
     *
     * @param param
     */
    public void executeAddRule(StockTest param) {
        log.info("参数数据:" + param.getId() + ";" + param.getName());
        log.info("插入规则");
    }

    /**
     * 移除规则
     *
     * @param param
     */
    public void executeRemoveRule(StockTest param) {
        log.info("参数数据:" + param.getId() + ";" + param.getName());
        log.info("移除规则");
    }
}
