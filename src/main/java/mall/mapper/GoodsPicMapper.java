package mall.mapper;

import mall.entity.Goods;
import mall.entity.GoodsPic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */
@Mapper
public interface GoodsPicMapper extends BaseMapper<GoodsPic> {
//    @Select("select * from goods inner join goods_pic on goods_pic.goods_id=goods.goods_id")
//    List<GoodsPic> findAllGoodsPic(Integer goods_id);

//    //查询商品详情，商品的详细图片
//    List<GoodsPic> findGoods_pic_goods();


    @Select("select * from goods_pic where goods_id = #{goods_id}")
    List<GoodsPic> findByGoodsPicId(Integer goods_id);
}
