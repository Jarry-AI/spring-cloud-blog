package com.example.easyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZhenHX
 * @description 用于导入导出的对象
 * @date 2020-04-03 16:34
 */
@Data
public class ScienceStudentScore {
    /**
     * 学籍号/考号
     */
    @ExcelProperty(value = "学籍号/考号",index = 0)
    private String studentCode;
    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名",index = 1)
    private String name;
    /**
     * 市级
     */
    @ExcelProperty(value = "市级",index = 2)
    private String province;
    /**
     * 区县
     */
    @ExcelProperty(value = "区县",index = 3)
    private String district;
    /**
     * 学校
     */
    @ExcelProperty(value = "学校",index = 4)
    private String school;
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
     * 物理
     */
    @ExcelProperty(value = "物理",index = 10)
    private Double subjectWuli;
    /**
     * 化学
     */
    @ExcelProperty(value = "化学",index = 11)
    private Double subjectHuaxue;
    /**
     * 生物
     */
    @ExcelProperty(value = "生物",index = 12)
    private Double subjectShengwu;
    /**
     * 总分
     */
    @ExcelProperty(value = "总分",index = 13)
    private Double myScore;
}
