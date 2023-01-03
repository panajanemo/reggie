package com.itheima.reggie.service.impl;

import com.itheima.reggie.pojo.Orders;
import com.itheima.reggie.mapper.OrdersMapper;
import com.itheima.reggie.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author panajenemo
 * @since 2023-01-03
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
