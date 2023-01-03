package com.itheima.reggie.service.impl;

import com.itheima.reggie.pojo.Dish;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.service.IDishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author panajenemo
 * @since 2023-01-03
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {

}
