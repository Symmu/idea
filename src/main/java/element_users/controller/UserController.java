package element_users.controller;

import com.alibaba.druid.util.StringUtils;
import element_users.entity.User;
import element_users.service.UserService;
import element_users.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther:shuang
 * @Date:2021/2/26 - 02 - 26 - 14:31:15
 * @Description: vue_cli
 * @versin:1.0
 */
@RestController
@CrossOrigin //解决跨域问题
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    //删除用户
    @DeleteMapping("delete")
    public Result delete(String id){
        Result result = new Result();
        try {
            userService.delete(id);
            result.setMsg("删除用户信息成功！");
        }catch (Exception e){
            result.setStatus(false);
            result.setMsg("删除用户信息失败！请稍后再试！");
        }
        return result;
    }
    //保存用户 或者更新用户
    @PostMapping("saveOrupdate")
    public Result saveOrupdate(@RequestBody User user){
        Result result = new Result();

        try {
            if (StringUtils.isEmpty(user.getId())){
                userService.save(user);
                result.setMsg("保存成功！！！");
            }else {
                userService.update(user);
                result.setMsg("编辑成功！！！");
            }
        }catch (Exception e){
        result.setStatus(false);
        result.setMsg("系统错误：保存用户信息失败，请稍后再试...");
        }
        return result;
    }
//    //保存用户
//    @PostMapping("save")
//    public Result save(@RequestBody User user){
//        Result result = new Result();
//        try {
//            userService.save(user);
//            result.setMsg("保存用户信息成功！！！");
//        }catch (Exception e){
//        result.setStatus(false);
//        result.setMsg("系统错误：保存用户信息失败，请稍后再试...");
//        }
//        return result;
//    }
    //查询所有
    @GetMapping("findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("findByPage")
    public Map<String,Object> findByPage(Integer pageNow,Integer pageSize){
        Map<String,Object> result = new HashMap<>();
        pageNow  = pageNow  == null?1:pageNow;//如果pageNow为空的时候则为当前页
        pageSize = pageSize == null?4:pageSize;
        List<User> users = userService.findByPage(pageNow, pageSize);
        Long total = userService.findTotals();
        result.put("users",users);
        result.put("total",total);
        return result;
    }
}
