package mall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mall.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import mall.entity.GoodsPic;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */

public interface GoodsService extends IService<Goods> {

    //分页 商品详情
//    Page<Goods> findGoodsDetail(IPage<Goods> iPage);

//    //查询商品详情，商品的详细图片
//    List<Goods> findGoodsDetail(Integer goods_id);
}
