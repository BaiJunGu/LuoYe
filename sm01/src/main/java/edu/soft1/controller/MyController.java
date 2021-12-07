package edu.soft1.controller;

import edu.soft1.pojo.User;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.apache.commons.io.FilenameUtils;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/param1")
public class MyController {
    @RequestMapping(value = "upload",method = {RequestMethod.POST})
    public String fileUpload(MultipartFile image, HttpServletRequest request, Map<String,Object>map) throws IOException {
        InputStream is = image.getInputStream();
        String filename = image.getOriginalFilename();
        String realPath = request.getServletContext().getRealPath("/images");
        System.out.println("上传路径="+realPath);
        FileOutputStream os = new FileOutputStream(new File(realPath,doFileName(filename)));
        int size = IOUtils.copy(is,os);
        os.close();is.close();
        if(size>0){
            map.put("msg","uploadSuccess");
            System.out.println("完成上传size="+size+"Byte");
        }
        return "welcome";
    }




    @RequestMapping(value = "upload2",method = {RequestMethod.POST})
    public String fileUpload2(MultipartFile[] images,HttpServletRequest request,Map<String,Object>map) throws IOException {
        InputStream is = null;
        FileOutputStream os = null;
        int count = 0;
        for (MultipartFile image: images) {
             is = image.getInputStream();
            String filename = image.getOriginalFilename();
            System.out.println(
                    "文件原名称"+filename
            );
            if (filename.equals("")){
                System.out.println("空字符=");

                continue;
            };
            String realPath = request.getServletContext().getRealPath("/images");
            System.out.println("上传路径="+realPath);
            os = new FileOutputStream(new File(realPath,doFileName(filename)));
            int size = IOUtils.copy(is,os);
            if (size>0){
                count++;
            }
        }
        os.close();is.close();
        map.put("msg2",count);
        System.out.println("上传成功"+count+"张");
        return "welcome";
    }
    @RequestMapping(value = "/load.do/{filename}")
   public void load(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response) throws IOException{
      response.setHeader("Content-Disposition","attachment;filename=");
      String realPath = request.getServletContext().getRealPath("/images");
        System.out.println(
                "下载路径"+realPath
        );
        FileInputStream is = new FileInputStream(new File(realPath,filename));
        OutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        os.close();
        is.close();

   }
    private String doFileName(String filename){
        String extension = FilenameUtils.getExtension(filename);//获取文件的后缀名称
        String uuid = UUID.randomUUID().toString();//获取uuid字符，规避名称重复
        System.out.println("上传文件名="+uuid);
        return uuid+"."+extension;
    }

/*    @RequestMapping("/hello")
    public String hello(){
        System.out.println("-------Hello-------");
        return "hello";
    }*/
/*    @RequestMapping("/hello")
    public ModelAndView hello(){
    System.out.println("-------Hello-------");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        mav.addObject("msg"," I am XiaoHu");
    return mav;
    }*/
    @RequestMapping("/hello")
    public String hello(String username, Model model){
        System.out.println("-------Hello-------");
/*        model.addAttribute("username",username);*/
        model.addAttribute(username);
        return "hello";
    }



    @RequestMapping(value = "/param",method = {RequestMethod.GET})
    public  String param(HttpServletRequest request){
        String name = request.getParameter("name");
        System.out.println("name="+name);
        request.setAttribute("name",name);
        return "hello";
    }
    @RequestMapping(value = "/param2",method = {RequestMethod.GET,RequestMethod.POST})
    public  String param2(HttpServletRequest request, HttpSession session){
        String name = request.getParameter("username");//获取数据
        String age = request.getParameter("age");//获取数据
        System.out.println("username="+name+",age="+age);//打印
        session.setAttribute("age",age);
        request.setAttribute("name",name);
        return "hello";
    }
    @RequestMapping(value =  "param3",method = {RequestMethod.POST})
    public String param3(String username,int age){
        System.out.println(
                "------param3()-------"
        );
        System.out.println("username="+username);
        System.out.println("age="+age);
        return "hello";
    }
    @RequestMapping(value =  "param4",method = {RequestMethod.POST})
    public String param4(@RequestParam(value = "username",required = false) String u, @RequestParam(value = "age",defaultValue = "18") int a,HttpServletRequest request){
        System.out.println(
                "------param4()-------"
        );
        System.out.println("username="+u);
        System.out.println("age="+a);
        return "redirect: hello";
    }
    @RequestMapping(value =  "param5",method = {RequestMethod.POST})
    public String param5(User user ,HttpSession session){
        System.out.println("------param5()-------");
        System.out.println("username="+user.getUsername());
        System.out.println("age="+user.getAge());
        return "redirect: hello";
    }


    @RequestMapping("/test")
    public String test(){
        System.out.println("-----------test()------------");

        return "hello";
    }

    @RequestMapping("/reg")
    public String reg(User user){
        System.out.println("username="+user.getUsername());
        System.out.println("pwd="+user.getAge());
        System.out.println("birthday"+user.getBirthday());
        System.out.println("city"+user.getAddress().getCity());
        System.out.println("street"+user.getAddress().getStreet());
        System.out.println("phone"+user.getAddress().getPhone());

        return "redirect:/param1/test";
    }
}
