package com.lyy.stock.ums.mbg.service;

import com.lyy.stock.ums.mbg.entity.po.StockMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyy
 * @since 2023-04-12
 */
public interface StockMenuService extends IService<StockMenu> {

    /**
     * 根据管理员ID获取对应菜单
     * @param userId
     * @return
     */
    List<StockMenu> getMenuList(Long userId);
}
