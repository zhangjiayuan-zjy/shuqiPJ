package Servlet;

import Bean.Image;
import Bean.User;
import DatabaseDAO.ImageDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private ImageDAO imageDAO = new ImageDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");
        int imageID = Integer.parseInt(request.getParameter("imageID"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        switch (flag) {
            case "favor":
                try {
                    imageDAO.deleteFavor(imageID, user.getUID());
                    List<Image> list = imageDAO.getFavorByID(user.getUID());
                    session.setAttribute("myFavor", list);
                    System.out.println(111111111);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "myPhoto":
                try {
                    imageDAO.deleteImage(imageID);
                    List<Image> list = imageDAO.getImageByUID(user.getUID());
                    session.setAttribute("myPhoto", list);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
