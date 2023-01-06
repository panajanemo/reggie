package com.itheima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author panajanemo
 * @version 1.0.0
 * @title LoginCheckFilter
 * @description 检查用户是否已经完成登录
 * @create 2023/1/6 21:31
 */
                            //过滤器名称       过滤器拦截路径
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    /**
     * @description
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @author panajanemo
     * @time 2023/1/6 21:34
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取本次请求的URL
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}",requestURI);


        //2.判断请求是否处理    urls是放行路径   /employee/page是当前列表的查询请求
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"};
        boolean check = check(urls, requestURI);

        //3.check  = true时 ，表示不需要处理，直接放行
        if (check) {
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request, response);  //放行
            return;
        }

        //4.check = false时，表示不能放行，则判断用户是否登录，若用户已登录则放行
        HttpSession session = request.getSession();
        if (session.getAttribute("employee") != null) {
            log.info("用户已登录，用户id为：{}",session.getAttribute("employee"));
            filterChain.doFilter(request, response);  //放行
            return;
        }

        //5.用户未登录，则不放行，将R对象转为JSON，响应给前端处理，前端接受响应后跳转至登录页面
        //不直接return R.error()是因为该方法无返回值类型
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * @description 路径匹配，判断本次请求是否需要放行
     * @param urls
     * @param requestURL
     * @return boolean
     * @author panajanemo
     * @time 2023/1/6 22:25
     */

    public boolean check(String[] urls , String requestURL){
        for (String s:urls){
            //是否匹配
            boolean match = PATH_MATCHER.match(s, requestURL);
            if (match){
                return true;
            }
        }
        return false;
    }


}
