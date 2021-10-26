package element_users.dao;

import element_users.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther:shuang
 * @Date:2021/2/26 - 02 - 26 - 13:48:07
 * @Description: vue_cli
 * @versin:1.0
 */
@Mapper
public interface UserDao {
    //查询所有用户信息
    List<User> findAll();

    //添加用户信息
    void save(User user);

    //根据id删除一个用户
    void delete(String id);

    //更新用户信息
    void update(User user);

    //分页查询
    List<User> findByPage(@Param("start")Integer start,@Param("rows")Integer rows);

    //查询总条数
    Long findTotals();
}
