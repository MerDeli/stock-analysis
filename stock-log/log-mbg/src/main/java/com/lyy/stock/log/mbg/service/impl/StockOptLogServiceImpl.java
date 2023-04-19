package com.lyy.stock.log.mbg.service.impl;

import com.lyy.stock.log.mbg.entity.po.StockOptLog;
import com.lyy.stock.log.mbg.entity.vo.StockOptLogVo;
import com.lyy.stock.log.mbg.mapper.StockOptLogMapper;
import com.lyy.stock.log.mbg.service.StockOptLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 * 系统操作日志表 服务实现类
 * </p>
 *
 * @author lyy
 * @since 2023-03-27
 */
@Service
public class StockOptLogServiceImpl extends ServiceImpl<StockOptLogMapper, StockOptLog> implements StockOptLogService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public boolean add(StockOptLog stockOptLog) {
        StockOptLog optLog = mongoTemplate.insert(stockOptLog);
        if(Objects.isNull(optLog)){
            return false;
        }
        return true;
    }

    @Override
    public Long updateLog(StockOptLog stockOptLog) {
        Query query=new Query(Criteria.where("_id").is(stockOptLog.getId()));
        Update update= new Update();
        update.set("username",stockOptLog.getUsername());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, StockOptLog.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public Long deleteLog(Long id) {
        Query query=new Query(Criteria.where("_id").is(id));
        DeleteResult deleteResult = mongoTemplate.remove(query, StockOptLog.class);
        return  deleteResult.getDeletedCount();
    }

    @Override
    public StockOptLogVo findById(Long id) {
        return mongoTemplate.findById(id, StockOptLogVo.class);
    }

}
