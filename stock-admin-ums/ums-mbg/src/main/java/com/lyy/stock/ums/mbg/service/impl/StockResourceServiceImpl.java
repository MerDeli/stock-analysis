package com.lyy.stock.ums.mbg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyy.stock.common.core.enumerate.DeleteFlagEnum;
import com.lyy.stock.ums.mbg.entity.po.StockResource;
import com.lyy.stock.ums.mbg.entity.po.StockUser;
import com.lyy.stock.ums.mbg.mapper.StockResourceMapper;
import com.lyy.stock.ums.mbg.mapper.StockUserRoleRelMapper;
import com.lyy.stock.ums.mbg.service.StockResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台资源表 服务实现类
 * </p>
 *
 * @author lyy
 * @since 2023-04-07
 */
@Service
public class StockResourceServiceImpl extends ServiceImpl<StockResourceMapper, StockResource> implements StockResourceService {

    @Autowired
    private StockResourceMapper resourceMapper;

    @Override
    public List<StockResource> listAll() {
        QueryWrapper<StockResource> query = new QueryWrapper<>();
        query.eq("delete_flag", DeleteFlagEnum.NOT_DELETE.getCode());
        return this.list(query);
    }



    @Override
    public List<StockResource> getResourceList(Long userId) {
        return resourceMapper.getResourceList(userId);
    }
}
