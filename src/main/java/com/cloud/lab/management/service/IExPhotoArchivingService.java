package com.cloud.lab.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.lab.management.entity.ExParamGroup;
import com.cloud.lab.management.entity.ExPhotoArchiving;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupAdd;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupSearch;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupUpdate;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingAdd;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingSearch;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingUpdate;

import java.util.List;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 16:57
 */
public interface IExPhotoArchivingService extends IService<ExPhotoArchiving> {

    /**
     * 新增
     * @param exPhotoArchivingAdd
     * @return
     */
    boolean save(ExPhotoArchivingAdd exPhotoArchivingAdd);

    /**
     * 更新
     * @param id
     * @param exPhotoArchivingUpdate
     * @return
     */
    boolean update(String id, ExPhotoArchivingUpdate exPhotoArchivingUpdate);


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
    ExPhotoArchiving selectOneById(String id);

    /**
     * 查询
     * @param exPhotoArchivingSearch
     * @return
     */
    List<ExPhotoArchiving> listBySearch(ExPhotoArchivingSearch exPhotoArchivingSearch);

    /**
     * 分页查询
     * @param exPhotoArchivingSearch
     * @return
     */
    IPage<ExPhotoArchiving> selectPageBySearch(ExPhotoArchivingSearch exPhotoArchivingSearch);
}
