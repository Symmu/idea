package mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@ApiModel(value="FloorTitle对象", description="")
@JsonIgnoreProperties(value = "handler")//让Jackson序列化时忽略handler属性
public class FloorTitle implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer Id;

    private String name;

    private String imageSrc;
    //一对多映射关系，主表实体含有从表实体的集合引用
    @TableField(exist = false)
    private List<ProductList> product_list;

}
