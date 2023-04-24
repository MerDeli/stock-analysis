package com.lyy.stock.test.service;

import com.lyy.stock.test.entity.po.StockTest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyy
 * @since 2023-04-21
 */
public interface StockTestService extends IService<StockTest> {

    boolean insertTest();

    boolean updateTest(StockTest stockTest);
}
