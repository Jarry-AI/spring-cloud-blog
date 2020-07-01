package com.example.easyexcel.mapper;

import com.example.easyexcel.entity.BksStudentTestscore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 高中学生成绩表 Mapper 接口
 * </p>
 *
 * @author zhen huaxiang
 * @since 2020-06-06
 */
public interface BksStudentTestscoreMapper extends BaseMapper<BksStudentTestscore> {

     Boolean insertScore(@Param("list")List<BksStudentTestscore> list);
}
