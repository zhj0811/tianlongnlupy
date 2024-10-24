package com.cctegitc.ai.authority.common.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cctegitc.ai.authority.common.utils.uuid.UUID;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object created = getFieldValByName("createTime", metaObject);
        if (null == created) {
            // 字段为空，可以进行填充
            setFieldValByName("createTime", new Date(), metaObject);
        }
        Object uuid = getFieldValByName("uuid", metaObject);
        if (null == uuid) {
            // 字段为空，可以进行填充
            setFieldValByName("uuid", UUID.getUuid(), metaObject);
        }
        Object updated = getFieldValByName("updateTime", metaObject);
        if (null == updated) {
            // 字段为空，可以进行填充
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新数据时，直接更新字段
        setFieldValByName("updateTime", new Date(), metaObject);
    }
}
