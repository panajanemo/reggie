package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.R;
import com.itheima.reggie.pojo.Employee;
import com.itheima.reggie.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 员工信息 前端控制器
 * </p>
 *
 * @author panajenemo
 * @since 2023-01-03
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    public IEmployeeService employeeService;

    /**
     * @description
     * @param request
     * @param employee
     * @return R<Employee>
     * @author panajanemo
     * @time 2023/1/5 15:10
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        //1、获取提交的密码，进行加密
        String password = employee.getPassword();
        //  将密码转化为字体节数组继续加密后，赋值给password
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2、根据用户进行查询，查看数据是否存在
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //  employee::getUsername(创建Employee对象获取username属性) = 前端传递的username     eq:等于=
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        //  根据查询结果封装为实体类对象
        Employee emp = employeeService.getOne(queryWrapper);

        //3、如果没有查询到则返回登录失败结果
        if(emp == null){
            return R.error("登录失败");
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }

        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if(emp.getStatus() == 0){
            return R.error("账号已禁用");
        }

        //6、登录成功，将员工id存入Session并返回登录成功结果给前端
        HttpSession session = request.getSession();
        session.setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /**
     * @description 员工推出
     * @param request
     * @return R<String>
     * @author panajanemo
     * @time 2023/1/5 15:10
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){        //退出不需要返回详细数据
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
}
