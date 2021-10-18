package mall.service.Impl;

import mall.entity.Cate;
import mall.mapper.CateMapper;
import mall.service.CateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */
@Service
public class CateServiceImpl extends ServiceImpl<CateMapper, Cate> implements CateService {
    @Override
    public List<Cate> listWithTree() {
        // 这个类继承了 ServiceImpl
        // 1. 查出所有分类列表
        List<Cate> entities = baseMapper.selectList(null); // 传入 null 代表查询所有

        // 2. 组装成树形结构
        List<Cate> levelMenu = entities.stream()
                .filter(CateEntity -> CateEntity.getCatPid() == 0)
                .map(menu -> {
                    menu.setChildren(getChildren(menu, entities));
                    return menu;
                })
                .collect(Collectors.toList());
        return levelMenu;
    }

//    public List<Cate> test() {
//        List<Cate> entities = baseMapper.selectList(null); // 传入 null 代表查询所有
//        return entities;
//    }

    /**
     * 递归查找所有菜单的子菜单
     *
     * @param root
     * @param all
     * @return
     */
    private List<Cate> getChildren(Cate root, List<Cate> all) {
        List<Cate> children = all.stream()
                .filter(CateEntity -> CateEntity.getCatPid() == (float)(root.getCatId()))
                .map(CateEntity -> {
                    CateEntity.setChildren(getChildren(CateEntity, all));
                    return CateEntity;
                })
                .collect(Collectors.toList());
        return children;
    }


//    @Override
//    public List<Cate> listWithTree() {
//        // 1 查所有分类
//        // baseMapper相当于CategoryDao，
//        List<Cate> entities = baseMapper.selectList(null);
//        // 2 构成三级列表
//        List<Cate> level1Menus = entities.stream().filter(Cate ->
//                Cate.getCatPid() == 0)
//                .map(menu -> {
//                    menu.setChildren(getChildren(menu, entities));
//                    return menu;
//                })
////                .sorted((menu1, menu2) -> {
////                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
////                })
//                .collect(Collectors.toList());
//        return level1Menus;
//    }
//
//    /**
//     * 设置子菜单
//     * @param root 当前要设置子菜单的对象
//     * @param all  所有菜单数据
//     * @return
//     */
//    private List<Cate> getChildren(Cate root, List<Cate> all) {
//        List<Cate> children = all.stream().filter(cate -> {
//            return cate.getCatPid() == root.getCatId();
//        }).map(cate -> {
//            cate.setChildren(getChildren(cate, all));
//            return cate;
//        })
////                .sorted((menu1, menu2) -> {
////            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
////        })
//                .collect(Collectors.toList());
//        return children;
//    }

}
