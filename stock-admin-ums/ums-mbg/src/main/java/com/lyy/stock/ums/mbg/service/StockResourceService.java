package com.lyy.stock.ums.mbg.service;

import com.lyy.stock.ums.mbg.entity.po.StockResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台资源表 服务类
 * </p>
 *
 * @author lyy
 * @since 2023-04-07
 */
public interface StockResourceService extends IService<StockResource> {

    /**
     * 查询全部资源
     */
    List<StockResource> listAll();


    /**
     * 获取用户所有可访问资源
     */
    List<StockResource> getResourceList(Long userId);
}
