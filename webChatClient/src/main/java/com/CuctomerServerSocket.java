package com;


import org.codehaus.jackson.map.ObjectMapper;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")

public class CuctomerServerSocket {

    JabberImplement jabber = new JabberImplement();

    AbstractXMPPConnection connection;

    @OnOpen
    public void onConnect(Session session) throws Exception {
        System.out.println("Connected ... ");
//        connection = jabber.connection("user", "user");
//        connection.login();
//        jabber.createInstantChatRoom(connection, "user");
    }


    @OnMessage
    public String onMessage(Session session, String msg) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Message recMsg = mapper.readValue(msg, Message.class);



        if (recMsg.getType().equals("chat")) {
            connection = jabber.connection( recMsg.getData().getUser(), recMsg.getData().getUser());
            connection.login();
            jabber.chat(connection, recMsg.getData().getUser(), recMsg.getData().getText());
        }


//        jabber.chat(connection, "user", msg);
//
//        System.out.println("Server :: message " + msg);

        return "Server : " + recMsg.getData().getUser() + " : " + recMsg.getData().getText();
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) throws Exception {

        connection.disconnect();
        System.out.println("Server disconnected " + closeReason.getReasonPhrase());
    }
}
