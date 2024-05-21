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

@WebServlet(urlPatterns = "/userDelete")
public class UserDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获得页面中删除用户的编号
        String userId = req.getParameter("userId");
        UserMessageService service = new UserMessageServiceImpl();
        try {
            int i = service.delete(userId);
            if(i>0){
                //删除成功
                //转发:向服务器发出请求userSelect,重新查询满足条件的用户信息
                System.out.println("删除成功！");
                req.getRequestDispatcher("userSelect").forward(req, resp);
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //删除失败或删除时发生异常
        //获得HttpSession
        HttpSession session = req.getSession(true);
        //将错误信息以error名字存入到session中
        session.setAttribute("error","<script>alert('用户删除失败！！！')</script>");
        //重定向到main.jsp页面
        resp.sendRedirect(resp.encodeRedirectURL("main.jsp"));
    }
}
