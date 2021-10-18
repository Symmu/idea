package mall.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mall.entity.Cate;
import mall.entity.Swiper;
import mall.mapper.CateMapper;
import mall.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
@CrossOrigin
//@RequestMapping("/categories")
@Api(value = "分类", tags = "分类",description = "分类模块")

public class CateController {
    @Resource
    private CateMapper cateMapper;

    @Autowired
    private CateService cateService;

//    @GetMapping("/categories")
//    @ApiOperation(value = "商品分类")
//    public List<Cate> findAll(){
//        return cateMapper.findCate2();
//    }

    @GetMapping("/categories")
    @ApiResponses({
            @ApiResponse(code =200, message ="非HTTP状态码，返回值JSON code字段值，描述：成功")
    })
    @ApiOperation(value = "商品分类")
    public List<Cate> findAll2(){
        return cateService.listWithTree();
    }
}

