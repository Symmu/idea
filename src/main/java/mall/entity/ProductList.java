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
@ApiModel(value="ProductList对象", description="")
public class ProductList implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String imageSrc;

    private String imageWidth;

    private String openType;

    private String navigatorUrl;

    private Integer parentId;


}
