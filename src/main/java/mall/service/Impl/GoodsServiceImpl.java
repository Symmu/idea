package mall.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mall.entity.Goods;
import mall.mapper.GoodsMapper;
import mall.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

//分页
//    public Page<Goods> findGoodsDetail(IPage<Goods> iPage){
//        return goodsMapper.findGoodsDetail(iPage);
//    }

    //查询商品详情
//    @Override
//    public List<Goods> findGoodsDetail(Integer goods_id) {
//        return goodsMapper.findGoodsDetail(goods_id);
//    }

}
