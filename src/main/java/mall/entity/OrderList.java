package mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@ApiModel(value="OrderList对象", description="")
@JsonIgnoreProperties(value = "handler")//让Jackson序列化时忽略handler属性
@JsonInclude(JsonInclude.Include.NON_EMPTY)//NON_EMPTY表示为空字符串和null都会忽略
public class OrderList implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "order_id", type = IdType.AUTO)
    private Integer order_id;

    private Integer user_id;

    private String openid;

    private String order_number;

    private Integer order_price;

    private String order_pay;

    private Integer is_send;

    private String consignee_addr;

    private String pay_status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Long create_time;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Long update_time;

    private String order_detail;

    //一对多
    @TableField(exist = false)
    private List<OrderGoods> orderGoods;

}
