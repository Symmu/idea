package mall.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mall.entity.Goods;
import mall.entity.GoodsPic;
import mall.mapper.GoodsPicMapper;
import mall.service.GoodsPicService;
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
public class GoodsPicServiceImpl extends ServiceImpl<GoodsPicMapper, GoodsPic> implements GoodsPicService {

    @Resource
    private GoodsPicMapper goodsPicMapper;


//    @Override
//    public List<GoodsPic> findAllGoodsPic(Integer goods_id) {
//        return  goodsPicMapper.findByGoodsPicId();
//    }


//    //查询商品详情，商品的详细图片
//    @Override
//    public List<GoodsPic> findGoods_pic_goods() {
//        return goodsPicMapper.findGoods_pic_goods();
//    }


}
