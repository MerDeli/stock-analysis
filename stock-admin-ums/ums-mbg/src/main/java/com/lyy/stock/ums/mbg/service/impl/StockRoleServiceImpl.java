package com.lyy.stock.ums.mbg.service.impl;

import com.lyy.stock.ums.mbg.entity.po.StockRole;
import com.lyy.stock.ums.mbg.service.StockRoleService;
import com.lyy.stock.ums.mbg.mapper.StockRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lyy
 * @since 2023-03-21
 */
@Service
public class StockRoleServiceImpl extends ServiceImpl<StockRoleMapper, StockRole> implements StockRoleService {

    @Autowired
    private StockRoleMapper roleMapper;

    @Override
    public List<StockRole> getRoleList(Long userId) {
        return roleMapper.getRoleList(userId);
    }
}
