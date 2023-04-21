package com.lyy.stock.test.controller;

import com.lyy.stock.test.service.StockTestService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyy
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/stockTest")
public class StockTestController {

    @Autowired
    private StockTestService stockTestService;


    @GetMapping("/add")
    @GlobalTransactional(rollbackFor = Exception.class)
    public boolean insertTest() {
        return stockTestService.insertTest();
    }


}
