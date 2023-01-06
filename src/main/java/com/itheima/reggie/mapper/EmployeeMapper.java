package com.itheima.reggie.mapper;

import com.itheima.reggie.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 员工信息 Mapper 接口
 * </p>
 *
 * @author panajenemo
 * @since 2023-01-03
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
