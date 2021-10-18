package mall.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mall.entity.Goods;
import mall.mapper.GoodsMapper;
import mall.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.sql.Wrapper;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther:shuang
 * @Date:2021/3/15 - 03 - 15 - 14:44:27
 * @Description: vue_cli
 * @versin:1.0
 */
@SpringBootTest
class GoodsServiceImplTest {
    @Autowired
    private GoodsService goodsService;
    @Resource
    private GoodsMapper goodsMapper;

    @Test
    void findGoods_goods_pic() {
//            System.out.println(goodsService.findGoodsDetail(/1/2));
        Page<Goods> page = new Page<>(2,5);// current:2,size:5
        goodsMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }
}