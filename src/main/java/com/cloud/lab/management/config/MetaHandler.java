package com.cloud.lab.management.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MetaHandler implements MetaObjectHandler {
    private static final Logger logger = LoggerFactory.getLogger(MetaHandler.class);
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createdAt", LocalDateTime.now(), metaObject);
        this.setFieldValByName("modifiedAt", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createdBy", "张越", metaObject);
        this.setFieldValByName("modifiedBy", "张越", metaObject);
        this.setFieldValByName("isDelete", 0, metaObject);
        this.setFieldValByName("version", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifiedAt", LocalDateTime.now(), metaObject);
        this.setFieldValByName("modifiedBy", "张越", metaObject);
    }
}
