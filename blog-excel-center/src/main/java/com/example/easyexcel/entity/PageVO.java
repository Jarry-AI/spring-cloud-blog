package com.example.easyexcel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 分页返回结果实体
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-03-16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "分页实体", description = "包含分页信息和数据数组")
public class PageVO<T> implements Serializable {

    private static final long serialVersionUID = -6868692026104831795L;

    @ApiModelProperty(value = "分页数据")
    private PageInfoVO pageInfoVO;

    @ApiModelProperty(position = 2, value = "列表数据")
    private List<T> list;

}
