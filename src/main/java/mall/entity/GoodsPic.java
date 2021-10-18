package mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "goods_pic")
@ApiModel(value="GoodsPic对象", description="")
public class GoodsPic implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "pics_id", type = IdType.AUTO)
    private Integer picsId;

    private Integer goodsId;

    private String picsBig;

    private String picsMid;

    private String picsSma;

    private String picsBigUrl;

    private String picsMidUrl;

    private String picsSmaUrl;
/*    //一对多
    @TableField(exist = false)
    private List<Goods> goods;*/
}
