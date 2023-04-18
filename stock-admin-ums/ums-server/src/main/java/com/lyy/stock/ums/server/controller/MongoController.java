package com.lyy.stock.ums.server.controller;

import com.lyy.stock.ums.mbg.entity.po.Emp;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author:
 * @createTime: 2023/04/18 14:46:26
 * @version:
 * @Description:
 */
//todo mongodb的测试接口文件
@RestController
@RequestMapping("mongo")
public class MongoController {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 新增数据
     * @param apple 对象
     */
    @PutMapping("insert")
    public void insert(@RequestBody Emp apple){
        mongoTemplate.insert(apple);
    }

    /**
     * 通过id查询一条数据
     * @param id id
     * @return
     */
    @GetMapping("findById")
    public Emp findById(@RequestParam Long id){
        return mongoTemplate.findById(id,Emp.class);
    }

    /**
     * 查询全部集合数据
     * @return
     */
    @GetMapping("find")
    public List<Emp> find(){
        return mongoTemplate.find(new Query(),Emp.class);
    }

    /**
     * 修改一条数据
     * @param apple 对象
     */
    @PostMapping("update")
    public void update(@RequestBody Emp apple){
        Query query=new Query(Criteria.where("_id").is(apple.getId()));
        Update update= new Update();
        //id是无法修改的因为要先通过id来查询数据，查询完数据以后替换掉除id以外的内容
        //update.set("_id",apple.getId());
        update.set("name",apple.getName());
        mongoTemplate.updateFirst(query,update,Emp.class);
    }

    /**
     * 通过id删除一条数据
     * @param id id
     */
    @DeleteMapping("delete")
    public void delete(@RequestParam Long id){
        Query query=new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,Emp.class);
    }

}
