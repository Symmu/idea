package mall.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.entity.Cate;
import mall.entity.Navigate;
import mall.service.NavigateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.jws.Oneway;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */
@RestController
@RequestMapping("/home")
@Api(value = "/home", tags = "首页")
public class NavigateController {
    @Autowired
    private NavigateService navigateService;

    @GetMapping("/catitems")
    @ApiOperation(value = "导航")
    public List<Navigate> findAll(){
        return navigateService.list();
    }
}

