package com.example.demo.webserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Msg extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String oid = request.getParameter("oid");
        String uid = session.getId();
        System.out.println("oid: "+oid+ "  uid:"+uid);

        request.setAttribute("initiator", "false");

        if(!VideoSocketServer.canCreate()) {
            response.getWriter().write("不能创建通话房间，超过最大创建数量！");
            return;
        }

        if(!VideoSocketServer.canJoin(oid)) {
            response.getWriter().write("对不起对方正在通话中，你不能加入！");
            return;
        }

        System.out.println("uid: "+uid+ " oid:"+oid);
        if(VideoSocketServer.addUser(uid, oid)) {
            request.setAttribute("uid", uid);
        } else {
            request.setAttribute("initiator", "true");
            request.setAttribute("uid", uid);
            request.setAttribute("oid", oid);
        }

//        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void init() throws ServletException {
    }

}
