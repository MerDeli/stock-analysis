package com.lyy.stock.ums.mbg.service.impl;

import com.lyy.stock.ums.mbg.entity.po.StockMenu;
import com.lyy.stock.ums.mbg.mapper.StockMenuMapper;
import com.lyy.stock.ums.mbg.service.StockMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyy
 * @since 2023-04-12
 */
@Service
public class StockMenuServiceImpl extends ServiceImpl<StockMenuMapper, StockMenu> implements StockMenuService {

    @Autowired
    private StockMenuMapper stockMenuMapper;


    @Override
    public List<StockMenu> getMenuList(Long userId) {
        return stockMenuMapper.getMenuList(userId);
    }
}
