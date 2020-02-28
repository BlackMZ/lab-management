package com.cloud.lab.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.ExPlanCameraConf;
import com.cloud.lab.management.entity.dto.explancameraconf.ExPlanCameraConfAdd;
import com.cloud.lab.management.entity.dto.explancameraconf.ExPlanCameraConfSearch;
import com.cloud.lab.management.entity.dto.explancameraconf.ExPlanCameraConfUpdate;
import com.cloud.lab.management.service.IExPlanCameraConfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: John.ma
 * @Description: 实验计划相机配置
 * @Date: 2020/02/26 10:47
 */
@Slf4j
@Api(tags = "实验计划相机配置")
@RestController
@RequestMapping("ex/camera/conf")
public class ExPlanCameraConfController {

    @Autowired
    private IExPlanCameraConfService exPlanCameraConfService;


    @ApiOperation(value = "保存实验计划相机配置", notes = "保存实验计划相机配置")
    @PostMapping(value = "/save/conf")
    public ResponseEntity<ResultModel> save(@RequestBody ExPlanCameraConfAdd add) {

        boolean save = exPlanCameraConfService.save(add);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "更新实验计划相机配置", notes = "更新实验计划相机配置")
    @PostMapping(value = "/update/conf")
    public ResponseEntity<ResultModel> update(@RequestParam String id, @RequestBody ExPlanCameraConfUpdate update) {

        boolean save = exPlanCameraConfService.update(id, update);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping(value = "/delete/conf/{id}")
    public ResponseEntity<ResultModel> delete(@PathVariable("id") String id) {
        boolean save = exPlanCameraConfService.delete(id);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping(value = "/get/conf/{id}")
    public ResponseEntity<ResultModel> getById(@PathVariable("id") String id) {
        ExPlanCameraConf exPlanCameraConf = exPlanCameraConfService.selectOneById(id);
        return new ResponseEntity<>(ResultModel.ok(exPlanCameraConf));
    }

    @ApiOperation(value = "查询列表", notes = "查询列表")
    @GetMapping(value = "/get/list")
    public ResponseEntity<ResultModel> getList(ExPlanCameraConfSearch search) {
        List<ExPlanCameraConf> exPlanCameraConfs = exPlanCameraConfService.listBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exPlanCameraConfs));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping(value = "/get/page")
    public ResponseEntity<ResultModel> getPageList(ExPlanCameraConfSearch search) {
        IPage<ExPlanCameraConf> page = exPlanCameraConfService.selectPageBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(page));
    }
}
