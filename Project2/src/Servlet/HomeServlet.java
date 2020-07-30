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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "HomeServlet")
public class HomeServlet extends HttpServlet {
    private ImageDAO imageDAO = new ImageDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Image> list = imageDAO.getFavorMost();
            List<Image> list1 = imageDAO.getDateLast();
            list.addAll(list1);
            String data = "";
            Gson gson = new Gson();
            data = gson.toJson(list);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.append(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
