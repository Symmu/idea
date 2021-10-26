package vue_cli.dao;

import org.apache.ibatis.annotations.Mapper;
import vue_cli.entity.User;

import java.util.List;

/**
 * @Auther:shuang
 * @Date:2021/2/22 - 02 - 22 - 12:41:59
 * @Description: vue_cli
 * @versin:1.0
 */
@Mapper
public interface UserDao {
    void save(User user);

    void update(User user);

    void delete(String id);

    List<User> findAll();

    User findById(String id);
}
