package com.example.easyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author ZhenHX
 * @description 学生实际成绩模板
 * @date 2020-05-29 17:20
 */
@Data
public class StudentScore {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * 学籍号
     */
    @ExcelProperty(value = "学籍号",index = 0)
    private String studentId;
    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名",index = 1)
    private String name;
    /**
     * 区县
     */
    @ExcelProperty(value = "区县",index = 2)
    private String district;
    /**
     * 学校
     */
    @ExcelProperty(value = "学校",index = 3)
    private String school;
    /**
     * 考试批次
     */
    @ExcelProperty(value = "考试批次",index = 4)
    private String examName;
    /**
     * 年级
     */
    @ExcelProperty(value = "年级",index = 5)
    private String gradeName;
    /**
     * 班级
     */
    @ExcelProperty(value = "班级",index = 6)
    private String className;

    /**
     * 语文
     */
    @ExcelProperty(value = "语文",index = 7)
    private Double subjectYuwen;
    /**
     * 数学
     */
    @ExcelProperty(value = "数学",index = 8)
    private Double subjectShuxue;
    /**
     * 英语
     */
    @ExcelProperty(value = "英语",index = 9)
    private Double subjectYingyu;
    /**
     * 政治
     */
    @ExcelProperty(value = "政治",index = 10)
    private Double subjectZhenzhi;
    /**
     * 历史
     */
    @ExcelProperty(value = "历史",index = 11)
    private Double subjectLishi;
    /**
     * 地理
     */
    @ExcelProperty(value = "地理",index = 12)
    private Double subjectDili;
    /**
     * 物理
     */
    @ExcelProperty(value = "物理",index = 13)
    private Double subjectWuli;
    /**
     * 化学
     */
    @ExcelProperty(value = "化学",index = 14)
    private Double subjectHuaxue;
    /**
     * 生物
     */
    @ExcelProperty(value = "生物",index = 15)
    private Double subjectShengwu;
    /**
     * 总分
     */
    @ExcelProperty(value = "总分",index = 16)
    private Double myScore;


}
