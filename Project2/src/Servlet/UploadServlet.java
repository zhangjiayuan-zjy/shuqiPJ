package Servlet;

import Bean.City;
import Bean.Country;
import Bean.Image;
import Bean.User;
import DatabaseDAO.CityDAO;
import DatabaseDAO.CountryDAO;
import DatabaseDAO.ImageDAO;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "travel-images/small/";
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    private ImageDAO imageDAO = new ImageDAO();
    private CountryDAO countryDAO = new CountryDAO();
    private CityDAO cityDAO = new CityDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("isFile") != null) {
            int imageID = Integer.parseInt(request.getParameter("imageID"));
            String title = request.getParameter("title");
            String des = request.getParameter("des");
            int city = Integer.parseInt(request.getParameter("city"));
            String country = request.getParameter("country");
            String content = request.getParameter("content");
            String author = request.getParameter("author");
            try {
                imageDAO.modifyImage(title, content, des, city, country, imageID, author);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + "/MyPhoto");
        } else {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            // 设置临时存储目录
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 设置最大文件上传值
            upload.setFileSizeMax(MAX_FILE_SIZE);
            // 设置最大请求值 (包含文件和表单数据)
            upload.setSizeMax(MAX_REQUEST_SIZE);
            // 构造临时路径来存储上传的文件
            // 这个路径相对当前应用的目录
            //String uploadPath = request.getContextPath() + "/travel-images/small";
            //String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
            String uploadPath=getServletContext().getRealPath("./")+UPLOAD_DIRECTORY;
            String uploadPath1=getServletContext().getRealPath("./")+"travel-images/large/";
            System.out.println(uploadPath);
            //String uploadPath = "C:\\Users\\23058\\Desktop\\Tower-of-the-Deathmaster-master\\Project2\\web\\" + "travel-images\\small\\";
            try {
                @SuppressWarnings("unchecked")
                List<FileItem> formItems = upload.parseRequest(request);
                Image image = new Image();
                String fileName = "";
                if (formItems != null && formItems.size() > 0) {
                    // 迭代表单数据
                    for (FileItem item : formItems) {
                        // 处理不在表单中的字段
                        if (!item.isFormField()) {
                            fileName = new File(item.getName()).getName();
                            System.out.println(fileName);
                            String filePath = uploadPath + fileName;
                            File storeFile = new File(filePath);
                            // 在控制台输出文件的上传路径
                            // 保存文件到硬盘
                            item.write(storeFile);
                            String filePath1=uploadPath1+fileName;
                            File storeFile1=new File(filePath1);
                            item.write(storeFile1);
                            System.out.println(11111);
                            image.setPath(fileName);
                        } else {
                            switch (item.getFieldName()) {
                                case "title":
                                    image.setTitle(item.getString("UTF-8"));
                                    break;
                                case "des":
                                    image.setDescription(item.getString("UTF-8"));
                                    break;
                                case "country":
                                    image.setCountry_RegionCodeISO(item.getString("UTF-8"));
                                    break;
                                case "city":
                                    image.setCityCode(Integer.parseInt(item.getString("UTF-8")));
                                    break;
                                case "content":
                                    image.setContent(item.getString("UTF-8"));
                                    break;
                                case "author":
                                    image.setAuthor(item.getString("UTF-8"));
                            }
                        }
                    }
                }
                System.out.println(222222);
                Date date = new Date();
                SimpleDateFormat formatime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = formatime.format(date);
                image.setDate(time);
                User user = (User) request.getSession().getAttribute("user");
                image.setUID(user.getUID());
                imageDAO.addImage(image);
                response.sendRedirect(request.getContextPath() + "/MyPhoto");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter printWriter = response.getWriter();
        if (request.getParameter("method").equals("city")) {
            try {
                List<City> list = cityDAO.getCityByCountry(request.getParameter("ISO"));
                Gson gson = new Gson();
                String data = gson.toJson(list);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                printWriter.append(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (request.getParameter("method").equals("modify")) {
            int imageID = Integer.parseInt(request.getParameter("imageID"));
            if (session.getAttribute("modifyImage") != null)
                session.removeAttribute("modifyImage");
            if (session.getAttribute("modifyCity") != null)
                session.removeAttribute("modifyCity");
            if (session.getAttribute("modifyCountry") != null)
                session.removeAttribute("modifyCountry");
            try {
                Image image = imageDAO.getImageByID(imageID);
                session.setAttribute("modifyImage", image);
                List<Country> list = countryDAO.getAllCountry(20);
                session.setAttribute("countryList", list);
                List<City> list1 = cityDAO.getCityByCountry(image.getCountry_RegionCodeISO());
                session.setAttribute("modifyCity", list1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + "/upload.jsp?method=modify");
        } else {
            if (session.getAttribute("countryList") != null)
                session.removeAttribute("countryList");
            try {
                List<Country> list = countryDAO.getAllCountry(20);
                session.setAttribute("countryList", list);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + "/upload.jsp?method=upload");
        }

    }

    public Image getImage(HttpServletRequest request) {
        Date date = new Date();
        SimpleDateFormat formatime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatime.format(date);
        Image image = new Image();
        image.setTitle(request.getParameter("title"));
        image.setContent(request.getParameter("content"));
        System.out.println(request.getParameter("content"));
        System.out.println(request.getParameter("country"));
        image.setCountry_RegionCodeISO(request.getParameter("country"));
        System.out.println(request.getParameter("city"));
        //image.setCityCode(Integer.parseInt(request.getParameter("city")));
        image.setTitle(request.getParameter("title"));
        image.setDescription(request.getParameter("des"));
        image.setDate(time);
        System.out.println(time);
        return image;
    }
}
