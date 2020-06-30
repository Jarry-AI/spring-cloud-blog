package com.example.easyexcel.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.easyexcel.mapper.StudentScoreMapper;
import com.example.easyexcel.model.StudentScore;
import com.example.easyexcel.service.IStudentScoreService;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-05-30
 */
@Service
public class StudentScoreServiceImpl extends ServiceImpl<StudentScoreMapper, StudentScore> implements IStudentScoreService {

}
