package com.lyc.spark.core.mybatisplus.base;


import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface BaseService<T> extends IService<T> {
    boolean deleteLogic(List<Long> ids);
}
