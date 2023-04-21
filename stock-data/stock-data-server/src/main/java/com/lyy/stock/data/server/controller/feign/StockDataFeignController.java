package com.lyy.stock.data.server.controller.feign;

import com.lyy.stock.data.mbg.entity.po.StockData;
import com.lyy.stock.data.mbg.service.StockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/srv/stockData")
public class StockDataFeignController {

    @Autowired
    private StockDataService stockDataService;

    @GetMapping("/add")
    public Boolean insertData(@RequestParam("name")String name, @RequestParam("code")String code){
        StockData stockData = new StockData();
        stockData.setName(name);
        stockData.setCode(code);
        return stockDataService.save(stockData);
    }
}
