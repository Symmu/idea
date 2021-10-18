package mall.mapper;

import mall.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

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
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询用户及订单数量
     */
    @Select("SELECT u.openid,u.nickName,\n" +
            "IFNULL(" +
                "(SELECT count(*) " +
                "FROM order_list o " +
                "WHERE o.openid=u.openid " +
                "GROUP BY u.openid)" +
                ",0)" +
            "as orderCount " +
            "FROM user u")
    List<User> findUsersAndCount();
    //openid查询用户订单
// 查询所有用户，及关联的订单信息
//    @Select("select * from user")
//    @Results({
//            @Result(column = "userId",   property = "userId", id = true), // 映射主键
//            @Result(column = "openid",   property = "openid"),
//            @Result(column = "nickName", property = "nickName"),
//            @Result(column = "city",     property = "city"),
//            @Result(column = "province", property = "province"),
//            @Result(column = "country",  property = "country"),
//            @Result(column = "avatarUrl",property = "avatarUrl"),
//            @Result(property = "ordersList", javaType = mall.entity.OrderList.class, column = "order_id",
//                    many = @Many(select = "mall.mapper.OrderListMapper.findByOid"))
//    })
//    List<User> findAllWithOrders();
}
