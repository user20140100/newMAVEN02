package com.origin.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

//元数据对象处理器

@Component
@Slf4j
public class myMetaObjectHandler implements MetaObjectHandler {

    private final Date now = new Date();

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充insert");
        log.info(metaObject.toString());

        long id = Thread.currentThread().getId();
        log.info("线程id为：{}",id);
        metaObject.setValue("starttime",now);
        metaObject.setValue("updatetime",now);
        metaObject.setValue("startuser",BaseContext.getCurrentuser());
        metaObject.setValue("updateuser",BaseContext.getCurrentuser());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充update");
        log.info(metaObject.toString());

        long id = Thread.currentThread().getId();
        log.info("线程id为：{}",id);

        metaObject.setValue("updatetime",now);
        metaObject.setValue("updateuser",BaseContext.getCurrentuser());
    }
}