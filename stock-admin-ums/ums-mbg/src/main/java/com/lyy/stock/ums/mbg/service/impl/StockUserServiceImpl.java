package com.lyy.stock.ums.mbg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyy.stock.auth.utils.JwtTokenUtil;
import com.lyy.stock.common.core.enumerate.DeleteFlagEnum;
import com.lyy.stock.common.core.exception.StockException;
import com.lyy.stock.ums.mbg.entity.bo.AdminUserDetails;
import com.lyy.stock.ums.mbg.entity.enumerate.user.UserStatusEnum;
import com.lyy.stock.ums.mbg.entity.form.StockUserRegisterForm;
import com.lyy.stock.ums.mbg.entity.param.StockUserQueryParam;
import com.lyy.stock.ums.mbg.entity.po.StockResource;
import com.lyy.stock.ums.mbg.entity.po.StockUser;
import com.lyy.stock.ums.mbg.entity.vo.StockUserRegisterVo;
import com.lyy.stock.ums.mbg.exception.UmsExceptionCode;
import com.lyy.stock.ums.mbg.service.StockResourceService;
import com.lyy.stock.ums.mbg.service.StockUserService;
import com.lyy.stock.ums.mbg.mapper.StockUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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
@Slf4j
public class StockUserServiceImpl extends ServiceImpl<StockUserMapper, StockUser> implements StockUserService {

    @Autowired
    private StockResourceService stockResourceService;

    @Autowired
    private StockUserMapper stockUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public StockUser getUserByUsername(String username) {
        QueryWrapper<StockUser> query = new QueryWrapper<>();
        query.eq("username",username);
        StockUser stockUser = this.getOne(query);
        return stockUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        log.info("loadUserByUsername 入参:{}",username);
        //获取用户信息
        StockUser stockUser = getUserByUsername(username);
        if (stockUser != null) {
            List<StockResource> resourceList = stockResourceService.getResourceList(stockUser.getId());
            log.info("用户:{},获取用户所有可访问资源:{}",username,resourceList);
            return new AdminUserDetails(stockUser,resourceList);
        }
        throw new StockException(UmsExceptionCode.USERNAME_OR_PASSWORD_ERROR);
    }


    @Override
    public StockUserRegisterVo register(StockUserRegisterForm registerForm) {
        log.info("register入参:{}",registerForm);
        //查询是否有相同用户名的用户
        StockUserQueryParam userQueryParam = new StockUserQueryParam();
        userQueryParam.setUsername(registerForm.getUsername());
        userQueryParam.setDeleteFlag(DeleteFlagEnum.NOT_DELETE.getCode());
        List<StockUser> userList = stockUserMapper.selectByUser(userQueryParam);
        if (userList.size() > 0) {
            log.info("用户:{}有相同用户名",registerForm.getUsername());
            return null;
        }
        // 保存注册用户信息
        StockUser stockUser = new StockUser();
        BeanUtils.copyProperties(registerForm, stockUser);
        stockUser.setUserStatus(UserStatusEnum.ENABLE.getCode());
        stockUser.setDeleteFlag(DeleteFlagEnum.NOT_DELETE.getCode());
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(stockUser.getPassword());
        stockUser.setPassword(encodePassword);
        stockUserMapper.insert(stockUser);
        StockUserRegisterVo registerVo = new StockUserRegisterVo();
        BeanUtils.copyProperties(stockUser,registerVo);
        return registerVo;
    }


    @Override
    public String login(String username, String password) {
        log.info("login入参用户名:{},密码:{}",username,password);
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            //todo 添加登录日志
//            insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }



    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }
}
