package mall.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther:shuang
 * @Date:2021/3/14 - 03 - 14 - 19:19:02
 * @Description: vue_cli
 * @versin:1.0
 */
public class GoodsPicGoodVO implements Serializable {

    @ApiModelProperty(value = "商品图片ID")
    private Integer picsId;

    private Integer goodsId;

    private String picsBig;

    private String picsMid;

    private String picsSma;

    private String picsBigUrl;

    private String picsMidUrl;

    private String picsSmaUrl;

}
