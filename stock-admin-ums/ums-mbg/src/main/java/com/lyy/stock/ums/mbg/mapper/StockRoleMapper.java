package com.lyy.stock.ums.mbg.mapper;

import com.lyy.stock.common.mybatis.SuperMapper;
import com.lyy.stock.ums.mbg.entity.po.StockMenu;
import com.lyy.stock.ums.mbg.entity.po.StockRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author lyy
 * @since 2023-03-21
 */
@Mapper
public interface StockRoleMapper extends SuperMapper<StockRole> {

    /**
     * 获取用于所有角色
     */
    List<StockRole> getRoleList(@Param("userId") Long userId);
}
