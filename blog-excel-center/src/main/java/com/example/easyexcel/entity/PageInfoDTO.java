package com.example.easyexcel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * <p>
 * 分页请求参数集
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-03-16
 */
@Data
@ToString
@NoArgsConstructor
@ApiModel(value="分页参数", description="分页参数")
public class PageInfoDTO implements Serializable {

    private static final long serialVersionUID = 6094298514687248499L;
    @Min(value = 1, message = "当前分页必须大于0")
    @ApiModelProperty(value = "当前页", name = "page",required = true,example = "1")
    private Integer page = 1;

    @ApiModelProperty(value = "显示数", name = "size", required = true,example = "500")
    @Range(min = 1, max = 500, message = "每页分页个数只能在1 - 500之间")
    private Integer size = 10;

}
