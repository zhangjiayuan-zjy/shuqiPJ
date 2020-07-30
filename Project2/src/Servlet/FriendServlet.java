package Servlet;

import Bean.User;
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

@WebServlet(name = "FriendServlet")
public class FriendServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int UID = Integer.parseInt(request.getParameter("UID"));
        int UID1 = user.getUID();
        switch (method) {
            case "delete":
                //int UID=Integer.parseInt(request.getParameter("UID"));
                try {
                    userDAO.deleteFriend(UID1, UID);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "message":
                String flag = request.getParameter("flag");
                //int UID1 =Integer.parseInt(request.getParameter("UID"));
                if (flag.equals("yes")) {
                    try {
                        userDAO.addFriendship(UID, UID1);
                        userDAO.deleteIdentify(UID, UID1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if (flag.equals("no")) {
                    try {
                        userDAO.deleteIdentify(UID, UID1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "add":
                try {
                    userDAO.addIdentify(UID1, UID);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "modify":
                try {
                    int state = Integer.parseInt(request.getParameter("states"));
                    System.out.println(state);
                    userDAO.modifyState(UID1, state);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        PrintWriter printWriter = response.getWriter();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        switch (method) {
            case "friend":
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                try {
                    printWriter.append(getFriend(user.getUID()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "message":
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                try {
                    printWriter.append(getMessage(user.getUID()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "search":
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                try {
                    printWriter.append(getUser(request.getParameter("name")));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private String getFriend(int UID) throws SQLException {
        List<User> list = userDAO.getFriendByUID(UID);
        Gson gson = new Gson();
        String data = gson.toJson(list);
        return data;
    }

    private String getMessage(int UID) throws SQLException {
        List<User> list = userDAO.getIdentifyByUID(UID);
        Gson gson = new Gson();
        String data = gson.toJson(list);
        return data;
    }

    private String getUser(String name) throws SQLException {
        List<User> users = userDAO.getUserByName(name);
        Gson gson = new Gson();
        String data = gson.toJson(users);
        System.out.println(data);
        return data;
    }
}
