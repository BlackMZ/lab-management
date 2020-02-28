package com.cloud.lab.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.ExDefectRecord;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordAdd;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordSearch;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordUpdate;
import com.cloud.lab.management.service.IExDefectRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: John.ma
 * @Description: 实验照片缺陷记录
 * @Date: 2020/02/26 10:47
 */
@Slf4j
@Api(tags = "实验照片缺陷记录")
@RestController
@RequestMapping("ex/defect/record")
public class ExDefectRecordController {

    @Autowired
    private IExDefectRecordService exDefectRecordService;


    @ApiOperation(value = "保存实验照片缺陷记录", notes = "保存实验照片缺陷记录")
    @PostMapping(value = "/save/record")
    public ResponseEntity<ResultModel> save(@RequestBody ExDefectRecordAdd exDefectRecordAdd) {

        boolean save = exDefectRecordService.save(exDefectRecordAdd);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "更新实验照片缺陷记录", notes = "更新实验照片缺陷记录")
    @PostMapping(value = "/update/record")
    public ResponseEntity<ResultModel> update(@RequestParam String id, @RequestBody ExDefectRecordUpdate exDefectRecordUpdate) {

        boolean save = exDefectRecordService.update(id, exDefectRecordUpdate);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping(value = "/delete/record/{id}")
    public ResponseEntity<ResultModel> deleteById(@PathVariable("id") String id) {
        boolean save = exDefectRecordService.delete(id);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping(value = "/get/record/{id}")
    public ResponseEntity<ResultModel> getById(@PathVariable("id") String id) {
        ExDefectRecord exDefectRecord = exDefectRecordService.selectOneById(id);
        return new ResponseEntity<>(ResultModel.ok(exDefectRecord));
    }

    @ApiOperation(value = "查询列表", notes = "查询列表")
    @GetMapping(value = "/get/list")
    public ResponseEntity<ResultModel> getList(ExDefectRecordSearch search) {
        List<ExDefectRecord> exParamGroups = exDefectRecordService.listBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exParamGroups));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping(value = "/get/page")
    public ResponseEntity<ResultModel> getPageList(ExDefectRecordSearch search) {
        IPage<ExDefectRecord> exParamGroupIPage = exDefectRecordService.selectPageBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exParamGroupIPage));
    }
}
