package com.cloud.lab.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.ExCameraInfo;
import com.cloud.lab.management.entity.dto.excamerainfo.ExCameraInfoAdd;
import com.cloud.lab.management.entity.dto.excamerainfo.ExCameraInfoSearch;
import com.cloud.lab.management.entity.dto.excamerainfo.ExCameraInfoUpdate;
import com.cloud.lab.management.service.IExCameraInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Description: 实验台相机信息
 * @Date: 2020/02/26 10:47
 */
@Slf4j
@Api(tags = "实验台相机信息")
@RestController
@RequestMapping("ex/camera/info")
public class ExCameraInfoController {

    @Autowired
    private IExCameraInfoService exCameraInfoService;


    @ApiOperation(value = "保存实验台相机信息", notes = "保存实验台相机信息")
    @PostMapping(value = "/save/camera")
    public ResponseEntity<ResultModel> save(@RequestBody ExCameraInfoAdd exCameraInfoAdd) {

        boolean save = exCameraInfoService.save(exCameraInfoAdd);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "更新实验台相机信息", notes = "更新实验台相机信息")
    @PutMapping(value = "/update/camera/{id}")
    public ResponseEntity<ResultModel> update(@PathVariable("id") String id, @RequestBody ExCameraInfoUpdate exCameraInfoUpdate) {

        boolean save = exCameraInfoService.update(id, exCameraInfoUpdate);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping(value = "/delete/camera/{id}")
    public ResponseEntity<ResultModel> delete(@PathVariable("id") String id) {
        boolean save = exCameraInfoService.delete(id);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping(value = "/get/plan/{id}")
    public ResponseEntity<ResultModel<ExCameraInfo>> getById(@PathVariable("id") String id) {
        ExCameraInfo exCameraInfo = exCameraInfoService.selectOneById(id);
        return new ResponseEntity<>(ResultModel.ok(exCameraInfo));
    }

    @ApiOperation(value = "查询实验计划", notes = "查询实验计划")
    @GetMapping(value = "/get/list")
    public ResponseEntity<ResultModel<List<ExCameraInfo>>> getList(ExCameraInfoSearch search) {
        List<ExCameraInfo> exCameraInfos = exCameraInfoService.listBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exCameraInfos));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping(value = "/get/page")
    public ResponseEntity<ResultModel<IPage<ExCameraInfo>>> getPageList(ExCameraInfoSearch search) {
        IPage<ExCameraInfo> exCameraInfoIPage = exCameraInfoService.selectPageBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exCameraInfoIPage));
    }
}
