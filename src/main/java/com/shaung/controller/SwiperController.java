package com.shaung.controller;

import com.shaung.entity.Swiper;
import com.shaung.service.SwiperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 轮播图表 前端控制器
 * </p>
 *
 * @author shuang
 * @since 2021-03-12
 */
@RestController
@RequestMapping("/shuang")
@Api(value = "轮播图管理")
public class SwiperController {

    @Autowired
    private SwiperService swiperService;

    //不写("/findSwipers") 则默认查询所有
    @GetMapping("/findSwipers")
    @ApiOperation(value = "查询所有的轮播图信息")
    public List<Swiper> findSwipers(){
        List<Swiper> list = swiperService.list();
        return list;
    }
}

