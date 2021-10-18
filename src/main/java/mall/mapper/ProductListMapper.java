package mall.mapper;

import mall.entity.ProductList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface ProductListMapper extends BaseMapper<ProductList> {

    @Select("select *from product_list where parent_id = #{id}")
    List<ProductList> findProductList();
}
