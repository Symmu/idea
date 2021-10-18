package mall.controller;


import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.entity.Goods;
import mall.mapper.GoodsMapper;
import mall.service.GoodsService;
import mall.vo.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */
@RestController
@RequestMapping("/goods")
@Api(value = "商品", tags = "商品",description = "商品模块")
public class GoodsController {

    /*
     * 热部署，除修改了方法（crud）,新增类 ，新增字段属性外 都能生效
     * idea自动编译 失败就代表更改不会生效*/
    @Autowired
    private GoodsService goodsService;

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 商品删除
     * @param goods_id
     * @return
     */
    @DeleteMapping("del")
    @ApiOperation(value = "商品删除")
    public Result del(@Param("goods_id")Integer goods_id){
        Result result = new Result();
        try {
            goodsMapper.deleteById(goods_id);
            result.setMsg("删除商品信息成功！");
        }catch (Exception e){
            result.setStatus(false);
            result.setMsg("删除商品信息失败！请稍后再试！");
        }
        return result;
    }


    /**
     * 商品添加或者更新
     * @param file
     * @param goods
     * @return
     */
    @PostMapping(value = "/addorupdate")
    @ApiOperation(value = "商品添加或更新")
    @Transactional
    public Map<String,Object> add3(@RequestParam("file")MultipartFile file, Goods goods, HttpServletRequest request ){
        System.out.println("goods = " + goods);
        Map<String,Object> result = new HashMap<>();

        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/upload_img/";
        String flag = System.currentTimeMillis() + "";
        String fileName = file.getOriginalFilename();

        //将得到的goodscat=1,3,5 分割出来成a=1 b=3 c=5
        String[] strs = goods.getGoodsCat().split(",");
        if (goods.getGoodsCat().length()>2){
            goods.setCatOneId(Integer.valueOf(strs[0]));
            goods.setCatTwoId(Integer.valueOf(strs[1]));
            goods.setCatThreeId(Integer.valueOf(strs[2]));
            goods.setCatId(Integer.valueOf(strs[2]));
            System.out.println("strs[0] = " + strs[0]);
        }

        final int serverPort = request.getServerPort();
        final String serverName = request.getServerName();
        final String filename2 = "/upload_img/";
        final String post = "http://"+serverName+":"+serverPort+filename2;

        System.out.println(post);
        //将数据库的图片名称设置  "http://localhost:8080/upload_img/"
        goods.setGoodsSmallLogo(post+flag+ "-" +fileName);
        goods.setGoodsIntroduce("<div class=\"lazyimg\">"+goods.getGoodsIntroduce()+"</div>");
        try {
            if (goods.getGoodsId()==null){ //添加操作
                FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
                System.out.println(fileName + "--图片上传成功");
                Thread.sleep(1L);
                goodsMapper.insert(goods);
                result.put("status",true);
                result.put("code",1);
                String msg = "商品保存成功！！！";
                result.put("msg",msg);
            }else {//更新操作
                System.out.println("更新操作=============");
                System.out.println("goods = " + goods);
                FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
                goodsMapper.updateById(goods);
                result.put("code",2);
                String msg = "商品编辑成功！！！";
                result.put("msg",msg);
            }
        }catch (Exception e){
            result.put("status",false);
            result.put("系统错误：保存用户信息失败，请稍后再试...","msg");
        }
        System.out.println("GOODSNAME====== " + goods);
//        System.out.println("FILENAME======= " + file);
        return result;
    }

    /**
     * 商品详情
     * @param goods_id
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation(value = "商品详情")
    public List<Goods> findGoodsDetail(@RequestParam("goods_id") Integer goods_id){
        System.out.println("goods_id = " + goods_id);
        return goodsMapper.findGoodsDetail(goods_id);
    }

    /**
     * 商品列表分页
     * @param current 当前页
     * @param size 每页条数
     * @return
     */
    @GetMapping("/search")
    @ApiOperation(value = "商品列表搜索")
    public Page<Goods> findGoods(@RequestParam(required = true,defaultValue = "1")Integer current,
                                 @RequestParam(required = true,defaultValue = "10")Integer size,
                                 @RequestParam(required = true,defaultValue = "1")Integer cat_id){
//        Map<String,Object> map = new HashMap<>();
        current = current  == null?1:current;//如果pageNow为空的时候则为当前页
        size  = size  == null?10:size;
        Page<Goods> iPage = new Page<Goods>(current, size);
        return goodsMapper.findGoods(iPage,cat_id);
    }

    /**
     * 商品搜索-name
     * @param query
     * @return
     */
    @GetMapping("/qsearch")
    @ApiOperation(value = "商品搜索")
    public List<Goods> findGoods(@RequestParam String query){
        return goodsMapper.findSearch("%"+query+"%");
    }


