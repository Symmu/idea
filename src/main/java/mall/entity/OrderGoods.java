package mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value="OrderGoods对象", description="")
public class OrderGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer order_id;

    private Integer goods_id;

    private String openid;

    private Integer goods_price;

    private Integer goods_number;

    private Integer goods_total_price;

    private String goods_name;

    private String goods_small_logo;

    private Integer user_id;


}
