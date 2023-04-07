package com.lyy.stock.ums.mbg.mapper;

import com.lyy.stock.common.mybatis.SuperMapper;
import com.lyy.stock.ums.mbg.entity.po.StockResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 后台资源表 Mapper 接口
 * </p>
 *
 * @author lyy
 * @since 2023-04-07
 */
@Mapper
public interface StockResourceMapper extends SuperMapper<StockResource> {

    List<StockResource>  getResourceList(Long userId);
}
