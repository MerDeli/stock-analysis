package com.lyy.stock.test.controller;

import com.lyy.stock.test.entity.po.StockTest;
import com.lyy.stock.test.service.StockTestService;
import com.lyy.stock.test.service.impl.ReloadDroolsRules;
import com.lyy.stock.test.service.impl.RuleEngineService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.KieUtils;

import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyy
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/stockTest")
@Slf4j
public class StockTestController {

    @Autowired
    private StockTestService stockTestService;

    @Autowired
    private RuleEngineService ruleEngineService;

    @Autowired
    private ReloadDroolsRules reloadDroolsRules;


    @GetMapping("/add")
    public boolean insertTest() {
        return stockTestService.insertTest();
    }


    @PutMapping("/update")
    public boolean updateTest(@RequestBody StockTest stockTest) {
        return stockTestService.updateTest(stockTest);
    }

    @RequestMapping("/param")
    public void param (){
        StockTest queryParam1 = new StockTest() ;
        queryParam1.setId(1L);
        queryParam1.setName("赵");
        StockTest queryParam2 = new StockTest() ;
        queryParam2.setId(2L);
        queryParam2.setName("张");
        StockTest queryParam3 = new StockTest() ;
        queryParam3.setId(3L);
        queryParam3.setName("李");
        // 入参
        KieUtils.getKieSession().insert(queryParam1) ;
        KieUtils.getKieSession().insert(queryParam2) ;
        KieUtils.getKieSession().insert(queryParam3) ;
        KieUtils.getKieSession().insert(this.ruleEngineService) ;
        // 返参
//        KieUtils.getKieSession().getAgenda().getAgendaGroup("test-group3").setFocus();
//        KieUtils.getKieSession().setGlobal("count",100);
//        ArrayList<String> list = new ArrayList<>();
//        KieUtils.getKieSession().setGlobal("gList",list);
        KieUtils.getKieSession().fireAllRules() ;
        log.info("id by queryParam3:{}",queryParam3.getId());
//        KieServices ks = KieServices.Factory.get();
//        KieContainer kc = ks.getKieClasspathContainer();
//        KieSession kieSession = kc.newKieSession();
//        QueryResults queryResults = KieUtils.getKieSession().getQueryResults("query-1");
//        queryResults.iterator().forEachRemaining(row->{
//            StockTest s = (StockTest)row.get("$u");
//            log.info("row:{}",s.getName());
//        });
    }

    @RequestMapping("/query")
    public void query (){
        StockTest queryParam1 = new StockTest() ;
        queryParam1.setId(1L);
        queryParam1.setName("赵");
        StockTest queryParam2 = new StockTest() ;
        queryParam2.setId(2L);
        queryParam2.setName("张");
        StockTest queryParam3 = new StockTest() ;
        queryParam3.setId(3L);
        queryParam3.setName("李");


        //1.获取一个KieServices
        KieServices kieServices = KieServices.Factory.get();
        //2.创建kiemodule xml对应的class
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
        //3.创建KieFileSystem虚拟文件系统
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        //4.添加具体的KieBase标签
        KieBaseModel kieBaseModel = kieModuleModel.newKieBaseModel("kbase").
                addPackage("rules");//kie fileSystem 中资源文件的文件夹
        //<KieBase></KieBase>标签添加KieSession属性
        kieBaseModel.newKieSessionModel("kiession");//a
        //5.添加kiemodule.xml文件到虚拟文件系统
        String kieModuleModelXml = kieModuleModel.toXML();
        kieFileSystem.writeKModuleXML(kieModuleModelXml);//kieModuleModel
        //6.把规则文件加载到虚拟文件系统
        Resource resource = ResourceFactory.newClassPathResource("rules/rule01.drl", "UTF-8");
        //这里是把规则文件添加到虚拟系统，第一个参数是文件在虚拟系统中的路径
        kieFileSystem.write(resource);
        //7.构建所有的KieBase并把所有的KieBase添加到仓库里
        kieServices.newKieBuilder(kieFileSystem).buildAll();
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());//创建kie容器
        //8.从容器中获取一个会话，这里和a处添加的是一个key，否则找不到 找不到任何一个会报异常
        KieSession kieSession = kieContainer.newKieSession("kiession");

        kieSession.insert(queryParam1);
        kieSession.insert(queryParam2);
        kieSession.insert(queryParam3);

//        QueryResults queryResults = kieSession.getQueryResults("query-1");
        QueryResults queryResults = kieSession.getQueryResults("query-2","张");
        queryResults.iterator().forEachRemaining(row->{
            StockTest s = (StockTest)row.get("$u");
            log.info("row:{}",s.getName());
        });
    }


    @RequestMapping("/timer")
    public void timer () throws InterruptedException {
        // 返参
        new Thread(new Runnable() {
            @Override
            public void run() {
                //启动规则引擎进行规则匹配，直到调用halt方法才结束规则引擎
                KieUtils.getKieSession().fireUntilHalt();
            }
        }).start();
        Thread.sleep(10000);
        //结束规则引擎
        KieUtils.getKieSession().halt();
    }

    @RequestMapping("/reload")
    public String reload (String ruleName) throws Exception {
        // 返参
        reloadDroolsRules.reload(ruleName);
        return "新规则重载成功";
    }
}
