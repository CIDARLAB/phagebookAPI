/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.phagebookapi;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.eclipse.jetty.websocket.WebSocket;
import org.json.simple.JSONValue;

/**
 *
 * @author prashantvaidyanathan
 */

@Slf4j
public class Phagebook implements MessageListener
{
    
    public Object receivedObject;
    private boolean received;
    private boolean successfulResult;
    
    public String requestId;
    public Channel channel;
    
    
    private WebSocket.Connection connection;
  
    public Phagebook(PhagebookConnection phagebookConn)
    {
        System.out.println("New Connection established");
        connection = phagebookConn.getServerConnection();
        phagebookConn.phagebookSocket.addMessageListener(this);
    }
    
    private static String getRequestId()
    {
        String reqId = "";
        reqId += System.currentTimeMillis();
        //System.out.println("Generated Req ID :" + reqId);
        return reqId;
    }
    
//    public Object createUser(String username,String password){
//        Map createUserMap = new HashMap();
//        createUserMap.put("username", username);
//        createUserMap.put("password", password);
//        return login(createUserMap);
//    }
    
    public Object login(Map map){
        JSONObject resultObject = null;
        //getRequestId();
        channel = Channel.login;
        received = false;
        successfulResult = false;
        try {
            requestId = getRequestId();
            Map createUserMap = new HashMap();
            createUserMap.put("channel", channel.toString());
            createUserMap.put("data", map);
            createUserMap.put("requestId", requestId);
            
            StringWriter queryStringWriter = new StringWriter(); //converting to string
            JSONValue.writeJSONString(createUserMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime < Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= Args.maxTimeOut)
            {
                System.out.println("System time out. Please check your Phagebook Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = JSONObject.fromObject(receivedObject);
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Phagebook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
    
    
    public Object createStatus(Map map){
        JSONObject resultObject = null;
        getRequestId();
        channel = Channel.createStatus;
        received = false;
        successfulResult = false;
        try {
            requestId = getRequestId();
            Map createUserMap = new HashMap();
            createUserMap.put("channel", channel.toString());
            createUserMap.put("data", map);
            createUserMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(createUserMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime < Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = JSONObject.fromObject(receivedObject);
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Phagebook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
    @Override
    public void messageRecieved(OnMessageEvent event) 
    {
        String message = event.getMessage();
        String nullString = null;
        //System.out.println("Th    is is a message :" + message);
        JSONObject messageObject = JSONObject.fromObject(message);
        try
        {
            if (this.requestId.equals(messageObject.get("requestId").toString())) 
            {
                /*if (messageObject.get("channel").equals("say")) 
                {

                    JSONObject dataObject = JSONObject.fromObject(messageObject.get("data"));
                    if (dataObject.get("text").equals(nullString)) 
                    {
                        System.out.println("No results found!");
                        received = true;
                        receivedObject = messageObject.get("data");

                    } 
                    else 
                    {
                        System.out.println(dataObject.get("text"));
                    }
                }*/
                if (messageObject.get("channel").equals(this.channel.toString())) 
                {
                    successfulResult = true;
                    receivedObject = messageObject.get("data");
                    received = true;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error accessing one of the object values");
        }
    }
    
}
