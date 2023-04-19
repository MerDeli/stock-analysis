package com.lyy.stock.log.mbg.mapper;

import com.lyy.stock.log.mbg.entity.po.StockOptLog;
import com.lyy.stock.common.mybatis.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统操作日志表 Mapper 接口
 * </p>
 *
 * @author lyy
 * @since 2023-03-27
 */
@Mapper
public interface StockOptLogMapper extends SuperMapper<StockOptLog> {

}
