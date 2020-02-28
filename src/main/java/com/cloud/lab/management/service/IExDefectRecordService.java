package com.cloud.lab.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.CameraParameters;
import com.cloud.lab.management.entity.ExDefectRecord;
import com.cloud.lab.management.entity.ExParamGroup;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordAdd;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordSearch;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordUpdate;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupAdd;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupSearch;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupUpdate;
import com.cloud.lab.management.entity.vo.CameraParametersVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 16:57
 */
public interface IExDefectRecordService extends IService<ExDefectRecord> {
    /**
     * 新增
     * @param exDefectRecordAdd
     * @return
     */
    boolean save(ExDefectRecordAdd exDefectRecordAdd);

    /**
     * 更新
     * @param id
     * @param exDefectRecordUpdate
     * @return
     */
    boolean update(String id, ExDefectRecordUpdate exDefectRecordUpdate);


    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    ExDefectRecord selectOneById(String id);

    /**
     * 查询
     * @param exDefectRecordSearch
     * @return
     */
    List<ExDefectRecord> listBySearch(ExDefectRecordSearch exDefectRecordSearch);

    /**
     * 分页查询
     * @param exDefectRecordSearch
     * @return
     */
    IPage<ExDefectRecord> selectPageBySearch(ExDefectRecordSearch exDefectRecordSearch);
}
