package com.test.controller;

import com.test.dao.impl.UserMessageDaoImpl;
import com.test.entity.UserMessage;
import com.test.service.UserMessageService;
import com.test.service.impl.UserMessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/userSelect")
public class UserSelectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获得请求中名字为userName和userSex的值
        String username = req.getParameter("userName");
        String userSex = req.getParameter("userSex");

        UserMessageService service = new UserMessageServiceImpl();
        try{
            List<UserMessage> list = service.selectUser(username,userSex);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
