package mall.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mall.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mall.entity.GoodsPic;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.transaction.annotation.Transactional;

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
public interface GoodsMapper extends BaseMapper<Goods> {

    //查询商品详情
    @Select("select *from goods where goods_id = #{goods_id}")
    @Results(id = "goodsMap",value = {@Result(id = true ,property="goodsId" ,column="goods_id"),
            @Result( property="goodsBigLogo",column="goods_big_logo"),
            @Result( property="goodsCat" ,column="goods_cat"),
            @Result( property="goodsIntroduce" ,column="goods_introduce"),
            @Result( property="goodsName" ,column="goods_name"),
            @Result( property="goodsNumber" ,column="goods_number"),
            @Result( property="goodsPrice" ,column="goods_price"),
            @Result( property="goodsSmallLogo" ,column="goods_small_logo"),
            @Result( property="goodsState" ,column="goods_state"),
            @Result( property="goodsWeight" ,column="goods_weight"),
            @Result( property="isDel" ,column="is_del"),
            @Result( property="addTime" ,column="add_time"),
            @Result( property="updTime" ,column="upd_time"),
            @Result( property="deleteTime" ,column="deleteTime"),
            @Result( property="hotMumber" ,column="hot_mumber"),
            @Result( property="isPromote" ,column="is_promote"),
            @Result( property="catId" ,column="cat_id"),
            @Result( property="catOneId" ,column="cat_one_id"),
            @Result( property="catTwoId" ,column="cat_two_id"),
            @Result( property="catThreeId" ,column="cat_three_id"),
            //配置 查询的方式column代表的传入的字段，-对一查询用one select代表使用的方法的全限定名，
            @Result( property = "pics" ,column = "goods_id",
                    many = @Many(select = "mall.mapper.GoodsPicMapper.findByGoodsPicId",fetchType = FetchType.LAZY))
    })
    List<Goods> findGoodsDetail(Integer goods_id);


    //分页 商品列表
    @Select("select " +
            "goods_id,cat_id,goods_name,goods_price,goods_number,goods_weight,goods_big_logo,goods_small_logo," +
            "add_time,upd_time,hot_mumber,cat_one_id,cat_two_id,cat_three_id " +
            "from goods where cat_id=#{cat_id}")
    Page<Goods> findGoods(IPage<Goods> iPage,Integer cat_id);

    //商品列表搜索
    @Select("select * from goods where goods_name like #{goods_name}")
    List<Goods> findSearch(String query);

    //单个商品查询
    @Select("select *from goods where goods_cat = #{goods_cat}")
    Page<Goods> findOneGood(IPage<Goods> iPage,String goods_cat);

//    @Select("insert into goods(goods_id,cat_id,goods_name,goods_price,goods_number,goods_weight,goods_small_logo,add_time,upd_time,cat_one_id,cat_two_id,cat_three_id,goods_cat) " +
//            "values \n" +
//            "(17,1000,goods_name=#{goods_name},goods_price= #{goods_price},goods_number= #{goods_number},goods_weight= #{goods_weight},goods_small_logo= #{goods_small_logo},now(),now()," +
//            "cat_one_id=#{cat_one_id},cat_two_id=#{cat_two_id},cat_three_id=#{cat_three_id},goods_cat=#{goods_cat});")
//    int insert();
//    @Select("insert into goods values" +
//            "(18, 1000, '21世纪最强电脑', 99999.00, 1, 1, '暂无介绍', '暂无图片', '暂无图片', NULL, NULL, now(),now(), NULL, 0, 0, 1, 3, 5, '1,3,5');\n")
//    int insert();

//    //查询商品详情
//    List<Goods> findGoodsDetail(Integer goods_id);

//    @Select("select * from goods inner join goods_pic on goods_pic.goods_id=goods.goods_id")
//    IPage<Goods> selectGoodsBypage(Page<Goods> page);


//
//    @Select("select * from goods_pic where goods_id=goods_id")
//    @ResultMap(value = "goodsMap")
//    List<GoodsPic> findAll();
}
