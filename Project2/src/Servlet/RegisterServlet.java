package Servlet;

import DatabaseDAO.UserDAO;
import Bean.User;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> list=new ArrayList<>();
        if (identifyCode(request,response)){
            User user = new User();
            System.out.println(request.getParameter("name"));
            user.setUserName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPass(request.getParameter("password"));
            try {
                if (userDAO.getCount(user)) {
                    userDAO.addUser(user);
                    user = userDAO.getUIDByName(user.getUserName());
                    request.getSession().setAttribute("user", user);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html; charset=utf-8");
                    PrintWriter printWriter=response.getWriter();
                    printWriter.append("success");
                } else {
                    System.out.println("用户名已存在");
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html; charset=utf-8");
                    PrintWriter printWriter=response.getWriter();
                    printWriter.write("userName");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            printWriter.write("code");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean identifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        System.out.println(code);
        // 验证验证码
        String sessionCode = request.getSession().getAttribute("code").toString();
        if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
            if (code.equalsIgnoreCase(sessionCode)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
