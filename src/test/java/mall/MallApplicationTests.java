package mall;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import mall.entity.*;
import mall.mapper.*;
import mall.service.CateService;
import mall.service.OrderGoodsService;
import mall.utils.Util;
import mall.vo.Result;
import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class MallApplicationTests {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private ProductListMapper productListMapper;

    @Resource
    private GoodsPicMapper goodsPicMapper;

    @Resource
    private OrderListMapper OrderListMapper;

    @Resource
    private CateService cateService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Test
    void contextLoads() {
    }
    @Test
    public void testFindAll() {
//        System.out.println("goodsMapper"+goodsMapper.findGoodsDetail2());
        System.out.println("是goodsPicMapper："+goodsPicMapper.findByGoodsPicId(28));
//        System.out.println(goodsMapper.findGoodsDetail());
    }

    //模糊查询
    @Test
    public void testFindSearch(){
        System.out.println(goodsMapper.findSearch("%飞%"));
    }

    //模糊查询
    @Test
    public void testFindSwiper(){
        String query = "飞利浦";
        System.out.println(goodsMapper.findSearch("%"+query+"%"));
    }
    //底部数据_商品
    @Test
    public void testFindProductList(){
//        System.out.println(productListMapper.findProductList(1));
    }
    //根据openid查询商品
    @Test
    public void testFindOrder(){
//        System.out.println(OrderListMapper.findOrder("ogl635Ld-UZGE_ZmTcFvlaEvFxuo"));
    }
    //分类查询
    @Test
    public void testFindCate(){
        System.out.println(cateService.listWithTree());
//        //拿到数据，Data为实体类，下面附有示例，获取数据直接select * from xxx；
//        List<Cate> Cate = cateMapper.findCate();
//        //第一层
//        String catPid = "0";
//        LinkedList<LinkedHashMap> list = new LinkedList<>();
//        for (Cate city:Cate) {
//            if (catPid.equals(city.getCatPid())) {
//                LinkedHashMap<String, Object> Map = new LinkedHashMap<>();
//                Map.put("name", city.getCatName());
//                LinkedList<LinkedHashMap> list2 = new LinkedList<>();
//                Map.put("children", list2);
//                list.add(Map);
//
//                //第二层
//                for (Cate city2 : Cate) {
//                    if (city.getCatId().equals(city2.getCatPid())) {
//                        LinkedHashMap<String, Object> Map2 = new LinkedHashMap<>();
//                        Map2.put("name", city2.getCatName());
//                        LinkedList<LinkedHashMap> list3 = new LinkedList<>();
//                        Map2.put("children", list3);
//                        list2.add(Map2);
//
//
//                        //第三层
//                        for (Cate city3 : Cate) {
//                            if (city2.getCatId().equals(city3.getCatPid())) {
//                                LinkedHashMap<String, Object> Map3 = new LinkedHashMap<>();
//                                Map3.put("name", city3.getCatName());
//                                list3.add(Map3);
//                            }
//                        }
//
//                    }
//                }
//
//
//            }
//        }
//        System.out.println(list);
    }

    //添加用户的操作
    @Test
    public void testAddUser(){
        User user = new User();
        user.setCity("北京");
        user.setAvatarUrl("无");
        user.setCountry("中国");
        user.setNickName("岳不悔");
        user.setOpenid("asdasdasdasd");
//        user.setUserId();
        //user.setUpdateTime(new Date());
        int i = userMapper.insert(user);
        System.out.println(i);
    }

    //添加订单(错误)
    @Test
    public void testAddOrder(){
        OrderList orderList = new OrderList();
        String prefix = "HM";
        //时间戳
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        //生成两位数的随机数
        int random = RandomUtils.nextInt() + 4;

        String order_number = prefix + format.format(new Date()) + random;
        String openid = "ogl635Ld-UZGE_ZmTcFvlaEvFxuo";
        Integer order_price = 10598;
        String consignee_addr = "广东省广州市海珠区新港中路397号";

        orderList.setUser_id(8);
        orderList.setOrder_pay("0");
        orderList.setIs_send(0);
        orderList.setPay_status("0");
//        orderList.setUpdateTime(new Date());
//        orderList.setCreateTime(new Date());
        orderList.setOrder_number(order_number);
        orderList.setOrder_price(order_price);
        orderList.setOpenid(openid);
        orderList.setConsignee_addr(consignee_addr);
//        int i = OrderListMapper.insert(orderList);
        System.out.println(order_number);
//        System.out.println(order_number);
//        System.out.println(OrderListMapper.createOrder(openid, order_number, order_price, consignee_addr));
    }

    //测试map循环
    @Test
    public void testMap(){
        Map<String,Object> params = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        int random = RandomUtils.nextInt() + 4;

        String order_number = "HM" + format.format(new Date()) + random;
        String openid = "ogl635Ld-UZGE_ZmTcFvlaEvFxuo";
        Integer order_price = 10598;
        String consignee_addr = "广东省广州市海珠区新港中路397号";

        params.put("order_number",order_number);
        params.put("openid",openid);
        params.put("order_price",order_price);
        params.put("consignee_addr",consignee_addr);
//            Object key;
//            Object value;
            for(Map.Entry<String,Object> entry : params.entrySet()){
//                key = entry.getKey();
//                value = entry.getValue();
//                System.out.println(key + ":\t" + value);
                if (entry.getKey().equals("order_number")){
                    System.out.println(entry.getKey() + ":\t" + entry.getValue());
                }if (entry.getKey().equals("openid")){
                    System.out.println(entry.getKey() + ":\t" + entry.getValue());
                }if (entry.getKey().equals("order_price")){
                    System.out.println(entry.getKey() + ":\t" + entry.getValue());
                }if (entry.getKey().equals("consignee_addr")){
                    System.out.println(entry.getKey() + ":\t" + entry.getValue());
                }

            }
    }
    //测试map2循环
    @Test
    public void testMap2(){
//订单的第二件商品
        Map<String,Object> params2_1 = new HashMap<>();
        Integer goods_id2_1 = 57419;
        String goods_name2_1 = "创维（Skyworth）65H7 65英寸25核全面屏HDR 4K超高清智能电视";
        Integer goods_number2_1 = 2;
        Integer goods_price2_1= 6599;
//        Integer goods_total_price2_1 = 23796;

        params2_1.put("goods_id2_1",goods_id2_1);
        params2_1.put("goods_name2_1",goods_name2_1);
        params2_1.put("goods_number2_1",goods_number2_1);
        params2_1.put("goods_price2_1",goods_price2_1);
//        params2_1.put("goods_total_price2_1",goods_total_price2_1);
//订单
        Map<String,Object> params2 = new HashMap<>();
        Integer goods_id = 47849;
        String goods_name = "海信(Hisense)LED65EC500U 65英寸超高清4K大屏 HDR 人工智能液晶平板电视 丰富影视教育资源";
        Integer goods_number = 2;
        Integer goods_price = 5299;
//        Integer goods_total_price = 23796;

        params2.put("goods_id",goods_id);
        params2.put("goods_name",goods_name);
        params2.put("goods_number",goods_number);
        params2.put("goods_price",goods_price);
//        params2.put("goods_total_price",goods_total_price);
        params2.put("params2_1",params2_1);

//        OrderList orderList = new OrderList();
        OrderGoods orderGoods = new OrderGoods();
        for(Map.Entry<String,Object> entry : params2.entrySet()){
            if (entry.getKey().equals("goods_id")){
                orderGoods.setGoods_id((Integer) entry.getValue());
            }if (entry.getKey().equals("goods_name")){
                orderGoods.setGoods_name((String) entry.getValue());
            }if (entry.getKey().equals("goods_number")){
                orderGoods.setGoods_number((Integer) entry.getValue());
            }if (entry.getKey().equals("goods_price")){
                orderGoods.setGoods_price((Integer) entry.getValue());
            }if (entry.getKey().equals("goods_total_price")){
                orderGoods.setGoods_total_price((Integer) entry.getValue());
            }
//            if (entry.getKey().equals("goods_total_price")){
//                orderGoods.setGoodsTotalPrice((Integer) entry.getValue());
//            }
        }
//        System.out.println("goods_id:"+orderGoods.getGoodsId()+"\n"+
//                           "goods_name:"+orderGoods.getGoodsName()+"\n"+
//                           "goods_number:"+orderGoods.getGoodsNumber()+"\n"+
//                           "goods_price:"+orderGoods.getGoodsPrice()+"\n"+
//                           "goods_total_price:"+orderGoods.getGoodsTotalPrice());


        Map<String,Object> params = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        int random = RandomUtils.nextInt() + 4;

        String order_number = "HM" + format.format(new Date()) + random;
        String openid = "ogl635Ld-UZGE_ZmTcFvlaEvFxuo";
        Integer order_price = 10598;
        String consignee_addr = "广东省广州市海珠区新港中路397号";

        params.put("order_number",order_number);
        params.put("openid",openid);
        params.put("order_price",order_price);
        params.put("consignee_addr",consignee_addr);
        params.put("goods",params2);
//            Object key;
//            Object value;
        for(Map.Entry<String,Object> entry : params.entrySet()){
//                key = entry.getKey();
//                value = entry.getValue();
//                System.out.println(key + ":\t" + value);
//            if (entry.getKey().equals("order_number")){
//                System.out.println(entry.getKey() + ":\t" + entry.getValue());
//            }if (entry.getKey().equals("openid")){
//                System.out.println(entry.getKey() + ":\t" + entry.getValue());
//            }if (entry.getKey().equals("order_price")){
//                System.out.println(entry.getKey() + ":\t" + entry.getValue());
//            }if (entry.getKey().equals("consignee_addr")){
//                System.out.println(entry.getKey() + ":\t" + entry.getValue());
//            }
            if (entry.getKey().equals("goods")){
                for (Map.Entry<String,Object>entry1 :params2.entrySet()){
                    System.out.println(entry1.getKey() + ":\t" + entry1.getValue());
                }

            }
            System.out.println(entry.getKey() + ":\t" + entry.getValue());
        }



//        int result = orderGoodsMapper.addOrderGoods(goods_name,goods_id,goods_number,goods_price,goods_total_price);
    }
    //测试map3循环(最终)
    @Test
    public void testMap3(){
//订单商品1
        Map<String,Object> goods_1 = new HashMap<>();
        Integer goods_id_1 = 57419;
        String goods_name_1 = "创维（Skyworth）65H7 65英寸25核全面屏HDR 4K超高清智能电视";
        Integer goods_number_1 = 2;
        Integer goods_price_1= 6599;
        Integer goods_total_price_1= 13198;
//订单商品2
        Map<String,Object> goods_2 = new HashMap<>();
        Integer goods_id_2 = 47849;
        String goods_name_2 = "海信(Hisense)LED65EC500U 65英寸超高清4K大屏 HDR 人工智能液晶平板电视 丰富影视教育资源";
        Integer goods_number_2 = 2;
        Integer goods_price_2 = 5299;
        Integer goods_total_price_2= 10598;

        goods_1.put("goods_id",goods_id_1);
        goods_1.put("goods_name",goods_name_1);
        goods_1.put("goods_number",goods_number_1);
        goods_1.put("goods_price",goods_price_1);
        goods_1.put("goods_total_price",goods_total_price_1);
//        goods_1.put("openid","ogl635Ld-UZGE_ZmTcFvlaEvFxuo");

        goods_2.put("goods_id",goods_id_2);
        goods_2.put("goods_name",goods_name_2);
        goods_2.put("goods_number",goods_number_2);
        goods_2.put("goods_price",goods_price_2);
        goods_2.put("goods_total_price",goods_total_price_2);
//        goods_2.put("openid","ogl635Ld-UZGE_ZmTcFvlaEvFxuo");
//用一个数组将两个订单商品装起来
        Object[] goods = new Object[2];
        goods[0] = goods_1;
        goods[1] = goods_2;
        Gson gson=new Gson();
        String goodssss = gson.toJson(goods);
        System.out.println("goods=========="+goods);
        System.out.println("goodssss=========="+goodssss);
        for (int i = 0; i < goods.length; i++) {
            Map<String ,Object> temp = (Map<String, Object>) goods[i];
//            System.out.println("temp="+temp.toString());
        }
//订单参数
        Map<String,Object> params = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        int random = RandomUtils.nextInt() + 4;

        String order_number = "HM" + format.format(new Date()) + random;
        String openid = "ogl635Ld-UZGE_ZmTcFvlaEvFxuo";
        Integer order_price = 23796;
        String consignee_addr = "广东省广州市海珠区新港中路397号";

        params.put("order_number",order_number);
        params.put("openid",openid);
        params.put("order_price",order_price);
        params.put("consignee_addr",consignee_addr);
        params.put("goods",goods);

//        OrderGoods orderGoods = new OrderGoods();
        List<OrderGoods> list = new ArrayList<>();
        List<OrderGoods> orderGoodsList = new ArrayList<>();
//        Object[] goods2= new Object[0];
//        OrderGoods orderGoods2 = new OrderGoods();
        OrderGoods orderGoods = new OrderGoods();
        for(Map.Entry<String,Object> entry : params.entrySet()){
            if (entry.getKey().equals("order_number")){
                System.out.println(entry.getKey() + ":\t" + entry.getValue());
            }if (entry.getKey().equals("openid")){
                orderGoods.setOpenid((String) entry.getValue());
                System.out.println(entry.getKey() + ":\t" + entry.getValue());
            }if (entry.getKey().equals("order_price")){
                System.out.println(entry.getKey() + ":\t" + entry.getValue());
            }if (entry.getKey().equals("consignee_addr")){
                System.out.println(entry.getKey() + ":\t" + entry.getValue());
            }
            if (entry.getKey().equals("goods")){
//                Object o[] =params.values().toArray();
                Object[] goods2 = (Object[]) entry.getValue();
                for (int i = 0; i < goods2.length; i++) {
                    OrderGoods goods1 = new OrderGoods();
                    goods1.setOrder_id(4);
                    goods1.setOpenid(orderGoods.getOpenid());
                    System.out.println("orderGoods.getOpenid():\t" + orderGoods.getOpenid());
                    Map<String ,Object> temp = (Map<String, Object>) goods2[i];
                    System.out.println("temp="+temp.toString());
                    for(Map.Entry<String,Object> entry1 : temp.entrySet()){
                        System.out.println(entry1.getKey() + ":\t" + entry1.getValue());
//                        if (entry1.getKey().equals("openid")){goods1.setOpenid((String) entry1.getValue());}
                        if (entry1.getKey().equals("goods_name")){goods1.setGoods_name((String) entry1.getValue());}
                        if (entry1.getKey().equals("goods_number")){goods1.setGoods_number((Integer) entry1.getValue());}
                        if (entry1.getKey().equals("goods_price")){goods1.setGoods_price((Integer) entry1.getValue());}
                        if (entry1.getKey().equals("goods_total_price")){goods1.setGoods_total_price((Integer) entry1.getValue());}
                    }
                        list.add(goods1);
                }
//                System.out.println(entry.getKey() + ":\t" + entry.getValue());
//            Gson gson1 = new GsonBuilder().serializeNulls().create();
//            String s = gson1.toJson(entry.getValue());
//            StringUtil UN_ = new StringUtil();//驼峰转换
//            String s1 = UN_.camelCaseName(s);
//            orderGoodsList = gson.fromJson(s1, new TypeToken<List<OrderGoods>>() {}.getType());
//            System.out.println("s = " + s);
//            System.out.println("s1 = " + s1);
//            System.out.println("orderGoodsList = \t" + orderGoodsList);
//                goods2 = (Object[]) entry.getValue();
//                Gson gson1 = new Gson();
//                String json = gson1.toJson(goods2);
//                List<OrderGoods> goods2list = gson.fromJson(json, new TypeToken<List<OrderGoods>>() {}.getType());
//                System.out.println("goods2json = \t" + json);
//                System.out.println("goods2list = \t" + goods2list);
//                for (int i = 0; i < goods2.length; i++) {
//                    Map<String ,Object> temp = (Map<String, Object>) goods2[i];
////                    System.out.println("temp="+temp.toString());
//                    Gson gson2=new Gson();
//                    String str=gson2.toJson(temp);
//                    OrderGoods orderGoods1 = gson.fromJson(str, OrderGoods.class);
////                    List<OrderGoods> strlist = gson.fromJson(str, new TypeToken<List<OrderGoods>>() {}.getType());
//                    System.out.println("str=\t"+str);
//                    System.out.println("orderGoods1=\t"+orderGoods1);
//                    System.out.println("strlist="+strlist);

//                    list.add((OrderGoods) temp);
//                    for(Map.Entry<String,Object> entry1 : temp.entrySet()){
//
////                        if (entry1.getKey().equals("goods_id")){
////                            Object[] goods3 = (Object[]) entry.getValue();
////                            for (int j = 0; j < goods3.length; j++) {
////                                Map<String ,Object> temp2 = (Map<String, Object>) goods3[j];
////                                for(Map.Entry<String,Object> entry2 : temp2.entrySet()){
////                                    System.out.println(entry2.getKey() + "============:\t" + entry2.getValue());
//////                                    orderGoods.setGoodsId((Integer) entry2.getValue());
////                                }
////                            }
////                            Integer  goods_id_11 = (Integer) entry1.getValue();
////                            orderGoods.setGoodsId((Integer) entry1.getValue());
////                            System.out.println(entry.getKey() + ":\t" + entry.getValue());
////                            list.add(orderGoods);
////                            System.out.println("goods_id_11" + ":\t"+goods_id_11);
////                            orderGoods2.setGoodsId((Integer) entry1.getValue());
////                        }
////                        if (entry1.getKey().equals("goods_name")){
////                            Object[] goods3 = (Object[]) entry.getValue();
////                            for (int j = 0; j < goods3.length; j++) {
////                                Map<String ,Object> temp2 = (Map<String, Object>) goods3[j];
////                                for(Map.Entry<String,Object> entry2 : temp2.entrySet()){
//////                                    System.out.println(entry2.getKey() + "============:\t" + entry2.getValue());
////                                    orderGoods.setGoodsName((String) entry2.getValue());
////                                }
////                            }
////////                            String  goods_name_11 = (String) entry1.getValue();
//////                            orderGoods.setGoodsName((String) entry1.getValue());
//////                            System.out.println(entry.getKey() + ":\t" + entry.getValue());
////////                            list.add(orderGoods);
////////                            System.out.println("goods_id_11" + ":\t"+goods_name_11);
////////                            orderGoods2.setGoodsName((String) entry1.getValue());
////                        }if (entry1.getKey().equals("goods_number")){
////                            Object[] goods3 = (Object[]) entry.getValue();
////                            for (int j = 0; j < goods3.length; j++) {
////                                Map<String ,Object> temp2 = (Map<String, Object>) goods3[j];
////                                for(Map.Entry<String,Object> entry2 : temp2.entrySet()){
//////                                    System.out.println(entry2.getKey() + "============:\t" + entry2.getValue());
////                                    orderGoods.setGoodsNumber((Integer) entry2.getValue());
////                                }
////                            }
////////                            Integer  goods_number_11 = (Integer) entry1.getValue();
//////                            orderGoods.setGoodsNumber((Integer) entry1.getValue());
//////                            System.out.println(entry.getKey() + ":\t" + entry.getValue());
////////                            list.add(orderGoods);
////////                            System.out.println("goods_id_11" + ":\t"+goods_number_11);
////////                            orderGoods2.setGoodsNumber((Integer) entry1.getValue());
////                        }if (entry1.getKey().equals("goods_price")){
////                            Object[] goods3 = (Object[]) entry.getValue();
////                            for (int j = 0; j < goods3.length; j++) {
////                                Map<String ,Object> temp2 = (Map<String, Object>) goods3[j];
////                                for(Map.Entry<String,Object> entry2 : temp2.entrySet()){
//////                                    System.out.println(entry2.getKey() + "============:\t" + entry2.getValue());
////                                    orderGoods.setGoodsPrice((Integer) entry2.getValue());
////                                }
////                            }
////////                            Integer  goods_price_11 = (Integer) entry1.getValue();
//////                            orderGoods.setGoodsPrice((Integer) entry1.getValue());
//////                            System.out.println(entry.getKey() + ":\t" + entry.getValue());
////////                            list.add(orderGoods);
////////                            System.out.println("goods_id_11" + ":\t"+goods_price_11);
////////                            orderGoods2.setGoodsPrice((Integer) entry1.getValue());
////                        }if (entry1.getKey().equals("goodsTotalPrice")){
////                            Object[] goods3 = (Object[]) entry.getValue();
////                            for (int j = 0; j < goods3.length; j++) {
////                                Map<String ,Object> temp2 = (Map<String, Object>) goods3[j];
////                                for(Map.Entry<String,Object> entry2 : temp2.entrySet()){
//////                                    System.out.println(entry2.getKey() + "============:\t" + entry2.getValue());
////                                    orderGoods.setGoodsTotalPrice((Integer) entry2.getValue());
////                                }
////                            }
////////                            Integer  goods_price_11 = (Integer) entry1.getValue();
//////                            orderGoods.setGoodsTotalPrice((Integer) entry1.getValue());
//////                            System.out.println(entry.getKey() + ":\t" + entry.getValue());
////////                            list.add(orderGoods);
////////                            System.out.println("goods_id_11" + ":\t"+goods_price_11);
////////                            orderGoods2.setGoodsPrice((Integer) entry1.getValue());
////                        }
//                        list.add(orderGoods);
//                        System.out.println("循环次数"+"999");
//                        System.out.println(entry1.getKey() + ":\t" + entry1.getValue());
//                    }
//                }
//                System.out.println(list);
            }

//            list.add(orderGoods);
        }
//        orderGoods.setOrderId(4);
//        orderGoodsList.add(orderGoods);
//        System.out.println(goods2);
//        Gson gson1 = new Gson();
//        String json = gson1.toJson(goods2);
//        List<OrderGoods> goods2list = gson.fromJson(json, new TypeToken<List<OrderGoods>>() {}.getType());
//        System.out.println("goods2list = \t" + goods2list);
//        System.out.println("goods2json = \t" + json);
//        System.out.println(orderGoodsService.saveBatch(list));
        System.out.println("params = " + params);
        System.out.println("list = " + list);
        for (OrderGoods orderGoods2 : orderGoodsList) {
            System.out.println("orderGoods2 = " + orderGoods2);
        }

//        List listWithoutDuplicates = list.stream().distinct().collect(Collectors.toList());
//            for (OrderGoods orderGoods1 : list.stream().distinct().collect(Collectors.toList())) {
//                System.out.println("orderGoods1: "+orderGoods1);
//            }
            //将数据插入数据库
//        System.out.println(orderGoodsService.saveBatch(listWithoutDuplicates));
//            System.out.println("goods_id:"+orderGoods.getGoodsId()+"\n"+
//                    "goods_name:"+orderGoods.getGoodsName()+"\n"+
//                    "goods_number:"+orderGoods.getGoodsNumber()+"\n"+
//                    "goods_price:"+orderGoods.getGoodsPrice());
//            System.out.println(entry.getKey() + ":\t" + entry.getValue());
    }
    //简单添加
    @Test
    public void  addOrder(){
        //订单商品1
        Map<String,Object> goods_1 = new HashMap<>();
        Integer goods_id_1 = 57419;
        String goods_name_1 = "创维（Skyworth）65H7 65英寸25核全面屏HDR 4K超高清智能电视";
        Integer goods_number_1 = 2;
        Integer goods_price_1= 6599;
        Integer goods_total_price_1= 13198;
//订单商品2
        Map<String,Object> goods_2 = new HashMap<>();
        Integer goods_id_2 = 47849;
        String goods_name_2 = "海信(Hisense)LED65EC500U 65英寸超高清4K大屏 HDR 人工智能液晶平板电视 丰富影视教育资源";
        Integer goods_number_2 = 2;
        Integer goods_price_2 = 5299;
        Integer goods_total_price_2= 10598;

        goods_1.put("goods_id",goods_id_1);
        goods_1.put("goods_name",goods_name_1);
        goods_1.put("goods_number",goods_number_1);
        goods_1.put("goods_price",goods_price_1);
        goods_1.put("goods_total_price",goods_total_price_1);
        goods_1.put("openid","ogl635Ld-UZGE_ZmTcFvlaEvFxuo");

        goods_2.put("goods_id",goods_id_2);
        goods_2.put("goods_name",goods_name_2);
        goods_2.put("goods_number",goods_number_2);
        goods_2.put("goods_price",goods_price_2);
        goods_2.put("goods_total_price",goods_total_price_2);
        goods_2.put("openid","ogl635Ld-UZGE_ZmTcFvlaEvFxuo");
//用一个数组将两个订单商品装起来
        Object[] goods = new Object[2];
        goods[0] = goods_1;
        goods[1] = goods_2;
        Gson gson=new Gson();
        String goodssss = gson.toJson(goods);
        System.out.println("goodssss=========="+goodssss);
        for (int i = 0; i < goods.length; i++) {
            Map<String ,Object> temp = (Map<String, Object>) goods[i];
//            System.out.println("temp="+temp.toString());
        }
//订单参数
        Map<String,Object> params = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        int random = RandomUtils.nextInt() + 4;

        String order_number = "HM" + format.format(new Date()) + random;
        String openid = "ogl635Ld-UZGE_ZmTcFvlaEvFxuo";
        Integer order_price = 23796;
        String consignee_addr = "广东省广州市海珠区新港中路397号";

        params.put("order_number",order_number);
        params.put("openid",openid);
        params.put("order_price",order_price);
        params.put("consignee_addr",consignee_addr);
        params.put("goods",goods);

        OrderGoods orderGoods = new OrderGoods();
        List<OrderGoods> list = new ArrayList<>();
        List<OrderGoods> orderGoodsList = new ArrayList<>();
        Object[] goods2= new Object[0];

    }
    //创建订单，
    @Test
    void orders(){

        List<OrderGoods> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setGoods_id(47849+i);
            orderGoods.setGoods_name("海信(Hisense)LED65EC500U 65英寸超高清4K大屏 HDR 人工智能液晶平板电视 丰富影视教育资源");
            orderGoods.setGoods_number(2);
            orderGoods.setGoods_price(5299);
            list.add(orderGoods);
        }
        Gson gson = new Gson();
        String ss = gson.toJson(list);
        List<OrderGoods> ss_list = gson.fromJson(ss, new TypeToken<List<OrderGoods>>() {}.getType());
//        for (Object object : ss_list) {
//            System.out.println("把jsonList转化为一个list对象"+object.toString());
//        }

        System.out.println("ss:\t"+ss);
        System.out.println("ss_list:\t"+ss_list);

//        System.out.println(orderGoodsMapper.mallOrderGoods(list));
        System.out.println("list:\t"+list);
//        for (OrderGoods orderGoods : list) {
//            System.out.println("orderGoods:\t"+orderGoods);
//        }
//        System.out.println(orderGoodsService.saveBatch(list));
    }
    //对传过来的object取值
    @SneakyThrows
    @Test
    void ObjectTest(){
        Object object = "{\n" +"\t\"consignee_addr\": \"广东省广州市海珠区新港中路397号\",\n" +"\t\"openid\": \"ogl635Ld-UZGE_ZmTcFvlaEvFxuo\",\n" +"\t\"order_price\": 10598,\n" +"\t\"order_number\": \"HM202103292043101014826027\",\n" +"\t\"goods\": [{\n" +"\t\t\t\"goods_id_1\": 57419,\n" +"\t\t\t\"goods_name_1\": \"创维（Skyworth）65H7 65英寸25核全面屏HDR 4K超高清智能电视\",\n" + "\t\t\t\"goods_price_1\": 6599,\n" +"\t\t\t\"goods_number_1\": 2,\n" +"\t\t\t\"goodsTotalPrice_1\": 13198\n" +"\t\t},\n" +"\t\t{\n" +"\t\t\t\"goods_id_2\": 47849,\n" +"\t\t\t\"goods_name_2\": \"海信(Hisense)LED65EC500U 65英寸超高清4K大屏 HDR 人工智能液晶平板电视 丰富影视教育资源\",\n" +"\t\t\t\"goodsTotalPrice_2\": 10598,\n" +"\t\t\t\"goods_price_2\": 5299,\n" +"\t\t\t\"goods_number_2\": 2\n" +"\t\t}\n" +"\t]\n" +"}";
        System.out.println("object = " + object);
        Map<String,Object> goods  = new Util().getObjectToMap(object);
//        System.out.println("goods = " + goods);
        for(Map.Entry<String,Object> entry : goods.entrySet()){
            System.out.println(entry.getKey() + "===============:\t" + entry.getValue());
        }
    }
    //java 获取json字符串中key对应的值
    @Test
    void JsonTest (){
        Map<String,Object> goods_1 = new HashMap<>();
        Integer goods_id_1 = 57419;
        String goods_name_1 = "创维（Skyworth）65H7 65英寸25核全面屏HDR 4K超高清智能电视";
        Integer goods_number_1 = 2;
        Integer goods_price_1= 6599;
        Integer goods_total_price_1= 13198;
        goods_1.put("goods_id",goods_id_1);
        goods_1.put("goods_name",goods_name_1);
        goods_1.put("goods_number",goods_number_1);
        goods_1.put("goods_price",goods_price_1);
        goods_1.put("goods_total_price",goods_total_price_1);
        goods_1.put("openid","ogl635Ld-UZGE_ZmTcFvlaEvFxuo");
        Map<String,Object> goods_2 = new HashMap<>();
        Integer goods_id_2 = 47849;
        String goods_name_2 = "海信(Hisense)LED65EC500U 65英寸超高清4K大屏 HDR 人工智能液晶平板电视 丰富影视教育资源";
        Integer goods_number_2 = 2;
        Integer goods_price_2 = 5299;
        Integer goods_total_price_2= 10598;
        goods_2.put("goods_id",goods_id_2);
        goods_2.put("goods_name",goods_name_2);
        goods_2.put("goods_number",goods_number_2);
        goods_2.put("goods_price",goods_price_2);
        goods_2.put("goods_total_price",goods_total_price_2);
        Object[] goods = new Object[2];
        goods[0] = goods_1;
        goods[1] = goods_2;

        Gson gson=new Gson();
        String goodssss = gson.toJson(goods);
        System.out.println("goods = " + goods);
        System.out.println("goodssss=========="+goodssss);
    }
    @Test
    void log4j(){
        Logger logger= Logger.getLogger(Test.class); // 获取logger实例
        logger.info("普通Info信息");
        logger.debug("调试debug信息");
    }
