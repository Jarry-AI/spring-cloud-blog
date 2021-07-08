package com.example.easyexcel.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 分页结果类型转换工具
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-03-16
 */
public class PageConvertUtil {
    /**
     * mybatis plus page转换 适用于链式类
     *
     * @author liushuangqing
     * @date 2020/5/28 17:06
     */
    public static <T> PageVO<T> copy(IPage source, Class<T> t) {
        if (source == null || source.getRecords() == null || source.getSize() <= 0) {
            return null;
        }
        PageVO<T> result = new PageVO<>();
        result.setPageInfoVO(new PageInfoVO(source.getCurrent(), source.getSize(), source.getTotal()));
        result.setList(JSON.parseArray(JSON.toJSONString(source.getRecords()), t));
        return result;
    }
}