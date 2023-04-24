package com.lyy.stock.test.service.impl;

import com.lyy.stock.data.common.client.StockDataClient;
import com.lyy.stock.test.entity.po.StockTest;
import com.lyy.stock.test.mapper.StockTestMapper;
import com.lyy.stock.test.service.StockTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyy
 * @since 2023-04-21
 */
@Service
public class StockTestServiceImpl extends ServiceImpl<StockTestMapper, StockTest> implements StockTestService {

    @Autowired
    private StockDataClient stockDataClient;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public boolean insertTest(){
        StockTest stockTest = new StockTest();
        stockTest.setName("测试");
        save(stockTest);

        stockDataClient.insertData("测试过来的","cs");

        int i = 2 / 0;
        return true;
    }

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public boolean updateTest(StockTest stockTest){
        saveOrUpdate(stockTest);

        stockDataClient.insertData("测试过来的","cs");

        int i = 2 / 0;
        return true;
    }
}
