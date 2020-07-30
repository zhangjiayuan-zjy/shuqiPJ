package Servlet;

import Bean.Image;
import Bean.User;
import DatabaseDAO.ImageDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MyPhotoServlet")
public class MyPhotoServlet extends HttpServlet {
    private ImageDAO imageDAO = new ImageDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                List<Image> list = imageDAO.getImageByUID(user.getUID());
                session.setAttribute("myPhoto",list);
                response.sendRedirect(request.getContextPath()+"/myPhoto.jsp");
                /*Gson gson=new Gson();
                String data=gson.toJson(list);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.append(data);*/
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
