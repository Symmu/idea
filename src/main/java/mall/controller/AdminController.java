package mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import mall.entity.Admin;
import mall.mapper.AdminMapper;
import mall.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther:shuang
 * @Date:2021/5/6 - 05 - 06 - 13:54:52
 * @Description: vue_cli
 * @versin:1.0
 */
@RestController
@RequestMapping("/admin")
@Api(value = "管理员", tags = "管理员",description = "管理员模块")
public class AdminController {
    @Resource
    private AdminMapper adminMapper;

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        Result result = new Result();
        LambdaQueryWrapper<Admin> queryWrapper = Wrappers.<Admin>lambdaQuery()
                .eq(Admin::getAccount, admin.getAccount()).eq(Admin::getPassword, admin.getPassword());
        if (adminMapper.selectOne(queryWrapper)!=null) {
            result.setStatus(true);
            result.setMsg("登录成功！");
        } else {
            result.setStatus(false);
            result.setMsg("登录失败！");
        }
        return result;
    }
}
