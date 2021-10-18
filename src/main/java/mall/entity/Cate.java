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
@ApiModel(value="Cate对象", description="")
@JsonIgnoreProperties(value = "handler")//让Jackson序列化时忽略handler属性
public class Cate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cat_id", type = IdType.AUTO)
    private Integer catId;

    private String catName;

    private Float catPid;

    private Integer catLevel;

    private Integer catDeleted;

    private String catIcon;

    @TableField(exist=false)
    private List<Cate> children;
}
