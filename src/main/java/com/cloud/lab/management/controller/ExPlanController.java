package com.cloud.lab.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.ExPlan;
import com.cloud.lab.management.entity.dto.explan.ExPlanAdd;
import com.cloud.lab.management.entity.dto.explan.ExPlanSearch;
import com.cloud.lab.management.entity.dto.explan.ExPlanUpdate;
import com.cloud.lab.management.service.IExPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: John.ma
 * @Description: 实验计划
 * @Date: 2020/02/26 10:47
 */
@Slf4j
@Api(tags = "实验计划")
@RestController
@RequestMapping("ex/plan")
public class ExPlanController {

    @Autowired
    private IExPlanService exPlanService;


    @ApiOperation(value = "保存实验计划", notes = "保存实验计划")
    @PostMapping(value = "/save/plan")
    public ResponseEntity<ResultModel> save(@Validated @RequestBody ExPlanAdd exPlanAdd) {

        boolean save = exPlanService.save(exPlanAdd);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "更新实验计划", notes = "更新实验计划")
    @PutMapping(value = "/update/plan/{id}")
    public ResponseEntity<ResultModel> update(@PathVariable("id") String id, @RequestBody ExPlanUpdate exPlanUpdate) {

        boolean save = exPlanService.update(id, exPlanUpdate);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "删除实验计划", notes = "删除实验计划")
    @GetMapping(value = "/delete/plan/{id}")
    public ResponseEntity<ResultModel> deleteById(@PathVariable("id") String id) {
        boolean save = exPlanService.delete(id);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "根据id查询实验计划", notes = "根据id查询实验计划")
    @GetMapping(value = "/get/plan/{id}")
    public ResponseEntity<ResultModel<ExPlan>> getById(@PathVariable("id") String id) {
        ExPlan exPlan = exPlanService.selectOneById(id);
        return new ResponseEntity<>(ResultModel.ok(exPlan));
    }

    @ApiOperation(value = "查询实验计划", notes = "查询实验计划")
    @GetMapping(value = "/get/list")
    public ResponseEntity<ResultModel<List<ExPlan>>> getList(ExPlanSearch search) {
        List<ExPlan> exPlans = exPlanService.listBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exPlans));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping(value = "/get/page")
    public ResponseEntity<ResultModel<IPage<ExPlan>>> getPageList(ExPlanSearch search) {
        IPage<ExPlan> exPlanIPage = exPlanService.selectPageBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exPlanIPage));
    }
}
