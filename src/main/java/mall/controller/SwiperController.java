package mall.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.entity.Swiper;
import mall.mapper.SwiperMapper;
import mall.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@CrossOrigin
@RequestMapping("/home")
@Api(value = "/home", tags = "首页")
public class SwiperController {
    @Autowired
    private SwiperService swiperService;

    @GetMapping("/swiperdata")
    @ApiOperation(value = "轮播图")
    public List<Swiper> findAll(){
        return swiperService.list();
    }

}

