package com.lyy.stock.ums.mbg.service;

import com.lyy.stock.ums.mbg.entity.form.StockUserRegisterForm;
import com.lyy.stock.ums.mbg.entity.po.StockUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyy.stock.ums.mbg.entity.vo.StockUserRegisterVo;
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


    /**
     * 注册用户
     * @param stockUserRegisterForm
     * @return
     */
    StockUserRegisterVo register(StockUserRegisterForm stockUserRegisterForm);


    /**
     * 用户登录，获取token
     */
    String login(String username, String password);


    /**
     * 刷新token
     * @return
     */
    String refreshToken(String token);
}
