package com.test.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 设置当前web项目的字符集
 * @author Thenchen
 *
 */
public class EncodingFilter implements Filter {
    private String encoding = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //过滤器初始化时，获得当前Filter在web.xml文件中名字为charset的私有信息的值
        this.encoding = filterConfig.getInitParameter("charset");
    }
    @Override
    public void destroy() {
        this.encoding = null;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(this.encoding);
        servletResponse.setCharacterEncoding(this.encoding);
        servletResponse.setContentType("text/html;charset=" + this.encoding);
        //将请求与响应继续向下传递
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
