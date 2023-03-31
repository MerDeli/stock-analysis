package com.lyy.stock.ums.server.controller;

import com.lyy.stock.ums.mbg.entity.StockRole;
import com.lyy.stock.ums.mbg.service.StockRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author lyy
 * @since 2023-03-21
 */
@RestController
@RequestMapping("/stockRole")
public class StockRoleController {

    @Autowired
    private StockRoleService stockRoleService;

    @GetMapping
    public List<StockRole> test(){
        return stockRoleService.list();
    }
}
