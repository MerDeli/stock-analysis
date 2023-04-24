//package com.lyy.stock.test.handler;
//
//import com.lyy.stock.test.entity.po.StockTest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import top.javatool.canal.client.annotation.CanalTable;
//import top.javatool.canal.client.handler.EntryHandler;
//
///**
// * @Author:
// * @createTime: 2023/04/23 15:26:26
// * @version:
// * @Description:
// */
//@CanalTable("stock_test")
//@Component
//@Slf4j
//public class StockTestHandler implements EntryHandler<StockTest> {
//
//    @Override
//    public void insert(StockTest stockTest) {
//        log.info("stock_test 新增:{}", stockTest);
//    }
//
//    @Override
//    public void update(StockTest before, StockTest after) {
//        log.info("stock_test 修改前:{}", before);
//        log.info("stock_test 修改后:{}", after);
//    }
//
//    @Override
//    public void delete(StockTest stockTest) {
//        log.info("stock_test 删除:{}", stockTest);
//    }
//}
