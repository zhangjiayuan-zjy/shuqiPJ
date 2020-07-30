package WebSocket;

import Bean.User;
import DatabaseDAO.UserDAO;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket" ,configurator = GetHttpSessionConfigurator.class)
public class Socket {
    private static UserDAO userDAO = new UserDAO();
    private static int onlineCount = 0;
    private static Map<String, Socket> webSocketMap = new ConcurrentHashMap<>();
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    //private static CopyOnWriteArraySet<Socket> webSocketSet = new CopyOnWriteArraySet<Socket>();
    private Session session;
    private HttpSession httpSession;

    /**
     *      * 连接建立成功调用的方法
     *      * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     *    
     **/
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.session = session;
        this.httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        User user = (User) httpSession.getAttribute("user");
        webSocketMap.put(user.getUserName(), this);
        addOnlineCount(); //在线数加1
        try {
            String message = ResultMessageTool.getMessage("system", getOnlineFriend(httpSession), true);
            this.sendMessage(message);
            List<String> list1=new ArrayList<>();
            list1.add(user.getUserName());
            String message1=ResultMessageTool.getMessage("system",list1,true);
            List<String> list=getOnlineFriend(httpSession);
            sendAll(list,message1);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }


    @OnClose
    public void onClose() {
        User user=(User) httpSession.getAttribute("user");
        String message=ResultMessageTool.getMessage(user.getUserName(),user.getUserName(),true);
        try {
            List<String> list=getOnlineFriend(httpSession);
            sendAll(list,message);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        webSocketMap.remove(user.getUserName()); //从set中删除
        subOnlineCount(); //在线数减1    
    }

    /**
     *      * 收到客户端消息后调用的方法
     *      * @param message 客户端发送过来的消息
     *      * @param session 可选的参数
     *     
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        Gson gson=new Gson();
        Message message1=gson.fromJson(message,Message.class);
        User user=(User) httpSession.getAttribute("user");
        String data=ResultMessageTool.getMessage(user.getUserName(),message1.getMessage(),false);
        try {
            webSocketMap.get(message1.getToName()).sendMessage(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *      * 发生错误时调用
     *      * @param session
     *      * @param error
     *     
     */

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     *      * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *      * @param message
     *      * @throws IOException
     *     
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }


    public static synchronized void addOnlineCount() {
        Socket.onlineCount++;
    }


    public static synchronized void subOnlineCount() {
        Socket.onlineCount--;
    }

    private void sendAll(List<String> list,String message) throws IOException, SQLException {
        //Set<String> names = webSocketMap.keySet();
        for (String name:list){
            if (webSocketMap.get(name)!=null){
                //HttpSession httpSession=webSocketMap.get(name).httpSession;
                //String message = ResultMessageTool.getMessage("system", getOnlineFriend(httpSession), true);
                webSocketMap.get(name).sendMessage(message);
            }
        }
        /*for (String name : names) {
            HttpSession httpSession=webSocketMap.get(names).httpSession;
            String message = ResultMessageTool.getMessage("system", getOnlineFriend(httpSession), true);
            System.out.println(message);
            webSocketMap.get(name).session.getBasicRemote().sendText(message);
        }*/
    }

    private List<String> getOnlineFriend(HttpSession httpSession) throws SQLException {
        User user = (User) httpSession.getAttribute("user");
        List<String> list = new ArrayList<>();
        List<User> list1 = userDAO.getFriendByUID(user.getUID());
        if (list1.size()>0){
            for (User user1 : list1) {
                if (webSocketMap.get(user1.getUserName()) != null) {
                    list.add(user1.getUserName());
                }
            }
        }
        return list;
    }


}
