package mall.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.entity.Goods;
import mall.entity.GoodsPic;
import mall.mapper.GoodsPicMapper;
import mall.service.GoodsPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
//@RequestMapping("/goods-pic")
@Api(value = "商品图片管理")
public class GoodsPicController {

    @Autowired
    private GoodsPicService goodsPicService;

    @Autowired(required = false)//不再去校验 goodsPicMapper 是否存在
    private GoodsPicMapper goodsPicMapper;
//    @GetMapping("/findGoodsPic_Goods")
//    @ApiOperation(value = "返回商品的详细信息及图片数组")
//    public List<GoodsPic> findGoods_pic_goods(){
//        return goodsPicService.findGoods_pic_goods();
//    }
}

