package com.lyy.stock.ums.mbg.mapper;

import com.lyy.stock.common.mybatis.SuperMapper;
import com.lyy.stock.ums.mbg.entity.po.StockUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lyy
 * @since 2023-03-21
 */
@Mapper
public interface StockUserMapper extends SuperMapper<StockUser> {

}
