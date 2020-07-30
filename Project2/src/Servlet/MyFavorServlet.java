package Servlet;

import Bean.Image;
import Bean.User;
import DatabaseDAO.ImageDAO;
import DatabaseDAO.UserDAO;
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

@WebServlet(name = "MyFavorServlet")
public class MyFavorServlet extends HttpServlet {
    private ImageDAO imageDAO = new ImageDAO();
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        /*try {
            List<Image> list=imageDAO.getFavorByID(user.getUID());
            Gson gson=new Gson();
            String data=gson.toJson(list);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.append(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        if (user != null) {
            if (request.getParameter("UID") != null) {
                int uid = Integer.parseInt(request.getParameter("UID"));
                try {
                    User user1=userDAO.getUserByID(uid);
                    httpSession.setAttribute("state", user1.getState());
                    if ((user1.getState())==1) {
                        List<Image> list = imageDAO.getFavorByID(uid);
                        if (httpSession.getAttribute("myFavor") != null)
                            httpSession.removeAttribute("myFavor");
                        httpSession.setAttribute("myFavor", list);
                    }
                    httpSession.setAttribute("favorName", user1.getUserName() + "'s Favor");
                    response.sendRedirect(request.getContextPath() + "/myFavor.jsp?able=false");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    List<Image> list = imageDAO.getFavorByID(user.getUID());
                    if (httpSession.getAttribute("myFavor") != null)
                        httpSession.removeAttribute("myFavor");
                    httpSession.setAttribute("myFavor", list);
                    httpSession.setAttribute("favorName", user.getUserName() + "'s Favor");
                    response.sendRedirect(request.getContextPath() + "/myFavor.jsp");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            }

    }
}
