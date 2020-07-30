package Servlet;

import Bean.Image;
import DatabaseDAO.ImageDAO;
import com.google.gson.Gson;

import javax.jnlp.ClipboardService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    private ImageDAO imageDAO = new ImageDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");
        System.out.println(flag);
        String order = request.getParameter("order");
        int page = Integer.parseInt(request.getParameter("page"));
        switch (flag) {
            case "init":
                try {
                    List<Image> images = imageDAO.getAllImage(order, page * 5);
                    long count = imageDAO.getCountInit();
                    returnData(response, images, order, count);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "title":
                try {
                    String title = request.getParameter("value");
                    List<Image> images = imageDAO.getImageByTitle(title, order, page * 5);
                    long count = imageDAO.getCountTitle(title);
                    returnData(response, images, order, count);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "content":
                try {
                    String content = request.getParameter("value");
                    List<Image> images = imageDAO.getImageByContent(content, order, page * 5);
                    long count = imageDAO.getCountContent(content);
                    returnData(response, images, order, count);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void sortList(List<Image> images) {
        Collections.sort(images, new Comparator<Image>() {
            @Override
            public int compare(Image o1, Image o2) {
                long i = o1.getFavorNumber() - o2.getFavorNumber();
                if (i > 0) {
                    return 1;
                } else if (i < 0) {
                    return -1;
                }
                return 0;
            }
        });
    }

    private void returnData(HttpServletResponse response, List<Image> images, String order, long count) throws IOException {
      /*
        if (order.equals("favor")) {
            sortList(images);
        }
        System.out.println(images);*/
        List<Object> list = new ArrayList<>();
        list.add(count);
        list.addAll(images);
        Gson gson = new Gson();
        String data = gson.toJson(list);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.append(data);
    }
}
