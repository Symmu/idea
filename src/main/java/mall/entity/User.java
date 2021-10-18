package mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="User对象", description="")
@JsonIgnoreProperties(value = "handler")//让Jackson序列化时忽略handler属性
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String openid;

    @TableField("nickName")
    private String nickName;

    private String city;

    private String province;

    private String country;

    @TableField("avatarUrl")
    private String avatarUrl;

    @TableField(exist = false)
    @ApiModelProperty("分组查询的用户订单数")
    private Integer orderCount;

//    //一对多
//    @TableField(exist = false)
//    private List<OrderList> ordersList;
}
