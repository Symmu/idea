package mall.service.Impl;

import mall.entity.ProductList;
import mall.mapper.ProductListMapper;
import mall.service.ProductListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */
@Service
public class ProductListServiceImpl extends ServiceImpl<ProductListMapper, ProductList> implements ProductListService {

}
