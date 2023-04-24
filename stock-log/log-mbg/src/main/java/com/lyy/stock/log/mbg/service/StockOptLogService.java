package com.lyy.stock.log.mbg.service;

import com.lyy.stock.log.mbg.entity.po.StockOptLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyy.stock.log.mbg.entity.vo.StockOptLogVo;

/**
 * <p>
 * 系统操作日志表 服务类
 * </p>
 *
 * @author lyy
 * @since 2023-03-27
 */
public interface StockOptLogService extends IService<StockOptLog> {


    /**
     * 联系各种查询
     */
    void find();

    /**
     * 新增
     * @param stockOptLog
     */
    boolean add (StockOptLog stockOptLog);



    /**
     * 更新
     * @param stockOptLog
     */
    Long updateLog(StockOptLog stockOptLog);



    /**
     * 删除
     * @param id
     */
    Long deleteLog(Long id);


    /**
     * 查询某一条详情信息
     * @param id
     */
    StockOptLogVo findById(Long id);

}
