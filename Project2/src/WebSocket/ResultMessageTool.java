package WebSocket;

import com.google.gson.Gson;

public class ResultMessageTool {
    public static String getMessage(String fromName,Object message,boolean isSystem){
        ResultMessage resultMessage=new ResultMessage();
        resultMessage.setFromName(fromName);
        resultMessage.setMessage(message);
        resultMessage.setSystem(isSystem);
        Gson gson=new Gson();
        return gson.toJson(resultMessage);
    }
}