    /**
     * 查询所有
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有的商品信息")
    public Map<String,Object> findgoods(@RequestParam(required = true,defaultValue = "1")Integer current,
                                        @RequestParam(required = true,defaultValue = "10") Integer size){
        Map<String,Object> result = new HashMap<>();
        Page<Goods> page = new Page<>(current,size);
        Page<Goods> goodsPage = goodsService.page(page);
        long total = goodsPage.getTotal();
        List<Goods> goods = goodsPage.getRecords();
        result.put("goods",goods);
        result.put("total",total);
        return result;
    }
    /**
     * 根据goods_cat 查询某个商品
     * @return
     */
    @GetMapping("/one")
    @ApiOperation(value = "根据goods_cat查询某个商品")
    public Page<Goods> one(@RequestParam(required = true,defaultValue = "1")Integer current,
                           @RequestParam(required = true,defaultValue = "10")Integer size,
                           @RequestParam String goods_cat){
        Page<Goods> iPage = new Page<Goods>(current, size);
        return goodsMapper.findOneGood(iPage,goods_cat);
    }

//    @PostMapping("/add")
//    @ApiOperation(value = "商品添加")
//    public int add(@RequestParam Map<String,Object> params, MultipartFile file){
//        Result result =new Result();
//        Goods goods = new Goods();
//        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/upload_img/";
//        String flag = System.currentTimeMillis() + "";
//        String fileName = file.getOriginalFilename();
//        try {
//            FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
//            System.out.println(fileName + "--上传成功");
//            System.out.println("result ========= " + result);
//            result.setStatus(true);
//            Thread.sleep(1L);
//        } catch (Exception e) {
//            System.err.println(fileName + "--文件上传失败");
//            result.setStatus(false);
//        }
//        goods.setGoodsCat(String.valueOf(params.get("goodsCat")));
//        goods.setGoodsName(String.valueOf(params.get("goodsName")));
//        goods.setGoodsPrice((Double.valueOf(String.valueOf(params.get("goodsPrice")))));
//        goods.setGoodsNumber((Integer.valueOf(String.valueOf(params.get("goodsNumber")))));
//
//        String goodsCat = new Gson().toJson(params.get("goodsCat"));
//        String[] strs = goodsCat.split(",");
//        goods.setCatOneId(Integer.parseInt(strs[0].replaceAll("\"","")));
//        goods.setCatTwoId(Integer.parseInt(strs[1].replaceAll("\"","")));
//        goods.setCatThreeId(Integer.parseInt(strs[2].replaceAll("\"","")));
//        goods.setCatId(1000);
////        "http://localhost:8080/upload_img/"
//        String  GoodsSmallLogo = flag+ "-" +fileName;
//        goods.setGoodsSmallLogo(GoodsSmallLogo);
//        System.out.println("goods = " + goods);
//        return goodsMapper.insert(goods);
////        return 1;
//    }
//
//    @PostMapping("/add")
//    @ApiOperation(value = "商品添加")
//    public Result add2(@RequestParam Map<String,Object> params, MultipartFile file){
//        Result result = new Result();
//        String json=new Gson().toJson(params);
//        System.out.println("params = " + params);
//        Goods goods = new Goods();
//        if (params.get("catId").equals("暂无更多分类")) {
//            goods.setCatId(null);
//        }
//        goods = new Gson().fromJson(json, Goods.class);
//
//        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/upload_img/";
//        String flag = System.currentTimeMillis() + "";
//        String fileName = file.getOriginalFilename();
//        try {
////            FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
//            System.out.println(fileName + "--上传成功");
//            System.out.println("result ========= " + result);
//            result.setStatus(true);
//            Thread.sleep(1L);
//        } catch (Exception e) {
//            System.err.println(fileName + "--文件上传失败");
//            result.setStatus(false);
//        }
//        //将得到的goodscat=1,3,5 分割出来成a=1 b=3 c=5
//        String goodsCat = new Gson().toJson(params.get("goodsCat"));
//        String[] strs = goodsCat.split(",");
//        goods.setCatOneId(Integer.parseInt(strs[0].replaceAll("\"","")));
//        goods.setCatTwoId(Integer.parseInt(strs[1].replaceAll("\"","")));
//        goods.setCatThreeId(Integer.parseInt(strs[2].replaceAll("\"","")));
//
//        String  GoodsSmallLogo = flag+ "-" +fileName;
//        goods.setGoodsSmallLogo(GoodsSmallLogo);
////        goods.setCatId(1000);
//
//
////        System.out.println("goods.getGoodsCat() = " + goods.getGoodsCat());
////        System.out.println("json = " + json);
////        System.out.println("goods ===================== " + goods);
////        System.out.println("goodsMapper.insert(goods) =======" + goodsMapper.insert(goods));
//        if (goods.getGoodsId()!=null){
//            //goodsid 不为空则为更新操作
//            System.out.println("更新操作!!!!");
//            System.out.println("goods = " + goods);
//        }else {
//            System.out.println("添加操作!!!!");
//        }
//        return result;
//    }

//    //xml使用
//    @GetMapping("/detail/{goods_id}")
//    @ApiOperation(value = "商品详情")
//    public List<Goods> findGoodsDetail(Integer goods_id){
//        List<Goods> goods_goods_pic = goodsService.findGoodsDetail(goods_id);
//        return goods_goods_pic;
//    }

//    @GetMapping(value = "/page/{pageNo}")
//    public Object page1(@PathVariable("pageNo")Integer pageNo){
//        Page<Goods> Page = new Page<>(pageNo,5);
//        IPage<Goods> goodsIPage = goodsMapper.selectGoodsBypage(Page);
//        return  goodsIPage;
//    }


}

