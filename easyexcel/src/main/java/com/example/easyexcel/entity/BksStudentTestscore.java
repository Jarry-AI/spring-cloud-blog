package com.example.easyexcel.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 高中学生成绩表
 * </p>
 *
 * @author zhen huaxiang
 * @since 2020-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "bks_student_testscore2")
public class BksStudentTestscore implements Serializable {

    private static final long serialVersionUID=1L;

    @ExcelProperty(value = "id",index = 0)
    private Integer id;

    /**
     * 考号
     */
    @ExcelProperty(value = "student_code",index = 1)
    private String studentCode;

    /**
     * 学籍号
     */
    @ExcelProperty(value = "student_id",index = 2)
    private String studentId;

    /**
     * 姓名
     */
    @ExcelProperty(value = "name",index = 3)
    private String name;

    /**
     * 区县
     */
    @ExcelProperty(value = "district",index = 4)
    private String district;

    /**
     * 学校
     */
    @ExcelProperty(value = "school",index = 5)
    private String school;

    /**
     * 考试名称
     */
    @ExcelProperty(value = "exam_name",index = 6)
    private String examName;
    @ExcelProperty(value = "school_years",index = 7)
    private String schoolYears;
    @ExcelProperty(value = "subject_yuwen",index = 8)
    private BigDecimal subjectYuwen;
    @ExcelProperty(value = "subject_shuxue",index = 9)
    private BigDecimal subjectShuxue;
    @ExcelProperty(value = "subject_yingyu",index = 10)
    private BigDecimal subjectYingyu;
    @ExcelProperty(value = "subject_wuli",index = 11)
    private BigDecimal subjectWuli;
    @ExcelProperty(value = "subject_lishi",index = 12)
    private BigDecimal subjectLishi;
    @ExcelProperty(value = "subject_dili",index = 13)
    private BigDecimal subjectDili;
    @ExcelProperty(value = "subject_huaxue",index = 14)
    private BigDecimal subjectHuaxue;
    @ExcelProperty(value = "subject_shengwu",index = 15)
    private BigDecimal subjectShengwu;
    @ExcelProperty(value = "subject_zhenzhi",index = 16)
    private BigDecimal subjectZhenzhi;
    @ExcelProperty(value = "subject_comb",index = 17)
    private String subjectComb;

    /**
     * 录入时间
     */
    @ExcelProperty(value = "gmt_create",index = 18)
    private String gmtCreate;

    /**
     * 修改时间
     */
    @ExcelProperty(value = "gmt_modified",index = 19)
    private String gmtModified;

    /**
     * 操作员代码
     */
    @ExcelProperty(value = "operator_id",index = 20)
    private String operatorId;

    /**
     * 操作员姓名
     */
    @ExcelProperty(value = "operator_name",index = 21)
    private String operatorName;

    /**
     * 备注(1 理科 2 文科)
     */
    @ExcelProperty(value = "remark",index = 22)
    private String remark;

    /**
     * 班级
     */
    @ExcelProperty(value = "class_name",index = 23)
    private String className;

    @ExcelProperty(value = "subject_dili_new",index = 24)
    private BigDecimal subjectDiliNew;
    @ExcelProperty(value = "subject_shengwu_new",index = 25)
    private BigDecimal subjectShengwuNew;
    @ExcelProperty(value = "subject_huaxue_new",index = 26)
    private BigDecimal subjectHuaxueNew;
    @ExcelProperty(value = "subject_zhenzhi_new",index = 27)
    private BigDecimal subjectZhenzhiNew;
    @ExcelProperty(value = "is_new",index = 28)
    private Integer isNew;

    /**
     * 是否可查 1:可查 0：不可查
     */
    @ExcelProperty(value = "is_check",index = 29)
    private String isCheck;
    @ExcelProperty(value = "my_score",index = 30)
    private BigDecimal myScore;
    @ExcelProperty(value = "grade_name",index = 31)
    private String gradeName;


}
