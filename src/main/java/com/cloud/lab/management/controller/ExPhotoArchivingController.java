package com.cloud.lab.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.ExPhotoArchiving;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingAdd;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingSearch;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingUpdate;
import com.cloud.lab.management.service.IExPhotoArchivingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: John.ma
 * @Description: 实验照片
 * @Date: 2020/02/26 10:47
 */
@Slf4j
@Api(tags = "实验照片")
@RestController
@RequestMapping("ex/photo/archiving")
public class ExPhotoArchivingController {

    @Autowired
    private IExPhotoArchivingService exPhotoArchivingService;


    @ApiOperation(value = "保存实验照片", notes = "保存实验照片")
    @PostMapping(value = "/save/photo")
    public ResponseEntity<ResultModel> save(@RequestBody ExPhotoArchivingAdd exPhotoArchivingAdd) {

        boolean save = exPhotoArchivingService.save(exPhotoArchivingAdd);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "更新实验照片", notes = "更新实验照片")
    @PostMapping(value = "/update/photo")
    public ResponseEntity<ResultModel> update(@RequestParam String id, @RequestBody ExPhotoArchivingUpdate exPhotoArchivingUpdate) {

        boolean save = exPhotoArchivingService.update(id, exPhotoArchivingUpdate);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping(value = "/delete/photo/{id}")
    public ResponseEntity<ResultModel> deleteById(@PathVariable("id") String id) {
        boolean save = exPhotoArchivingService.delete(id);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping(value = "/get/photo/{id}")
    public ResponseEntity<ResultModel> getById(@PathVariable("id") String id) {
        ExPhotoArchiving exPhotoArchiving = exPhotoArchivingService.selectOneById(id);
        return new ResponseEntity<>(ResultModel.ok(exPhotoArchiving));
    }

    @ApiOperation(value = "查询列表", notes = "查询列表")
    @GetMapping(value = "/get/list")
    public ResponseEntity<ResultModel> getList(ExPhotoArchivingSearch search) {
        List<ExPhotoArchiving> exPhotoArchivings = exPhotoArchivingService.listBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exPhotoArchivings));
    }

    @ApiOperation(value = "查询照片流水号列表", notes = "查询照片流水号列表")
    @GetMapping(value = "/get/photo/no")
    public ResponseEntity<ResultModel> getPhotoNoList(ExPhotoArchivingSearch search) {
        List<ExPhotoArchiving> exPhotoArchivings = exPhotoArchivingService.listBySearch(search);
        List<String> photoNos = exPhotoArchivings.stream().map(ExPhotoArchiving::getPhotoNo).collect(Collectors.toList());
        return new ResponseEntity<>(ResultModel.ok(photoNos));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping(value = "/get/page")
    public ResponseEntity<ResultModel> getPageList(ExPhotoArchivingSearch search) {
        IPage<ExPhotoArchiving> exPhotoArchivingIPage = exPhotoArchivingService.selectPageBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exPhotoArchivingIPage));
    }
}
