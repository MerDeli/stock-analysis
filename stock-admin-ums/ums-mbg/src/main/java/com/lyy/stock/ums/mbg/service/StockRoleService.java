package com.lyy.stock.ums.mbg.service;

import com.lyy.stock.ums.mbg.entity.po.StockMenu;
import com.lyy.stock.ums.mbg.entity.po.StockRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author lyy
 * @since 2023-03-21
 */
public interface StockRoleService extends IService<StockRole> {

    /**
     * 获取用户对于角色
     */
    List<StockRole> getRoleList(Long userId);
}
