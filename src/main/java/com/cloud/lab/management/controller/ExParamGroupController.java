package com.cloud.lab.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.ExParamGroup;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupAdd;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupSearch;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupUpdate;
import com.cloud.lab.management.service.IExParamGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: John.ma
 * @Description: 实验参数组
 * @Date: 2020/02/26 10:47
 */
@Slf4j
@Api(tags = "实验参数组")
@RestController
@RequestMapping("ex/param/group")
public class ExParamGroupController {

    @Autowired
    private IExParamGroupService exParamGroupService;


    @ApiOperation(value = "保存实验参数组", notes = "保存实验参数组")
    @PostMapping(value = "/save/group")
    public ResponseEntity<ResultModel> save(@RequestBody ExParamGroupAdd exParamGroupAdd) {

        boolean save = exParamGroupService.save(exParamGroupAdd);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "更新实验参数组", notes = "更新实验参数组")
    @PostMapping(value = "/update/group")
    public ResponseEntity<ResultModel> update(@RequestParam String id, @RequestBody ExParamGroupUpdate exParamGroupUpdate) {

        boolean save = exParamGroupService.update(id, exParamGroupUpdate);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping(value = "/delete/group/{id}")
    public ResponseEntity<ResultModel> deleteById(@PathVariable("id") String id) {
        boolean save = exParamGroupService.delete(id);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping(value = "/get/group/{id}")
    public ResponseEntity<ResultModel> getById(@PathVariable("id") String id) {
        ExParamGroup exParamGroup = exParamGroupService.selectOneById(id);
        return new ResponseEntity<>(ResultModel.ok(exParamGroup));
    }

    @ApiOperation(value = "查询列表", notes = "查询列表")
    @GetMapping(value = "/get/list")
    public ResponseEntity<ResultModel> getList(ExParamGroupSearch search) {
        List<ExParamGroup> exParamGroups = exParamGroupService.listBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exParamGroups));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping(value = "/get/page")
    public ResponseEntity<ResultModel> getPageList(ExParamGroupSearch search) {
        IPage<ExParamGroup> exParamGroupIPage = exParamGroupService.selectPageBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exParamGroupIPage));
    }
}
