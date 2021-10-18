package mall.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.entity.User;
import mall.mapper.UserMapper;
import mall.service.UserService;
import mall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */
//@CrossOrigin//解决跨域问题
@RestController
@RequestMapping("/user")
@Api(value = "/user", tags = "用户")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户")
    public Result AddUser(@RequestBody User user){
        System.out.println("user = " + user.getOpenid());
        Result result = new Result();
        String openid ="";
        try {
            for (User user2 : userMapper.selectList(null)) {
                if (user2.getOpenid().equals(user.getOpenid())){
                    openid =user2.getOpenid();
                }
            }
            if(user.getOpenid().equals(openid)){
                result.setMsg("用户信息已存在！！！");
            }else{
                userMapper.insert(user);
                result.setMsg("保存用户信息成功！！！");
            }
        }catch (Exception e){
            result.setStatus(false);
            result.setMsg("系统错误：保存用户信息失败，请稍后再试...");
        }
       return result;
    }

    /**
     * 查询所有用户-分页操作
     * @return
     */
    @GetMapping("/allUser")
    @ApiOperation(value = "所有用户")
    public Map<String, Object> all(@RequestParam(required = true,defaultValue = "1")Integer current,
                                   @RequestParam(required = true,defaultValue = "7")Integer size){
        Map<String,Object> result = new HashMap<>();
        Page<User> page = new Page<>(current,size);
        Page<User> userPage = userService.page(page);
        long total = userPage.getTotal();
        List<User> users = userPage.getRecords();
        result.put("users",users);
        result.put("total",total);
        return result;
//        return userMapper.selectList(null);
    }
    /**
     * 查询用户及订单数量
     */
    @GetMapping("/findUsersAndCount")
    @ApiOperation(value = "查询用户及订单数")
    public Map<String,Object> findUsersAndCount(){
        Map<String,Object> result = new HashMap<>();
        String msg="未找到数据!";
        List<User> usersAndCountList = userMapper.findUsersAndCount();
        if(usersAndCountList.size()==0){
            result.put("msg",msg);
        }
        result.put("usersAndCountList",usersAndCountList);
        return result;
    }

    /**
     * 根据id、查询用户
     * @param user_id
     * @return
     */
    @GetMapping("/byidUser/{user_id}")
    @ApiOperation(value = "查询某个用户信息")
    public User ByidFindUser(@PathVariable String user_id){
        return userMapper.selectById(user_id);
    }


//    @RequestMapping("/addUser")
//    public int AddUser(String avatarUrl,String city,String country,String nickName,String openid,String province){
//        User user = new User();
//        user.setAvatarurl(avatarUrl);
//        user.setCity(city);
//        user.setCountry(country);
//        user.setNickname(nickName);
//        user.setProvince(province);
//        user.setOpenid(openid);
//        int result = userMapper.insert(user);
//       return result;
//    }
}

