package com.test.controller;

import com.test.service.UserMessageService;
import com.test.service.impl.UserMessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/userUpdate")

public class UserUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Integer id = Integer.parseInt(req.getParameter("userId"));
       String username = req.getParameter("newuserName");
       String userpassword = req.getParameter("newuserPassword");
       String userPhone = req.getParameter("newuserPhone");
       String userEmail = req.getParameter("newuserEmail");
       String userSex = req.getParameter("newuserSex");
       UserMessageService service = new UserMessageServiceImpl();
       try{
           int i = service.modifyUserMessage(id,username,userpassword,userPhone,userEmail,userSex);
           if(i>0){
               System.out.println("更新成功");
               req.getRequestDispatcher("userSelect").forward(req,resp);
               return;
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
        //删除失败或删除时发生异常
        //获得HttpSession
        HttpSession session = req.getSession(true);
        //将错误信息以error名字存入到session中
        session.setAttribute("error","<script>alert('用户修改失败！！！')</script>");
        //重定向到main.jsp页面
        resp.sendRedirect(resp.encodeRedirectURL("main.jsp"));
    }
}
