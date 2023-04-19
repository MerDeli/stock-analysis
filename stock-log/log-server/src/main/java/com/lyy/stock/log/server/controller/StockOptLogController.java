package com.lyy.stock.log.server.controller;

import com.lyy.stock.common.core.api.ResponseData;
import com.lyy.stock.log.mbg.entity.form.StockOptLogForm;
import com.lyy.stock.log.mbg.entity.form.StockOptLogQueryForm;
import com.lyy.stock.log.mbg.entity.form.StockOptLogUpdateForm;
import com.lyy.stock.log.mbg.entity.vo.StockOptLogVo;
import com.lyy.stock.log.mbg.service.StockOptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统操作日志表 前端控制器
 * </p>
 *
 * @author lyy
 * @since 2023-03-27
 */
@RestController
@RequestMapping("/stockOptLog")
public class StockOptLogController {

    @Autowired
    private StockOptLogService stockOptService;

//    /**
//     * 分页查询
//     * @param queryForm 分页查询对象
//     * @return
//     */
//    @PostMapping("/page")
//    public StockOptLogForm findById(@RequestBody StockOptLogQueryForm queryForm){
//
//    }

    /**
     * 新增数据
     * @param logForm 新增对象
     */
    @PostMapping
    public ResponseData<Boolean> add(@RequestBody StockOptLogForm logForm){
        return ResponseData.success(stockOptService.add(logForm));
    }


    /**
     * 修改数据
     * @param logForm 修改对象
     */
    @PutMapping
    public ResponseData<Long> add(@RequestBody StockOptLogUpdateForm logForm){
        return ResponseData.success(stockOptService.updateLog(logForm));
    }


    /**
     * 删除数据
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseData<Long> delete(@PathVariable Long id){
        return ResponseData.success(stockOptService.deleteLog(id));
    }


    /**
     * 通过id查询一条数据
     * @param id id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseData<StockOptLogVo> findById(@PathVariable Long id){
        return ResponseData.success(stockOptService.findById(id));
    }

}
