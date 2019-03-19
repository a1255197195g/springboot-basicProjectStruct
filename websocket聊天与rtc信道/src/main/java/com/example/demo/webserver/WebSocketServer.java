package com.example.demo.webserver;


import com.example.demo.entity.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Pattern;

@ServerEndpoint("/websocket/{id}")
@Component
public class WebSocketServer {

    //记录档期那在线连接数量
    private static  Integer onlineCount = 0;
    //concurrent包的线程安全set,用来存放每个客户端对应的websocket对象
    private static CopyOnWriteArraySet<WebSocketServer>  wsSet =  new CopyOnWriteArraySet<WebSocketServer>();
    //与某个客户端的连接会话，需要通过它来与客户端进行数据收发
    private Session session;
    //某个客户对应的ID
    private Long userId;

    private ObjectMapper objectMapper = new ObjectMapper();

    //1.连接
    @OnOpen
    public  void onOpen(Session session, @PathParam("id") long id ) throws Exception {
        //记录该session
        this.session = session;
        //用于判断当前ID是谁
        this.userId = id;
        //保存session到集合中
        wsSet.add(this);
        //有新的连接加入，连接数+1
        addOnlineCount();
        System.out.println("有新的连接加入.当前在线人数为"+getOnlineCount());
    }

    //2.接受到消息
    @OnMessage
    public  void onMessage( String message, Session session ) throws Exception {

        //使用json解包出数据，然后获取到需要发送给谁。
        //通过webSocketServer对象持有的session来操作
        System.out.println("接受到来自"+this.userId+"的消息:"+message);
        Message msg = objectMapper.readValue(message, Message.class);

        //1.判断消息类型，是单发还是群发
        //2.如果是单发，则获取目标ID，进行单发
        //3.如果是群发，则获取群中所有人ID，进行群发
        //4.如果是发送给所有人，则直接发送给所有人
        if ( msg.getMsgType() == 0 ){

            sendMessageToAll(message);

        }else if( msg.getMsgType() == 1 ){

            //所有群友的ID
            Long groupId = msg.getGroupId();
            if ( groupId < 0  ){
                throw new Exception("groupId异常");
            }
            String  ids[] = msg.getGroupFriendIds().split(",");
            Set<Long> idsSet = new HashSet<Long>();
            for ( String id : ids ) {

                if ( isInteger(id) ){
                    Long longId = Long.parseLong(id);
                    idsSet.add(longId);
                }
            }
            sendMessageToPartByIds(message, idsSet);

        }else if( msg.getMsgType() == 2 ){
            //获取friendId
            Long friendId = msg.getFriendId();
            Long targetId = msg.getTargetId();
            sendMessageToOneById(message, targetId);
            sendMessageToOneById(message, friendId);
        }
    }

    //3.关闭
    @OnClose
    public void onClose() throws Exception {
        //将当前webserver从set中移除。
        wsSet.remove(this);
        //连接数-1
        subOnlineCount();
        System.out.println("有用户退出。当前连接人数为:"+getOnlineCount());
    }


    //4.错误
    @OnError
    public void onError( Session session, Throwable error ) throws Exception {
        System.out.println("发生错误:"+this.userId);
    }


    //5.获取在线数量
    public static synchronized  int getOnlineCount() throws Exception {
        return onlineCount;
    }

    //6.增加在线数量
    public static synchronized void addOnlineCount() throws Exception {
        onlineCount++;
    }

    //7.减少在线数量
    public static synchronized void subOnlineCount() throws Exception{
        onlineCount--;
    }


    //9.单发给某个人
    public void sendMessageToOneById( String message, Long userId ) throws Exception {
        if ( this.userId.equals(userId) ){
            sendMessageToOne(message, this);
        }else {
            sendMsgIfUserIdExists(message, userId);
        }
    }

    //10.群发给部分人
    public  void sendMessageToPartByIds( String message, Set<Long> set ) throws Exception {

        for ( Long userId : set ){
            this.sendMsgIfUserIdExists(message, userId);
        }
    }

    //11.群发给所有在线的人
    public static void sendMessageToAll( String message ) throws Exception {

        System.out.println("-----------所有人群发开始--------------");
        for ( WebSocketServer webSocketServer : wsSet ){
            sendMessageToOne(message, webSocketServer);
        }
        System.out.println("-------------所有人群发结束---------------");
    }


    //判断被发送人是否存在
    public static void sendMsgIfUserIdExists(  String message, Long userId ) throws Exception {
        for ( WebSocketServer webSocketServer : wsSet ){
            if ( webSocketServer.userId.equals(userId)  ){
               sendMessageToOne( message , webSocketServer);
            }
        }
    }

    //发送给某人消息
    public static  void sendMessageToOne( String message, WebSocketServer webSocketServer ) throws Exception{
        webSocketServer.session.getBasicRemote().sendText(message);
        System.out.println("发送用户:"+webSocketServer.userId);
    }

    //判断字符串是否是数字
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
