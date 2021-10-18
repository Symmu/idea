package mall.service;

import mall.entity.Cate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */
public interface CateService extends IService<Cate> {

    List<Cate> listWithTree();
//    List<Cate> test();
}
