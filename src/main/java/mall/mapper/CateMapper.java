package mall.mapper;

import mall.entity.Cate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 * 设置了@MapperScan 这里不用加@Mapper
 */
public interface CateMapper extends BaseMapper<Cate> {

    //获取一级分类
    @Select("SELECT *from cate ")
//    @Results(id = "cateMap",value = {@Result(id = true, property = "catId",column = "cat_id"),
//            @Result(property = "catName",column = "cat_name"),
//            @Result(property = "catPid",column = "cat_pid"),
//            @Result(property = "catLevel",column = "cat_level"),
//            @Result(property = "catDeleted",column = "cat_deleted"),
//            @Result(property = "catIcon",column = "cat_icon"),
//    })
    List<Cate> findCate();

    @Select("SELECT *\n" +
            "FROM cate a \n" +
            "INNER JOIN cate b \n" +
            "ON a.cat_pid=b.cat_id \n" +
            "INNER JOIN cate c \n" +
            "ON b.cat_pid=c.cat_id ")
    List<Cate> findCate2();
}
