package com.lyy.stock.flowable.server;

import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class StockFlowableServerApplicationTests {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    StockFlowableServerApplicationTests() {
    }

    @Test
    void contextLoads() {
        // 发起请假
        Map<String, Object> map = new HashMap<>();
        map.put("day", 2);
        map.put("studentUser", "小明");
        ProcessInstance studentLeave = runtimeService.startProcessInstanceByKey("StudentLeave", map);
        Task task = taskService.createTaskQuery().processInstanceId(studentLeave.getId()).singleResult();
        taskService.complete(task.getId());

        // 老师审批
        List<Task> teacherTaskList = taskService.createTaskQuery().taskCandidateGroup("teacher").list();
        Map<String, Object> teacherMap = new HashMap<>();
        teacherMap.put("outcome", "通过");
        for (Task teacherTask : teacherTaskList) {
            taskService.complete(teacherTask.getId(), teacherMap);
        }

        // 校长审批
        List<Task> principalTaskList = taskService.createTaskQuery().taskCandidateGroup("principal").list();
        Map<String, Object> principalMap = new HashMap<>();
        principalMap.put("outcome", "通过");
        for (Task principalTask : principalTaskList) {
            taskService.complete(principalTask.getId(), principalMap);
        }

        // 查看历史
        List<HistoricActivityInstance> activities = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(studentLeave.getId())
                .finished()
                .orderByHistoricActivityInstanceEndTime().asc()
                .list();
        for (HistoricActivityInstance activity : activities) {
            System.out.println(activity.getActivityName());
        }
    }


    @Test
    void test01(){
        Authentication.setAuthenticatedUserId("lisi");
        HashMap<String, Object> map = new HashMap<>();
        map.put("bxUser","张三");
        ProcessInstance pi = runtimeService.startProcessInstanceById("报销流程:1:e31cb903-eb1e-11ed-afea-00ff12280e2e",map);
//        ProcessInstance pi = runtimeService.startProcessInstanceByKey("报销流程",map);
//        log.info("id:{}",pi.getId());
        log.info("id:{}",pi.getProcessDefinitionId());
        log.info("key:{},activityId:{}",pi.getProcessDefinitionKey(),pi.getActivityId());
    }

    @Test
    void test02(){
        List<Task> list = taskService.createTaskQuery().taskAssignee("张三").list();
        for(Task task : list){
            log.info("id:{},name:{},taskDefinitionKey:{}",task.getId(),task.getName(),task.getTaskDefinitionKey());
        }
    }

    @Test
    void test03(){
        List<Task> list = taskService.createTaskQuery().taskAssignee("张三").list();
        for(Task task : list){
            taskService.complete(task.getId());
        }
    }

    @Test
    void test04(){
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId("b58e42da-eb25-11ed-bcbf-00ff12280e2e").singleResult();
        log.info("processInstance:{}",processInstance.getId());
    }


    @Test
    void test07(){
        List<Execution> list = runtimeService.createExecutionQuery().list();
        for (Execution execution : list) {
            List<String> activeActivityIds = runtimeService.getActiveActivityIds(execution.getId());
            for (String activeActivityId : activeActivityIds) {
                log.info("activeActivityId:{}",activeActivityId);
            }
        }
    }

}
