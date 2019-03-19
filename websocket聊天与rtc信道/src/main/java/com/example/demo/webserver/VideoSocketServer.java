package com.example.demo.webserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/videoSocket/{id}")
@Component
public class VideoSocketServer {

    //记录档期那在线连接数量
    private static  Integer onlineCount = 0;
    //concurrent包的线程安全set,用来存放每个客户端对应的websocket对象
    private static CopyOnWriteArraySet<VideoSocketServer> wsSet =  new CopyOnWriteArraySet<VideoSocketServer>();
    //与某个客户端的连接会话，需要通过它来与客户端进行数据收发
    private Session session;
    //某个客户对应的ID
    private Long userId;

    private ObjectMapper objectMapper = new ObjectMapper();

    // 最大通话数量
    private static final int MAX_COUNT = 20;
    private static final long MAX_TIME_OUT = 2 * 60 * 1000;
//    // 用户和用户的对话映射
//    private static final Map<String, String> user_user = Collections.synchronizedMap(new HashMap<String, String>());
//    // 用户和websocket的session映射
//    private static final Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<String, Session>());

    /**
     * 打开websocket
     * @param session websocket的session
     * @param uid 打开用户的UID
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id")Long uid) {
        session.setMaxIdleTimeout(MAX_TIME_OUT);
        this.session = session;
        this.userId = uid;
        wsSet.add(this);
        System.out.println("有新的连接进来了： "+uid);
    }

    /**
     * websocket关闭
     * @param session 关闭的session
     * @param uid 关闭的用户标识
     */
    @OnClose
    public void onClose(Session session, @PathParam("id")String uid) {

        System.out.println("有用户退出:"+uid);
        wsSet.remove(this);
    }

    /**
     * 收到消息
     * @param message 消息内容
     * @param session 发送消息的session
     * @param uid
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("id")Long uid) {
        System.out.println("发出id:"+uid+"新的messaage: "+message);

        System.out.println("当前用户数量："+wsSet.size());
        for ( VideoSocketServer videoSocketServer : wsSet ) {
            //将自己排除
            if ( videoSocketServer.userId.equals(uid) ) {
                System.out.println("被排除的ID： "+this.userId+" "+uid);  continue; }
            Session osession = videoSocketServer.session;
            try {
                System.out.println("发给ID： "+videoSocketServer.userId+"新的message: "+message);
                osession.getAsyncRemote().sendText(new String(message.getBytes("UTF-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

//        try {
//         //   if(uid != null && user_user.get(uid) != null && VideoSocketServer.sessions.get(user_user.get(uid)) != null) {
//                Session osession = sessions.get(user_user.get(uid)); // 被呼叫的session
//                if(osession.isOpen())
//                    osession.getAsyncRemote().sendText(new String(message.getBytes("utf-8")));
//                else
//                    //没有人等待
//                    this.nowaiting(osession);
//            } else {
//                //没有人等待
//                this.nowaiting(session);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 没有人在等待
     * @param session 发送消息的session
     */
    private void nowaiting(Session session) {
        session.getAsyncRemote().sendText("{\"type\" : \"nowaiting\"}");
    }

    /**
     * 是否可以继续创建通话房间
     * @return 可以：true；不可以false；
     */
    public static boolean canCreate() {
        return  wsSet.size() <= MAX_COUNT;
    }

    /**
     * 判断是否可以加入
     * @param oid 被申请对话的ID
     * @return 如果能加入返回：true；否则返回false；
     */
    public static boolean canJoin(String oid) {
       return true;
        // return !(oid != null && user_user.containsKey(oid) && user_user.get(oid) != null);
    }

    /**
     * 添加视频对象
     * @param uid 申请对话的ID
     * @param oid 被申请对话的ID
     * @return 是否是创建者：如果没有申请对话ID为创建者，否则为为加入者。创建者返回：true；加入者返回：false；
     */
    public static boolean addUser(String uid, String oid) {
        if(oid != null && !oid.isEmpty()) {
//            VideoSocketServer.user_user.put(uid, oid);
//            VideoSocketServer.user_user.put(oid, uid);

            return false;
        } else {
//            VideoSocketServer.user_user.put(uid, null);

            return true;
        }
    }

    /**
     * 移除聊天用户
     * @param session 移除的session
     * @param uid 移除的UID
     */
    private static void remove(Session session, String uid) {
//        String oid = user_user.get(uid);
//
//        if(oid != null) user_user.put(oid, null); // 设置对方无人聊天
//
//        sessions.remove(uid); // 异常session
//        user_user.remove(uid); // 移除自己
//
//        try {
//            if(session != null && session.isOpen()) session.close(); // 关闭session
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
