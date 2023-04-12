package com.lyy.stock.ums.mbg.mapper;

import com.lyy.stock.common.mybatis.SuperMapper;
import com.lyy.stock.ums.mbg.entity.po.StockMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyy
 * @since 2023-04-12
 */
@Mapper
public interface StockMenuMapper extends SuperMapper<StockMenu> {


    /**
     * 根据后台用户ID获取菜单
     */
    List<StockMenu> getMenuList(@Param("userId") Long userId);
}
