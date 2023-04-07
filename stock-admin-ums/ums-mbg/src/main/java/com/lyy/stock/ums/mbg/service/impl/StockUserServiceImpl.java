package com.lyy.stock.ums.mbg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyy.stock.common.core.exception.StockException;
import com.lyy.stock.ums.mbg.entity.bo.AdminUserDetails;
import com.lyy.stock.ums.mbg.entity.po.StockResource;
import com.lyy.stock.ums.mbg.entity.po.StockUser;
import com.lyy.stock.ums.mbg.service.StockResourceService;
import com.lyy.stock.ums.mbg.service.StockUserService;
import com.lyy.stock.ums.mbg.mapper.StockUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lyy
 * @since 2023-03-21
 */
@Service
public class StockUserServiceImpl extends ServiceImpl<StockUserMapper, StockUser> implements StockUserService {

    @Autowired
    private StockResourceService stockResourceService;

    @Override
    public StockUser getAdminByUsername(String username) {
        QueryWrapper<StockUser> query = new QueryWrapper<>();
        query.eq("username",username);
        query.eq("delete_flag",1);
        StockUser stockUser = this.getOne(query);
        return stockUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        StockUser stockUser = getAdminByUsername(username);
        if (stockUser != null) {
            List<StockResource> resourceList = stockResourceService.getResourceList(stockUser.getId());
            return new AdminUserDetails(stockUser,resourceList);
        }
        throw new StockException(600,"用户名或密码错误");
    }
}
