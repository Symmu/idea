package mall.mapper;

import mall.entity.FloorTitle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import javax.annotation.Resource;
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
public interface FloorTitleMapper extends BaseMapper<FloorTitle> {

    @Select("select *from floor_title")
    @Results(id = "floorMap",value = {
            @Result(id = true,property = "Id",column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "imageSrc", column = "image_src"),
            //定义一对多的关系映射，实现对productlist的封装
            //        @Many注解用于一对多或多对多查询使用
            //        fetchType属性指定内容:指定延时查询FetchType.LAZY或立即查询FetchType.EAGER
            @Result(property = "product_list",column = "id",
                    one = @One(select = "mall.mapper.ProductListMapper.findProductList",fetchType = FetchType.LAZY))
    })
    List<FloorTitle> floordata();

}
