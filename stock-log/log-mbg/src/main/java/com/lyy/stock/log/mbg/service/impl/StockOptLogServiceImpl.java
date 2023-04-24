package com.lyy.stock.log.mbg.service.impl;

import com.lyy.stock.log.mbg.entity.po.StockOptLog;
import com.lyy.stock.log.mbg.entity.vo.StockOptLogVo;
import com.lyy.stock.log.mbg.mapper.StockOptLogMapper;
import com.lyy.stock.log.mbg.service.StockOptLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
@Slf4j
public class StockOptLogServiceImpl extends ServiceImpl<StockOptLogMapper, StockOptLog> implements StockOptLogService {

    @Resource
    private MongoTemplate mongoTemplate;


    @Override
    public void find(){
        specialFieldQuery();
        andQuery();
        orQuery();
        inQuery();
        likeQuery();
        groupQuery();
        pageQuery();
    }

    // 指定field查询
    public void specialFieldQuery(){
        // 单个条件
        Query query = new Query(Criteria.where("username").is("角色管理")).with(Sort.by("optType"));
        StockOptLog stockOptLog = mongoTemplate.findOne(query, StockOptLog.class);
        log.info("单个条件单个数据：{}",stockOptLog);

        Query query1 = new Query(Criteria.where("username").is("角色管理")).with(Sort.by("optType"));
        List<StockOptLog> stockOptLogs = mongoTemplate.find(query1, StockOptLog.class);
        log.info("单个条件所有数据：{}",stockOptLogs);

    }

    public void andQuery(){
        Query query = new Query(Criteria.where("username").is("角色管理").and("optType").is(1));
        StockOptLog stockOptLog = mongoTemplate.findOne(query, StockOptLog.class);
        log.info("and查询单个数据：{}",stockOptLog);
    }

    public void orQuery(){
        Query query = new Query(Criteria.where("username").is("角色管理").orOperator(Criteria.where("optType").is(1),Criteria.where("optType").is(2)));
        List<StockOptLog> stockOptLogs = mongoTemplate.find(query, StockOptLog.class);
        log.info("or查询多个数据：{}",stockOptLogs);
    }

    public void inQuery(){
        Query query = new Query(Criteria.where("username").in(Arrays.asList("角色管理","ppp")));
        List<StockOptLog> stockOptLogs = mongoTemplate.find(query, StockOptLog.class);
        log.info("in查询多个数据：{}",stockOptLogs);
    }

    // 模糊查询:使用正则
    public void likeQuery(){
        Query query = new Query(Criteria.where("username").regex("角色"));
        List<StockOptLog> stockOptLogs = mongoTemplate.find(query, StockOptLog.class);
        log.info("regex查询多个数据：{}",stockOptLogs);
    }

    // 分组查询
    public void groupQuery(){
        TypedAggregation<StockOptLog> aggregation = Aggregation.newAggregation(StockOptLog.class, Aggregation.match(Criteria.where("username").is("角色管理")), Aggregation.group("username"));
        AggregationResults<Map> aggregate = mongoTemplate.aggregate(aggregation, Map.class);
        log.info("分组查询数据：{}",aggregate.getMappedResults());
    }

    // 分页查询
    public void pageQuery(){
        Query query = Query.query(new Criteria()).skip(5).limit(2);
        List<StockOptLog> stockOptLogs = mongoTemplate.find(query, StockOptLog.class);
        log.info("分页查询数据：{}",stockOptLogs);
    }

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
