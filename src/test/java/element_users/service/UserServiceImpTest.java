package element_users.service;

import element_users.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


/**
 * @Auther:shuang
 * @Date:2021/2/26 - 02 - 26 - 14:03:50
 * @Description: vue_cli
 * @versin:1.0
 */
@SpringBootTest
class UserServiceImpTest {

    @Autowired
    private UserService userService;

    @Test
    public void findAll(){
        userService.findAll().forEach(user -> System.out.println("user = " + user));
    }

    @Test
    public void save() {
        User user = new User();
        user.setName("王老五");
        user.setSex("男");
        user.setBir(new Date());
        user.setAddress("上海市");
        userService.save(user);
    }

    @Test
    void delete() {
        userService.delete("9");
    }
}