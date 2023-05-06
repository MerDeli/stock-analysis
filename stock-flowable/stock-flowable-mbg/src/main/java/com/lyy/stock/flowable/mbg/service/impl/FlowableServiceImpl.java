package com.lyy.stock.flowable.mbg.service.impl;

import com.lyy.stock.flowable.mbg.service.FlowableService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author:
 * @createTime: 2023/05/04 18:16:51
 * @version:
 * @Description:
 */
@Slf4j
@Service
public class FlowableServiceImpl implements FlowableService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deployment(String name, InputStream in) {
        Deployment deploy = repositoryService.createDeployment().addInputStream(name + "bpmn", in).name(name).deploy();
        log.info("流程部署id={}", deploy.getId());
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
        log.info("(启动流程使用)流程processDefinitionId={}", processDefinition.getId());

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<String> list() {
        List<ProcessDefinition> processList = repositoryService.createProcessDefinitionQuery().list();
        return processList.stream().map(ProcessDefinition::getName).collect(Collectors.toList());
    }

    // flowable 任务反签收




}
