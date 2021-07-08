package com.example.easyexcel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 分页信息
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-03-16
 */
@Data
@NoArgsConstructor
@ApiModel(value="相应分页", description="相应分页")
@Accessors(chain = true)
public class PageInfoVO implements Serializable {

    private static final long serialVersionUID = 3481236693416019129L;

    @ApiModelProperty(value="当前页", required = true, example = "1")
    private Long page;
    @ApiModelProperty(value="每页数量", required = true, example = "20")
    private Long size;
    @ApiModelProperty(value="总记录", required = true, example = "999")
    private Long total;

    public PageInfoVO(long page){
        this.page = page;
    }

    public PageInfoVO(long page, long size){
        this.page = page;
        this.size = size;
    }

    public PageInfoVO(long page, long size, long total){
        this.page = page;
        this.size = size;
        this.total = total;
    }

}
