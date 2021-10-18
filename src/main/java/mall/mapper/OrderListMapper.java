package mall.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mall.entity.Goods;
import mall.entity.OrderList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mall.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */
@Mapper
public interface OrderListMapper extends BaseMapper<OrderList> {
    @ResultMap("OrderMap")
    @Select("select * from order_list where order_pay =0 and openid = #{openid}")
    List<OrderList> Onpay(String openid);

    @ResultMap("OrderMap")
    @Select("select * from order_list where is_send = 1 and openid = #{openid}")
    List<OrderList> Issend(String openid);


    /**
     * 查询所有用户订单
     * @param iPage
     * @return
     */
    @Select("select * from order_list")
    @Results({@Result(id = true,property = "order_id",column = "order_id"),
            @Result(property = "openid",column = "openid"),
            @Result(property = "user_id",column = "user_id"),
            @Result(property = "order_number",column = "order_number"),
            @Result(property = "order_price",column = "order_price"),
            @Result(property = "order_pay",column = "order_pay"),
            @Result(property = "is_send",column = "is_send"),
            @Result(property = "consignee_addr",column = "consignee_addr"),
            @Result(property = "pay_status",column = "pay_status"),
            @Result(property = "create_time",column = "create_time"),
            @Result(property = "update_time",column = "update_time"),
            @Result(property = "order_detail",column = "order_detail"),
            //定义一对多的关系映射，实现封装
            @Result(property = "orderGoods",column = "order_id",
                    many = @Many(select = "mall.mapper.OrderGoodsMapper.findByOid",fetchType = FetchType.LAZY))
    })
    Page<OrderList> findAllOrders(IPage<OrderList> iPage);

    //查询用户关联的订单信息
    @Select("select * from order_list where openid=#{openid} order by create_time desc;")
    @Results(id = "OrderMap",value = {
            @Result(id = true,property = "order_id",column = "order_id"),
            @Result(property = "openid",column = "openid"),
            @Result(property = "user_id",column = "user_id"),
            @Result(property = "order_number",column = "order_number"),
            @Result(property = "order_price",column = "order_price"),
            @Result(property = "order_pay",column = "order_pay"),
            @Result(property = "is_send",column = "is_send"),
            @Result(property = "consignee_addr",column = "consignee_addr"),
            @Result(property = "pay_status",column = "pay_status"),
            @Result(property = "create_time",column = "create_time"),
            @Result(property = "update_time",column = "update_time"),
            @Result(property = "order_detail",column = "order_detail"),
            //定义一对多的关系映射，实现封装
            @Result(property = "orderGoods",column = "order_id",
                    many = @Many(select = "mall.mapper.OrderGoodsMapper.findByOid",fetchType = FetchType.LAZY))
    })
    List<OrderList> findMyOrders(String openid);

    //创建订单列表->controller创建订单商品
    @Insert("insert into " +
            "order_list(openid,order_number,order_price,order_pay,is_send,consignee_addr,pay_status,create_time,update_time) " +
            "values(#{openid},#{order_number},#{order_price},#{order_pay},#{is_send},#{consignee_addr},#{pay_status},now(),now())")
    //获取自增长的主键值
    @Options(useGeneratedKeys=true, keyProperty="order_id")
    int createOrder(OrderList orderList);


//    // 根据传递过来的用户id，查询该用户所具有的订单信息
//    @Select("select * from order_list where openid = #{order_id}")
//    List<OrderList> findByOid(Integer order_id);

////根据用户id查询订单
//    @Select("select * from order_list where openid=#{openid}")
//    @Results(id = "orderMap", value = {
//            @Result(id = true,property = "order_id",column = "order_id"),
//            @Result(property = "openid",column = "openid"),
//            @Result(property = "user_id",column = "user_id"),
//            @Result(property = "order_number",column = "order_number"),
//            @Result(property = "order_price",column = "order_price"),
//            @Result(property = "order_pay",column = "order_pay"),
//            @Result(property = "is_send",column = "is_send"),
//            @Result(property = "consignee_addr",column = "consignee_addr"),
//            @Result(property = "pay_status",column = "pay_status"),
//            @Result(property = "create_time",column = "create_time"),
//            @Result(property = "update_time",column = "update_time"),
//            @Result(property = "order_detail",column = "order_detail"),
//            //定义一对多的关系映射，实现封装
//            @Result(property = "goods",column = "openid",
//                    many = @Many(select = "mall.mapper.OrderGoodsMapper.findOrderGoods",fetchType = FetchType.LAZY))
//    })
//    List<OrderList> findOrder(String openid);



//    int createOrder(String openid,String order_number,Integer order_price,String consignee_addr);
//      int createOrder(@Param("param") Map<String,Object> params);
//    List<OrderList> createOrder(Object orderParams,String order_number);
//    List<OrderList> createOrder(String openid,String order_number,String order_price,String consignee_addr);

//    @Select("select * from order_list")
//    @Results(id = "orderMap2", value = {
//            @Result(id = true,property = "orderId",column = "order_id"),
//            @Result(property = "userId",column = "user_id"),
//            @Result(property = "orderNumber",column = "order_number"),
//            @Result(property = "orderPrice",column = "order_price"),
//            @Result(property = "orderPay",column = "order_pay"),
//            @Result(property = "isSend",column = "is_send"),
//            @Result(property = "consigneeAddr",column = "consignee_addr"),
//            @Result(property = "payStatus",column = "pay_status"),
//            @Result(property = "createTime",column = "create_time"),
//            @Result(property = "updateTime",column = "update_time"),
//            @Result(property = "orderDetail",column = "order_detail"),
//            //定义一对多的关系映射，实现封装
//            @Result(property = "goods",column = "order_id",
//                    many = @Many(select = "mall.mapper.OrderGoodsMapper.addOrderGoods",fetchType = FetchType.LAZY))
//    })
//    List<OrderList> createOrder();

//    @Select("select order_id,user_id,order_number,order_price,order_pay,consignee_addr,pay_status from order_list")
//    List<OrderList> createOrder();
}
