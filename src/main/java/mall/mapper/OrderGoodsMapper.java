package mall.mapper;

import mall.entity.OrderGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mall.entity.OrderList;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */
@Mapper
public interface OrderGoodsMapper extends BaseMapper<OrderGoods> {

    // 根据传递过来的用户id，查询该用户所具有的订单信息
//    @Select("select * from order_goods g inner join order_list l on g.order_id=l.order_id where l.openid=#{openid}")
    @Select("select * from order_goods where order_id = #{order_id} ")
    @Results({
            @Result(id = true,property = "order_id",column = "order_id"),
            @Result(property = "goods_id",column = "goods_id"),
            @Result(property = "openid",column = "openid"),
            @Result(property = "goods_price",column = "goods_price"),
            @Result(property = "goods_number",column = "goods_number"),
            @Result(property = "goods_total_price",column = "goods_total_price"),
            @Result(property = "goods_name",column = "goods_name"),
            @Result(property = "goods_small_logo",column = "goods_small_logo"),
    })
    List<OrderGoods> findByOid(Integer order_id);
//    List<OrderGoods> findByOid(String openid);


//    @Select("select * from order_goods where openid=#{openid}")
//    List<OrderGoods> findOrderGoods(String openid);

//    @Insert("insert into order_goods " +
//            "(goods_name,goods_id,goods_number,goods_price,goods_total_price)" +
//            "value(#{goods_name},#{goods_id},#{goods_number},#{goods_price},#{goods_total_price})")
//    List<OrderGoods> addOrderGoods(String goods_name,Integer goods_id,Integer goods_number,Integer goods_price,Integer goods_total_price);

}


