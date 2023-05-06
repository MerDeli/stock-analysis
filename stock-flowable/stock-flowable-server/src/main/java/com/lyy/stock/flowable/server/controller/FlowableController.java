package com.lyy.stock.flowable.server.controller;

import com.lyy.stock.flowable.mbg.service.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author:
 * @createTime: 2023/05/04 18:09:03
 * @version:
 * @Description:
 */
@RestController
@RequestMapping("/flowable-demo")
public class FlowableController {

    @Autowired
    private FlowableService flowableService;

    @PostMapping("/deploy")
    public String deploay(MultipartFile file) throws Exception{
        flowableService.deployment(file.getName(),file.getInputStream());
        return "部署成功";
    }

    @GetMapping("/list")
    public List<String> list() {
        return flowableService.list();
    }


}
