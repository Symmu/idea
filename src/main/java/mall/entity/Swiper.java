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
@ApiModel(value="Swiper对象", description="")
public class Swiper implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片地址")
    private String imageSrc;

    private String openType;

      @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    private String navigatorUrl;


}
