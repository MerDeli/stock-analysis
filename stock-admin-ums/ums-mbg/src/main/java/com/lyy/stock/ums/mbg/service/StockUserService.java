package com.lyy.stock.ums.mbg.service;

import com.lyy.stock.ums.mbg.entity.po.StockUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lyy
 * @since 2023-03-21
 */
public interface StockUserService extends IService<StockUser> {

    StockUser getAdminByUsername(String username);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}
