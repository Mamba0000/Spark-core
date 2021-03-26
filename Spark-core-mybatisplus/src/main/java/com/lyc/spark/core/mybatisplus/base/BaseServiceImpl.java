package com.lyc.spark.core.mybatisplus.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Date;
import java.util.List;

import com.lyc.spark.core.auth.util.BladeUser;
import com.lyc.spark.core.auth.util.SecureUtil;
import com.lyc.spark.core.mybatisplus.entity.BaseEntity;
import com.lyc.spark.core.tool.DateUtil;
import org.springframework.validation.annotation.Validated;

@Validated
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {
    public BaseServiceImpl() {
    }

    public boolean save(T entity) {
        BladeUser user = SecureUtil.getUser();
        if (user != null) {
            entity.setCreateUser(user.getUserId());
            entity.setUpdateUser(user.getUserId());
        }

        Date now = DateUtil.now();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        if (entity.getStatus() == null) {
            entity.setStatus(1);
        }

        entity.setIsDeleted(0);
        return super.save(entity);
    }

    public boolean updateById(T entity) {
        BladeUser user = SecureUtil.getUser();
        if (user != null) {
            entity.setUpdateUser(user.getUserId());
        }

        entity.setUpdateTime(DateUtil.now());
        return super.updateById(entity);
    }

    public boolean deleteLogic(List<Long> ids) {
        return super.removeByIds(ids);
    }
}
