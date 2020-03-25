package com.cloud.lab.management.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.ExDefectRecord;
import com.cloud.lab.management.entity.dto.exdefectrecord.DefectExport;
import com.cloud.lab.management.entity.dto.exdefectrecord.DefectRecordSaveAll;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordAdd;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordSearch;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordUpdate;
import com.cloud.lab.management.service.IExDefectRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @PutMapping(value = "/update/record/{id}")
    public ResponseEntity<ResultModel> update(@PathVariable("id") String id, @RequestBody ExDefectRecordUpdate exDefectRecordUpdate) {

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
    public ResponseEntity<ResultModel<ExDefectRecord>> getById(@PathVariable("id") String id) {
        ExDefectRecord exDefectRecord = exDefectRecordService.selectOneById(id);
        return new ResponseEntity<>(ResultModel.ok(exDefectRecord));
    }

    @ApiOperation(value = "查询列表", notes = "查询列表")
    @GetMapping(value = "/get/list")
    public ResponseEntity<ResultModel<List<ExDefectRecord>>> getList(ExDefectRecordSearch search) {
        List<ExDefectRecord> exDefectRecords = exDefectRecordService.listBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exDefectRecords));
    }

    @ApiOperation(value = "查询列表", notes = "查询列表")
    @GetMapping(value = "/get/list/by/{planId}/{groupId}/{photoNo}")
    public ResponseEntity<ResultModel<List<ExDefectRecord>>> getListBy(@PathVariable("planId") String planId, @PathVariable("groupId") String groupId,
                                                                       @PathVariable("photoNo") String photoNo) {
        List<ExDefectRecord> exDefectRecords = exDefectRecordService.listByPlanIdAndPhotoNo(planId, groupId, photoNo);
        return new ResponseEntity<>(ResultModel.ok(exDefectRecords));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping(value = "/get/page")
    public ResponseEntity<ResultModel<IPage<ExDefectRecord>>> getPageList(ExDefectRecordSearch search) {
        IPage<ExDefectRecord> exParamGroupIPage = exDefectRecordService.selectPageBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exParamGroupIPage));
    }


    @ApiOperation(value = "保存实验照片缺陷记录(多条同时)", notes = "保存实验照片缺陷记录(多条同时)")
    @PostMapping(value = "/save/record/all")
    public ResponseEntity<ResultModel> saveAll(@RequestBody DefectRecordSaveAll defectRecordSaveAll) {

        boolean save = exDefectRecordService.saveRecordAndUpdateFlag(defectRecordSaveAll);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "导出缺陷记录", notes = "导出缺陷记录")
    @GetMapping(value = "/export")
    public void export(HttpServletResponse response, ExDefectRecordSearch search) {
        List<DefectExport> exports = exDefectRecordService.exportDefect(search);
        String fileName = "train_passfail";
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".csv");
        response.setCharacterEncoding("UTF-8");
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "record"),
                DefectExport.class, exports);
        if (workbook != null) {
            try {
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