// 查询所有用户具有的订单
    @Test
    public void testUserWithOrders(){
        // 3.调用方法
        List<OrderList> orderLists = OrderListMapper.findMyOrders("ogl635Ld-UZGE_ZmTcFvlaEvFxuo");
        for (OrderList orderList : orderLists) {
            System.out.println("orderList:\t"+orderList);
        }
    }
    //添加用户判断
    @Test
    void TestAdduser(){
        User user = new User();
        user.setOpenid("ogl635Ld-UZGE_ZmTcFvlaEvFxuo");
        System.out.println("user.getOpenid() = " + user.getOpenid());
        String openid ="";
        Result result = new Result();
        try {
//            System.out.println("selectList = " + userMapper.selectList(new QueryWrapper<User>().eq("openid", user.getOpenid())));
            for (User user2 : userMapper.selectList(null)) {
                if (user2.getOpenid().equals("user.getOpenid()")){
                    openid=user2.getOpenid();
//                    result.setMsg("用户信息已存在！！！");
//                    System.out.println("result = " + result);
//                    break;
                }
//                else{
////                    result.setMsg("保存用户信息成功！！！");
////                    System.out.println("result = " + result);
////                    break;
//                }
            }
            if(user.getOpenid().equals(openid)){
                result.setMsg("用户信息已存在！！！");
                System.out.println("result = " + result);
            }else{
                result.setMsg("保存用户信息成功！！！");
                System.out.println("result = " + result);
            }
        }catch (Exception e){
            result.setStatus(false);
            result.setMsg("系统错误：保存用户信息失败，请稍后再试...");
        }
    }
    //添加商品
    @Test
    void addGood(){
        Goods goods = new Goods();
        String goodsCat="1,2,3";
//        Goods goods = new Gson().fromJson(goodsCat, Goods.class);
        String[] strs = goodsCat.split(",");
        for (int i =0;i<strs.length;i++){
            goods.setCatOneId(Integer.valueOf(strs[0]));
            goods.setCatTwoId(Integer.valueOf(strs[1]));
            goods.setCatThreeId(Integer.valueOf(strs[2]));
        }
//        goods.setCatOneId(Integer.parseInt(strs[0].replaceAll("\"","")));
//        goods.setCatTwoId(Integer.parseInt(strs[1].replaceAll("\"","")));
//        goods.setCatThreeId(Integer.parseInt(strs[2].replaceAll("\"","")));
        goods.setGoodsId(57435);
        System.out.println("goodsMapper.updateById(goods) = " + goodsMapper.updateById(goods));
        System.out.println("goods = " + goods);
    }
}
