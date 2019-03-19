package com.example.demo.entity;

public class Message {

    Long msgType; //消息类型
    Long groupId; //群id
    String groupFriendIds; //群组所有人的ID
    Long targetId; //发送人ID
    Long friendId; //接受者ID
    String  content; //发送的消息

    public Long getMsgType() {
        return msgType;
    }

    public void setMsgType(Long msgType) {
        this.msgType = msgType;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupFriendIds() {
        return groupFriendIds;
    }

    public void setGroupFriendIds(String groupFriendIds) {
        this.groupFriendIds = groupFriendIds;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
