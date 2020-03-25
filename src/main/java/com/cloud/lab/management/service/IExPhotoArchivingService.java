package com.cloud.lab.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.lab.management.entity.ExPhotoArchiving;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ArchivingAdd;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingAdd;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingSearch;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingUpdate;
import com.cloud.lab.management.entity.vo.SampleVO;

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
     * 新增
     * @param archivingAdd
     * @return
     */
    boolean saveArchive(ArchivingAdd archivingAdd);

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

    /**
     * 根据id查询
     * @param groupId
     * @return
     */
    List<SampleVO> selectByGroupId(String groupId);

    /**
     * 获取照片流水号
     * @param exPhotoArchivingSearch
     * @return
     */
    String getPhotoNo(ExPhotoArchivingSearch exPhotoArchivingSearch);

    /**
     * 获取照片流水号
     * @param groupId
     * @return
     */
    String getSingleNoByGroupId(String groupId);

    /**
     * 重拍
     * @param exPhotoArchivingSearch
     * @return
     */
    boolean retake(ExPhotoArchivingSearch exPhotoArchivingSearch);


}
