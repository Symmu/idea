package vue_cli_01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vue_cli_01.entity.User;
import vue_cli_01.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther:shuang
 * @Date:2021/2/22 - 02 - 22 - 13:14:24
 * @Description: vue_cli
 * @versin:1.0
 */
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 查询一个
     */
    @GetMapping("findOne")
    public User findOne(String id){
        return userService.findById(id);
    }
    /**
     * 删除
     */
    @GetMapping("delete")
    public Map<String,Object> delete(String id){
        HashMap<String, Object> map = new HashMap<>();
        try {
            userService.delete(id);
            map.put("success",true);
            map.put("msg","删除用户信息成功！！");
        }catch (Exception e){
            map.put("success",false);
            map.put("msg","删除用户信息失败！！");
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 添加
     */
    @PostMapping("add")
    public Map<String,Object> add(@RequestBody User user){
        HashMap<String, Object> map = new HashMap<>();
        try {
            userService.save(user);
            map.put("success",true);
            map.put("msg","添加用户信息成功！！");
        }catch (Exception e){
            map.put("success",false);
            map.put("msg","添加用户信息失败！！");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询所有
     */
    @GetMapping("findAll")
//    public Map<String,Object> findAll(Integer page){
    public Map<String,Object> findAll(Integer page,Integer rows){
        Map<String,Object> map = new HashMap<>();
        List<User> results = userService.findAll();
        map.put("total",10);
        map.put("totalpage",1);
        map.put("page",page);
        map.put("results",results);
        return map;
    }
}
