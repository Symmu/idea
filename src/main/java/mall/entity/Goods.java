package mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName(value = "goods")
@ApiModel(value="Goods对象", description="")
@JsonIgnoreProperties(value = "handler")//让Jackson序列化时忽略handler属性
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    private Integer catId;

    private String goodsName;

    private Double goodsPrice;

    private Integer goodsNumber;

    private Double goodsWeight;

    private String goodsIntroduce;

    private String goodsBigLogo;

    private String goodsSmallLogo;

    private Integer goodsState;

    private String isDel;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long addTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long updTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long deleteTime;

    private Integer hotMumber;

    private Integer isPromote;

    private Integer catOneId;

    private Integer catTwoId;

    private Integer catThreeId;

    private String goodsCat;

    //一对多
    @TableField(exist = false)
    private List<GoodsPic> pics;
}
