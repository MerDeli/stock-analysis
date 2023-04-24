package com.lyy.stock.test.controller;

import com.lyy.stock.test.entity.po.StockTest;
import com.lyy.stock.test.service.StockTestService;
import com.lyy.stock.test.service.impl.ReloadDroolsRules;
import com.lyy.stock.test.service.impl.RuleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.KieUtils;

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

    @Autowired
    private RuleEngineService ruleEngineService;

    @Autowired
    private ReloadDroolsRules reloadDroolsRules;


    @GetMapping("/add")
    public boolean insertTest() {
        return stockTestService.insertTest();
    }


    @PutMapping("/update")
    public boolean updateTest(@RequestBody StockTest stockTest) {
        return stockTestService.updateTest(stockTest);
    }

    @RequestMapping("/param")
    public void param (){
        StockTest queryParam1 = new StockTest() ;
        queryParam1.setId(1L);
        queryParam1.setName("赵");
        StockTest queryParam2 = new StockTest() ;
        queryParam2.setId(2L);
        queryParam2.setName("张");
        StockTest queryParam3 = new StockTest() ;
        queryParam3.setId(3L);
        queryParam3.setName("李");
        // 入参
        KieUtils.getKieSession().insert(queryParam2) ;
        KieUtils.getKieSession().insert(queryParam3) ;
        KieUtils.getKieSession().insert(queryParam1) ;
        KieUtils.getKieSession().insert(this.ruleEngineService) ;
        // 返参
        KieUtils.getKieSession().fireAllRules() ;
    }

    @RequestMapping("/reload")
    public String reload (String ruleName) throws Exception {
        // 返参
        reloadDroolsRules.reload(ruleName);
        return "新规则重载成功";
    }
}
