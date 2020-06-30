package com.example.easyexcel.service;

import com.example.easyexcel.entity.BksStudentTestscore;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 高中学生成绩表 服务类
 * </p>
 *
 * @author zhen huaxiang
 * @since 2020-06-06
 */
public interface IBksStudentTestscoreService extends IService<BksStudentTestscore> {

    Boolean insertScore(List<BksStudentTestscore> list);
}
