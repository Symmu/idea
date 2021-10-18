package mall.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.entity.OrderGoods;
import mall.entity.OrderList;
import mall.mapper.OrderListMapper;
import mall.service.OrderGoodsService;
import mall.vo.Result;
import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
@RequestMapping("/my")
@Api(value = "订单", tags = "订单",description = "订单模块")
public class OrderListController {
    @Resource
    private OrderListMapper orderListMapper;
    @Autowired
    private OrderGoodsService orderGoodsService;


    @GetMapping("/Allorders")
    @ApiOperation(value = "所有的订单查询")
    public Page<OrderList> allOrders(@RequestParam(required = true,defaultValue = "1")Integer current,
                                     @RequestParam(required = true,defaultValue = "10") Integer size){
        if(size==100){
//            Page<OrderList> orderPage = new Page<OrderList>();
            System.out.println("+++++++++++++++++++");
            return orderListMapper.findAllOrders(new Page<OrderList>(current,size));
        }
        else
//        Page<OrderList> orderPage = new Page<OrderList>(current, size);
            System.out.println("---------------------------");
        return orderListMapper.findAllOrders(new Page<OrderList>(current, size));
    }

    /**
     * 根据openid订单查询
     * @param openid
     * @param type
     * @return
     */
    @GetMapping("/orders")
    @ApiOperation(value = "根据openid订单查询")
    public Object findOrder(@RequestParam("openid")String openid,
                            @RequestParam(required = true,defaultValue = "1")Integer type) {
        Result result = new Result();
        if (type==1){
            //获取所有订单
            if (orderListMapper.findMyOrders(openid).size()!=0){
                return orderListMapper.findMyOrders(openid);
            }else {
                result.setMsg("暂时没有购买商品");
                return result;
            }
        }if (type==2){
            //获取所有订单
            if (orderListMapper.findMyOrders(openid).size()!=0){
                return orderListMapper.Onpay(openid);
            }else {
                result.setMsg("暂时没有支付");
                return result;
            }
        }if (type==3){
            //获取所有订单
            if (orderListMapper.findMyOrders(openid).size()!=0){
                return orderListMapper.Issend(openid);
            }else {
                result.setMsg("已发货");
                return result;
            }
        }
        result.setStatus(false);
        result.setMsg("获取信息失败");
        return result;
    }

    @PostMapping("pay")
    @ApiOperation(value = "订单待付款支付")
    public Result pay(@RequestBody OrderList orderList){
        Result result = new Result();
        UpdateWrapper<OrderList> orderListUpdateWrapper = new UpdateWrapper<>();
        orderListUpdateWrapper.eq("order_id",orderList.getOrder_id());
        System.out.println("orderList.getOrder_id()= " + orderList.getOrder_id());

        orderList.setOpenid(orderList.getOpenid());
        orderList.setOrder_pay("1");
        orderList.setPay_status("1");
        orderList.setIs_send(1);
        int update = orderListMapper.update(orderList, orderListUpdateWrapper);
        System.out.println("update ============" + update);
        if (update ==1){
            result.setMsg("支付成功！");
            result.setStatus(true);
        }else {
            result.setMsg("支付失败！");
            result.setStatus(false);
        }
        System.out.println("orderList = " + orderList);
        return result;
    }
    /**
     * 订单删除
     * @param order_id
     * @return
     */
    @DeleteMapping("del")
    @ApiOperation(value = "商品删除")
    public Result del(@Param("order_id")Integer order_id){
        Result result = new Result();
        try {
            orderListMapper.deleteById(order_id);
            result.setMsg("删除商品信息成功！");
        }catch (Exception e){
            result.setStatus(false);
            result.setMsg("删除商品信息失败！请稍后再试！");
        }
        return result;
    }

