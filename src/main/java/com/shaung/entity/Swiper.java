package com.shaung.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 轮播图表
 * </p>
 *
 * @author shuang
 * @since 2021-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Swiper对象", description="轮播图表")
public class Swiper implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品主键")
      @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    @ApiModelProperty(value = "打开方式")
    private String openType;

    @ApiModelProperty(value = "图片地址")
    private String imageSrc;

    @ApiModelProperty(value = "跳转地址")
    private String navigatorUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
