package com.cloud.lab.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.ExParamDetails;
import com.cloud.lab.management.entity.dto.exparamdetails.ExParamDetailsAdd;
import com.cloud.lab.management.entity.dto.exparamdetails.ExParamDetailsSearch;
import com.cloud.lab.management.entity.dto.exparamdetails.ExParamDetailsUpdate;
import com.cloud.lab.management.service.IExParamDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: John.ma
 * @Description: 实验参数详细
 * @Date: 2020/02/26 10:47
 */
@Slf4j
@Api(tags = "实验参数详细")
@RestController
@RequestMapping("ex/param/details")
public class ExParamDetailsController {

    @Autowired
    private IExParamDetailsService exParamDetailsService;


    @ApiOperation(value = "保存实验参数详细", notes = "保存实验参数详细")
    @PostMapping(value = "/save/details")
    public ResponseEntity<ResultModel> save(@RequestBody ExParamDetailsAdd exParamDetailsAdd) {

        boolean save = exParamDetailsService.save(exParamDetailsAdd);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "更新实验参数详细", notes = "更新实验参数详细")
    @PostMapping(value = "/update/details")
    public ResponseEntity<ResultModel> update(@RequestParam String id, @RequestBody ExParamDetailsUpdate exParamDetailsUpdate) {

        boolean save = exParamDetailsService.update(id, exParamDetailsUpdate);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping(value = "/delete/details/{id}")
    public ResponseEntity<ResultModel> deleteById(@PathVariable("id") String id) {
        boolean save = exParamDetailsService.delete(id);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping(value = "/get/details/{id}")
    public ResponseEntity<ResultModel> getById(@PathVariable("id") String id) {
        ExParamDetails exParamDetails = exParamDetailsService.selectOneById(id);
        return new ResponseEntity<>(ResultModel.ok(exParamDetails));
    }

    @ApiOperation(value = "查询列表", notes = "查询列表")
    @GetMapping(value = "/get/list")
    public ResponseEntity<ResultModel> getList(ExParamDetailsSearch search) {
        List<ExParamDetails> exParamDetails = exParamDetailsService.listBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exParamDetails));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping(value = "/get/page")
    public ResponseEntity<ResultModel> getPageList(ExParamDetailsSearch search) {
        IPage<ExParamDetails> exParamDetailsIPage = exParamDetailsService.selectPageBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exParamDetailsIPage));
    }
}
