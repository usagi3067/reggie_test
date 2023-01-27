package com.dango.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.dango.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经完成登录
 */

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //1.2 路径匹配器， 支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1. 获取本次请求的URI
        String requestURI = request.getRequestURI();

        log.info("拦截到请求:{}", requestURI);
        //1.1 创建白名单 定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        //2. 判断本次请求是否需要处理
        //2.5 查看是否为白名单路径
        boolean check = check(urls,requestURI);
        //3. 如果不需要处理， 则直接放行
        if (check) {
            log.info("本次请求{}不需要处理", requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        //4. 判断登录状态， 如果已登录， 则直接放行
        if (request.getSession().getAttribute("employee") != null) {
            log.info("用户已登录， 用户id为： {}", request.getSession().getAttribute("employee"));
            filterChain.doFilter(request, response);
            return;
        }
        log.info("用户未登录");
        //5. 如果未登录则返回未登录结果, 通过输出流方式像客户端页面相应数据
        //5.1 由于不是springmvc的controller， 需要像response对象输出流自己写JSON数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }

    //2.1 定义方法
    /**
     * 路径匹配检查本次请求是否需要放行
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        //2.2 循环每个白名单路径
        for (String url : urls) {
            //2.3 查看是否匹配通配符
            boolean match = PATH_MATCHER.match(url, requestURI);
            //2.4 若匹配上，返回true， 否则返回false
            if (match) {
                return true;
            }
        }
        return false;
    }
}
