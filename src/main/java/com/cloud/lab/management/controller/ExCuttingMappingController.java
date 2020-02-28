package com.cloud.lab.management.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.ExCuttingMapping;
import com.cloud.lab.management.entity.dto.excuttingmapping.ExCuttingMappingAdd;
import com.cloud.lab.management.entity.dto.excuttingmapping.ExCuttingMappingSearch;
import com.cloud.lab.management.service.IExCuttingMappingService;
import com.cloud.lab.management.util.ExportExcelUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: John.ma
 * @Description: 实验照片切片對照檔
 * @Date: 2020/02/26 10:47
 */
@Slf4j
@Api(tags = "实验照片切片對照檔")
@RestController
@RequestMapping("ex/cutting/mapping")
public class ExCuttingMappingController {

    @Autowired
    private IExCuttingMappingService exCuttingMappingService;


    @ApiOperation(value = "保存实验照片切片對照檔", notes = "保存实验照片切片對照檔")
    @PostMapping(value = "/save/mapping")
    public ResponseEntity<ResultModel> save(@RequestBody ExCuttingMappingAdd exCuttingMappingAdd) {

        boolean save = exCuttingMappingService.save(exCuttingMappingAdd);
        if (save) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "查询列表", notes = "查询列表")
    @GetMapping(value = "/get/list")
    public ResponseEntity<ResultModel> getList(ExCuttingMappingSearch search) {
        List<ExCuttingMapping> exCuttingMappings = exCuttingMappingService.listBySearch(search);
        return new ResponseEntity<>(ResultModel.ok(exCuttingMappings));
    }

    @ApiOperation(value = "导入excel", notes = "导入excel")
    @PostMapping(value = "/import")
    public ResponseEntity<ResultModel> importExcel(@RequestParam("file") MultipartFile file) {
        System.out.println("名字：" + file.getOriginalFilename());
        List<ExCuttingMapping> exCuttingMappings = ExportExcelUtil.importExcel(file, 0, 1, ExCuttingMapping.class);
        boolean result = exCuttingMappingService.saveImport(exCuttingMappings);
        System.out.println("导入数据一共【" + exCuttingMappings.size() + "】行");
        if (result) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }

    @ApiOperation(value = "导入excel(多sheet)", notes = "导入excel(多sheet)")
    @PostMapping(value = "/excelImport")
    public ResponseEntity<ResultModel> excelImport(@RequestParam("file") MultipartFile file) throws IOException {
        //根据file得到Workbook,主要是要根据这个对象获取,传过来的excel有几个sheet页
        Workbook workBook = ExportExcelUtil.getWorkBook(file);
//        StringBuilder sb = new StringBuilder();
        try {
            ImportParams params = new ImportParams();
            List<ExCuttingMapping> allList = Lists.newArrayList();
            // 循环工作表Sheet
            for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
                //表头在第几行
                params.setTitleRows(0);
                //距离表头中间有几行不要的数据
                params.setStartRows(0);
                //第几个sheet页
                params.setStartSheetIndex(numSheet);
                //验证数据
                params.setNeedVerfiy(true);
                //ExcelImportResult<Object> result=null;
                List<ExCuttingMapping> exCuttingMappings = ExcelImportUtil.importExcel(file.getInputStream(), ExCuttingMapping.class, params);
                allList.addAll(exCuttingMappings);

//                if (numSheet == 0) {
//                    ExcelImportResult<ExCuttingMapping> result = ExcelImportUtil.importExcelMore(file.getInputStream(), ExCuttingMapping.class, params);
//                    List<ExCuttingMapping> list = null;
//                    //如果有些数据验证出来有误   为true
//                    if (result.isVerfiyFail()) {
//                        //不合规定的数据
//                        list = result.getFailList();
//                        //拼凑错误信息,自定义
//                        for (int i = 0; i < list.size(); i++) {
//                            if (list.get(i) instanceof ExCuttingMapping) {
//                                ExportExcelUtil.getWrongInfo(sb, list, i, list.get(i), "realName", "Entity1信息不符");
//                            } else if (list.get(i) instanceof ExCuttingMapping) {
//                                ExportExcelUtil.getWrongInfo(sb, list, i, list.get(i), "age", "Entity2信息不符");
//                            }
//                        }
//                    }
//                    //List<ProjectPojo> proList= ExcelImportUtil.importExcel(file.getInputStream(),ProjectPojo.class, params);
//                    System.out.println("***********导入数据:" + result.getList());
//                    System.out.println("***********错误信息:" + sb.toString());
//                    //插入验证合格的数据
//                    //CallServiceUtil.callDataService("", "", new Object[] { result.getList() },new Class[] { List.class });
//                } else if (numSheet == 1) {
//                    List<ExCuttingMapping> exCuttingMappings = ExcelImportUtil.importExcel(file.getInputStream(), ExCuttingMapping.class, params);
//                    System.out.println("***********导入数据:" + exCuttingMappings);
//                } else if (numSheet > 1) {
//                    List<ExCuttingMapping> exCuttingMappings = ExcelImportUtil.importExcel(file.getInputStream(), ExCuttingMapping.class, params);
//                    System.out.println("***********导入数据:" + exCuttingMappings);
//                }
            }
            System.out.println("导入数据一共【" + allList.size() + "】行");
//            if (sb.length() != 0) {
//                return new ResponseEntity<>(ResultModel.ok(sb.toString()));
//            }
            boolean saveImport = exCuttingMappingService.saveImport(allList);
            if (saveImport) {
                return new ResponseEntity<>(ResultModel.ok());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResultModel.failure("导入失败！请检查导入文档的格式是否正确"));
        }
        return new ResponseEntity<>(ResultModel.failure());
    }
}