    /**
     * 创建订单
     * @param params
     * @return
     */
    @PostMapping("/orders/create")
    @ApiOperation(value = "创建订单")
    public String test(@RequestBody Map<String,Object> params) {
        OrderList orderList = new OrderList();
        int result=0;
        List<OrderGoods> orderGoodsList = new ArrayList<>();
        List<Map<Object, Object>>mapList =new ArrayList<Map<Object, Object>>();
        for(Map.Entry<String,Object> entry : params.entrySet()){
            //对传过来的数据进行赋值
            if (entry.getKey().equals("openid")){orderList.setOpenid((String) entry.getValue());}
            if (entry.getKey().equals("order_price")){orderList.setOrder_price((Integer) entry.getValue());}
            if (entry.getKey().equals("consignee_addr")){orderList.setConsignee_addr((String) entry.getValue());}
            if (entry.getKey().equals("order_pay")){orderList.setOrder_pay(String.valueOf(entry.getValue()));}
            if (entry.getKey().equals("goods")){
                mapList = (List<Map<Object, Object>>) entry.getValue();
            }
        }
        String prefix = "HM";//前缀
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//时间戳
        int random = RandomUtils.nextInt() + 10;//生成两位数的随机数

        String order_number = prefix + format.format(new Date()) + random;
        //设置订单编号
        orderList.setOrder_number(order_number);
        //设置订单状态
        System.out.println("orderList.getOrder_pay() = " + orderList.getOrder_pay());
        if (orderList.getOrder_pay().equals("1")){orderList.setPay_status(String.valueOf(1));orderList.setIs_send(1);}
        if (orderList.getOrder_pay().equals("0")){ orderList.setPay_status(String.valueOf(0));orderList.setIs_send(0);}
        System.out.println("orderList = " + orderList);
//        //插入一条订单数据
        if(orderList.getOrder_number()!=null){
            result = orderListMapper.createOrder(orderList);
        }
//       orderList.getOrder_id() 得在插入数据之后才能获取到
        System.out.println("orderList.getOrderId() = " + orderList.getOrder_id());
        if (orderList.getOrder_id()!=null){
            for (Map<Object, Object> map : mapList) {
                OrderGoods goods = new OrderGoods();
                //获取订单的id
                goods.setOrder_id(orderList.getOrder_id());
                goods.setOpenid(orderList.getOpenid());
                for (Object o : map.keySet()) {
                    if (o.equals("goods_id")){goods.setGoods_id((Integer)map.get(o));}
                    if (o.equals("goods_name")){goods.setGoods_name((String)map.get(o));}
                    if (o.equals("goods_small_logo")){goods.setGoods_small_logo((String)map.get(o));}
                    if (o.equals("goods_number")){goods.setGoods_number((Integer)map.get(o));}
                    if (o.equals("goods_price")){goods.setGoods_price((Integer)map.get(o));}
                    if (o.equals("goods_total_price")){goods.setGoods_total_price((Integer)map.get(o));}
                }
                orderGoodsList.add(goods);
            }
        }
//        for (OrderGoods orderGoods2 : orderGoodsList) {
//            System.out.println("orderGoods:\t"+orderGoods2);
//        }

        //插入一条订单商品数据
        boolean b = orderGoodsService.saveBatch(orderGoodsList);
        if (result==1&&b==true) {
            return order_number;
        }
        String msg = "获取失败";
        return msg;
    }



//    @SneakyThrows
//    @PostMapping("/orders/create")
//    @ApiOperation(value = "创建订单")
//    public String CreateOrder(@RequestBody Object params) {
//        Map<String,Object> params2 = new Util().getObjectToMap(params);
//        OrderList orderList = new OrderList();
//        List<OrderGoods> list = new ArrayList<>();
//        OrderGoods orderGoods = new OrderGoods();
////        List<OrderGoods> orderGoodsList =new ArrayList<>();
//        for(Map.Entry<String,Object> entry : params2.entrySet()){
//            //对传过来的数据进行赋值
//            if (entry.getKey().equals("openid")){
//                orderGoods.setOpenid((String) entry.getValue());
//                orderList.setOpenid((String) entry.getValue());
//            }
//            if (entry.getKey().equals("order_price")){orderList.setOrderPrice((Integer) entry.getValue());}
//            if (entry.getKey().equals("consignee_addr")){orderList.setConsigneeAddr((String) entry.getValue());}
//            if (entry.getKey().equals("goods")){
//                //将entry.getValue() 对象值 转变为json
//                Object[] goods2 = (Object[]) entry.getValue();
//                for (int i = 0; i < goods2.length; i++) {
//                    OrderGoods goods = new OrderGoods();
//                    goods.setOrderId(orderGoods.getOrderId());
//                    Map<String ,Object> temp = (Map<String, Object>) goods2[i];
//                    for(Map.Entry<String,Object> entry1 : temp.entrySet()){
//                        if (entry1.getKey().equals("openid")){goods.setOpenid((String) entry1.getValue());}
//                        if (entry1.getKey().equals("goods_name")){goods.setGoodsName((String) entry1.getValue());}
//                        if (entry1.getKey().equals("goods_number")){goods.setGoodsNumber((Integer) entry1.getValue());}
//                        if (entry1.getKey().equals("goods_price")){goods.setGoodsPrice((Integer) entry1.getValue());}
//                        if (entry1.getKey().equals("goods_total_price")){goods.setGoodsTotalPrice((Integer) entry1.getValue());}
//                    }
//                    list.add(goods);
//                }
//            }
//        }
//        String prefix = "HM";//前缀
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//时间戳
//        int random = RandomUtils.nextInt() + 10;//生成两位数的随机数
//
//        String order_number = prefix + format.format(new Date()) + random;
//        String openid =  orderList.getOpenid();
//        Integer order_price = orderList.getOrderPrice();
//        String consignee_addr = orderList.getConsigneeAddr();
////        //插入一条订单商品数据
////        boolean b = orderGoodsService.saveBatch(list);
////        //插入一条订单数据
////        int result = orderListMapper.createOrder(openid, order_number, order_price, consignee_addr);
////
////        if (result==1 && b !=false) {
////            return order_number;
////        }
////        return "获取失败";
//        String asd= "openid: \t"
//                + openid +"\norder_number: \t"
//                + order_number + "\norder_price: \t"
//                + order_price+ "\nconsignee_addr: \t"
//                + consignee_addr+"\nlist: \t"
//                + list;
//        return String.valueOf(params2);
//    }
//
//    @PostMapping("/orders/create")
//    @ApiOperation(value = "创建订单")
//    public String CreateOrder(@RequestBody Map<String,Object> params) {
//        OrderList orderList = new OrderList();
//        Date date = new Date();
//        //将所得到的参数循环，得到openid，order_price,consignee_addr
//        Object key;
//        Object value;
//        for(Map.Entry<String,Object> entry : params.entrySet()){
//            key = entry.getKey();
//            value = entry.getValue();
//            if (key.equals("openid")){
//                orderList.setOpenid((String) value);
//            }if (key.equals("order_price")){
//                orderList.setOrderPrice((Integer) value);
//            }if (key.equals("consignee_addr")){
//                orderList.setConsigneeAddr((String) value);
//            }
//        }
//        String prefix = "HM";
//        //时间戳
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
//        //生成两位数的随机数
//        int random = RandomUtils.nextInt() + 4;
//        String order_number = prefix + format.format(new Date()) + random;
////        String openid = "ogl635Ld-UZGE_ZmTcFvlaEvFxuo";
////        Integer order_price = 10598;
////        String consignee_addr = "广东省广州市海珠区新港中路397号";
//        orderList.setUserId(8);
//        orderList.setOrderPay("0");
//        orderList.setIsSend(0);
//        orderList.setPayStatus("0");
//        orderList.setUpdateTime(date);
//        orderList.setCreateTime(date);
//        orderList.setOrderNumber(order_number);
////        orderList.setOrderPrice(order_price);
////        orderList.setOpenid(openid);
////        orderList.setConsigneeAddr(consignee_addr);
//        int result = orderListMapper.insert(orderList);
//        if (result==1) {
//            return order_number;
//        }
//        return "获取失败";
////        Integer goods_id = null;
////        OrderGoods orderGoods = new OrderGoods();
////        orderGoods.setGoodsId(goods_id);
////        orderGoods.setGoodsName();
////        orderGoods.setGoodsNumber();
////        orderGoods.setGoodsPrice();
////        orderGoods.setGoodsSmallLogo();
////        orderGoods.setGoodsTotalPrice();
////        orderGoods.setOrderId();
////        orderGoods.setUserId();
////        orderGoods.setOpenid();
//    }
//
//

//    @PostMapping("/orders/create/{openid}/{order_price}/{consignee_addr}")
//    @PathVariable String openid,
    //@PathVariable String order_number,
//    @PathVariable Integer order_price,
//    @PathVariable String consignee_addr
//    public List<OrderList> CreateOrder(@RequestBody Map<String,Object> params) {
}