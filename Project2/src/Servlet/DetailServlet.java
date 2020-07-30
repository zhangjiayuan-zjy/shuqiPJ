package Servlet;

import Bean.Image;
import Bean.User;
import DatabaseDAO.ImageDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DetailServlet")
public class DetailServlet extends HttpServlet {
    private ImageDAO imageDAO = new ImageDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Image image = (Image) session.getAttribute("detailImage");
        User user = (User) session.getAttribute("user");
        String value = request.getParameter("value");
        PrintWriter printWriter = response.getWriter();
        switch (value) {
            case "Collect":
                try {
                    imageDAO.addFavor(image, user);
                    //session.removeAttribute("detailImage");
                    Image image1=imageDAO.getImageByID(image.getImageID());
                    //image.setFavorNumber(image.getFavorNumber() + 1);
                    session.setAttribute("detailImage", image1);
                    printWriter.append("Collected|" + image1.getFavorNumber());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "Collected":
                try {
                    imageDAO.deleteFavor(image.getImageID(), user.getUID());
                    //image.setFavorNumber(image.getFavorNumber() - 1);
                    //session.removeAttribute("detailImage");
                    Image image1=imageDAO.getImageByID(image.getImageID());
                    session.setAttribute("detailImage", image1);
                    printWriter.append("Collect|" + image1.getFavorNumber());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int imageID = Integer.parseInt(request.getParameter("imageID"));
        try {
            Image image = imageDAO.getImageByID(imageID);
            //String date=imageDAO.getDate(imageID);
            //image.setDate(date);
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                Cookie[] cookies = request.getCookies();
                List<Cookie> list = new ArrayList<>();
                Cookie tempCookie = null;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().startsWith("detailImage")) {
                        list.add(cookie);
                        if (URLDecoder.decode(cookie.getValue(),"utf-8").equals(image.getTitle())) {
                            tempCookie = cookie;
                        }
                    }
                }
                if (cookies.length >= 10 && tempCookie == null) {
                    tempCookie = list.get(0);
                }
                if (tempCookie != null) {
                    tempCookie.setMaxAge(0);
                    response.addCookie(tempCookie);
                }
                Cookie cookie = new Cookie("detailImage" + image.getImageID(), URLEncoder.encode(image.getTitle(), "utf-8"));
                response.addCookie(cookie);
            }
            if (session.getAttribute("flag") != null)
                session.removeAttribute("flag");
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                if (imageDAO.isFavor(imageID, user.getUID())) {
                    session.setAttribute("flag", "Collected");
                } else
                    session.setAttribute("flag", "Collect");
            }

            /*if (session.getAttribute("detailImage") != null) {
                session.removeAttribute("detailImage");
            }*/
            session.setAttribute("detailImage", image);
            response.sendRedirect(request.getContextPath() + "/detail.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
