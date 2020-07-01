package com.example.easyexcel.service.impl;

import com.example.easyexcel.entity.BksStudentTestscore;
import com.example.easyexcel.mapper.BksStudentTestscoreMapper;
import com.example.easyexcel.service.IBksStudentTestscoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 高中学生成绩表 服务实现类
 * </p>
 *
 * @author zhen huaxiang
 * @since 2020-06-06
 */
@Service
public class BksStudentTestscoreServiceImpl extends ServiceImpl<BksStudentTestscoreMapper, BksStudentTestscore> implements IBksStudentTestscoreService {

    @Resource
    public BksStudentTestscoreMapper studentTestscoreMapper;

    @Override
    public Boolean insertScore(List<BksStudentTestscore> list) {

        return studentTestscoreMapper.insertScore(list);
    }
}
