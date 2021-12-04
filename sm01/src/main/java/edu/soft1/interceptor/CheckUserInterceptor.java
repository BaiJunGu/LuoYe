package edu.soft1.interceptor;

import edu.soft1.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        Object ojb = session.getAttribute("user");//获取session中的登录对象

        if(ojb != null && ojb instanceof User){
            System.out.println("拦截器放行");

            return true;//通过
        }
        request.getRequestDispatcher("/index.jsp").forward(request,response);//转发到登录
        response.sendRedirect("/index.jsp");//重定向（到登录）
        //返回登录页
        System.out.println("返回登录页");
        return false;//拦截

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
