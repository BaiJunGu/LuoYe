package edu.soft1.controller;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import edu.soft1.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("-------Hello-------");
        return "hello";
    }
    @RequestMapping(value = "/login")//不被拦截的控制器
    public String login(User user, HttpSession session,HttpServletRequest request){
        System.out.println("------login()--------");//进入控制器哦
        int flag = 1;//调用业务层(业务层调用dao层),获取flag的值
        if (  flag== 1) {
            session.setAttribute("user",user);//登录对象放入session
            return "welcome";//登录成功
        }
        System.out.println(
                "登录失败，返回首页index"
        );
        request.setAttribute("error","用户或密码不正确");
        return "forward:/index.jsp";//登录失败
/*        return "redirect:/index.jsp";//登录失败*/
    }

    @RequestMapping("/reg")
    public String reg(User user){
        System.out.println("username="+user.getUsername());
        System.out.println("pwd="+user.getPwd());
        System.out.println("birthday"+user.getBirthday());
        System.out.println("city"+user.getAddress().getCity());
        System.out.println("street"+user.getAddress().getStreet());
        System.out.println("phone"+user.getAddress().getPhone());

        return "hello";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request){
        System.out.println("---------delete()----------");
        request.setAttribute("CRUDmsg","删除功能执行完毕");
        return "hello";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        System.out.println("---------welcome()----------");

        return "welcome";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session,HttpServletRequest request){
        System.out.println("---------out()----------");
        session.invalidate();
        request.setAttribute("error","已登出");
        return "redirect:/index.jsp";
    }

}
