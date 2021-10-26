package vue_cli.service;

import vue_cli.entity.User;

import java.util.List;

/**
 * @Auther:shuang
 * @Date:2021/2/22 - 02 - 22 - 13:09:22
 * @Description: vue_cli
 * @versin:1.0
 */
public interface UserService {
    void save(User user);

    void update(User user);

    void delete(String id);

    List<User> findAll();

    User findById(String id);
}
