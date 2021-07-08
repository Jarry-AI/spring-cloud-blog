package com.example.easyexcel.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 分数
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-03-16
 */
@Data
@ApiModel
public class ScoreVO implements Serializable {

    @ApiModelProperty(value = "姓名",required = true)
    @NotBlank(message = "姓名不能为空")
    private String name;
    @ApiModelProperty(value = "学校",required = true)
    @NotEmpty(message = "学校不能为空")
    private List<String> school;
    @ApiModelProperty(value = "总分",required = true)
    @NotNull(message = "总分不能为空")
    private Double myScore;

}
