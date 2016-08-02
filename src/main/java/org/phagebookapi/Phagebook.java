/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.phagebookapi;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
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
    
    private static Map formattedData(String userEmail, String password){
        Map dataToSend = new HashMap();
        dataToSend.put("username", userEmail);
        dataToSend.put("password", password);
        return dataToSend;
    }
    
    private static Map formattedData(String userEmail, String password, String objId){
        Map dataToSend = new HashMap();
        dataToSend.put("username", userEmail);
        dataToSend.put("password", password);
        dataToSend.put("id", objId);
        return dataToSend;
    }
    
    private static Map formattedData(String userEmail, String password, String objId, String status){
        Map dataToSend = new HashMap();
        dataToSend.put("username", userEmail);
        dataToSend.put("password", password);
        dataToSend.put("id", objId);
        dataToSend.put("status", status);
        return dataToSend;
    }
    
    private Object emit(Map dataToSend) throws IOException{
        JSONObject resultObject = null;
        requestId = getRequestId();
        Map protocol = new HashMap();
        protocol.put("channel", channel.toString());
        protocol.put("data", dataToSend);
        protocol.put("requestId", requestId);

        StringWriter queryStringWriter = new StringWriter();
        JSONValue.writeJSONString(protocol, queryStringWriter);
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
            System.out.println("System time out. Please check your Clotho Connection");
        }
        received = false;

        if(successfulResult)
        {
            resultObject = JSONObject.fromObject(receivedObject); //Should probably not be a JSON object..
        }
        return resultObject;
    }
    
//   
//    public Object login(Map map){
//        JSONObject resultObject = null;
//        channel = Channel.login;
//        received = false;
//        successfulResult = false;
//        try {
//            requestId = getRequestId();
//            Map createUserMap = new HashMap();
//            createUserMap.put("channel", channel.toString());
//            createUserMap.put("data", map);
//            createUserMap.put("requestId", requestId);
//            
//            StringWriter queryStringWriter = new StringWriter(); //converting to string
//            JSONValue.writeJSONString(createUserMap, queryStringWriter);
//            String queryString = queryStringWriter.toString();
//            
//            long startTime = System.currentTimeMillis();
//            long elapsedTime = 0;
//            
//            connection.sendMessage(queryString);
//            while((!received) && (elapsedTime < Args.maxTimeOut))
//            {
//                System.out.print("");
//                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
//            }
//            if(elapsedTime >= Args.maxTimeOut)
//            {
//                System.out.println("System time out. Please check your Phagebook Connection");
//            }
//            received = false;
//            
//            if(successfulResult)
//            {
//                resultObject = JSONObject.fromObject(receivedObject); //Should probably not be a JSON object..
//            }
//            return resultObject;
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(Phagebook.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//        }
    
    
    public Object createStatus(String username, String password, String status){
        channel = Channel.CREATE_STATUS;
        received = false;
        successfulResult = false;
        try {
            return emit(formattedData(username, password, null, status));
        } catch (IOException ex) {
            Logger.getLogger(Phagebook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object createProjectStatus(String username, String password, String projId, String status){
        channel = Channel.CREATE_PROJECT_STATUS;
        received = false;
        successfulResult = false;
        try {
            return emit(formattedData(username, password, projId, status));
        } catch (IOException ex) {
            Logger.getLogger(Phagebook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object changeOrderingstatus(String username, String password, String orderId, String status){
        channel = Channel.CHANGE_ORDERING_STATUS;
        received = false;
        successfulResult = false;
        try {
            return emit(formattedData(username, password, orderId, status));
        } catch (IOException ex) {
            Logger.getLogger(Phagebook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object getProjects(String username, String password){
        channel = Channel.GET_PROJECTS;
        received = false;
        successfulResult = false;
        try {
            return emit(formattedData(username, password));
        } catch (IOException ex) {
            Logger.getLogger(Phagebook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object getProject(String username, String password, String projId){
        channel = Channel.GET_PROJECT;
        received = false;
        successfulResult = false;
        try {
            return emit(formattedData(username, password, projId));
        } catch (IOException ex) {
            Logger.getLogger(Phagebook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
    public Object getOrders(String username, String password){
        channel = Channel.GET_ORDERS;
        received = false;
        successfulResult = false;
        try {
            return emit(formattedData(username, password));
        } catch (IOException ex) {
            Logger.getLogger(Phagebook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object getOrder(String username, String password, String orderId){
        channel = Channel.GET_ORDER;
        received = false;
        successfulResult = false;
        try {
            return emit(formattedData(username, password, orderId));
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
        JSONObject messageObject = JSONObject.fromObject(message);
        try
        {
            if (this.requestId.equals(messageObject.get("requestId").toString())) 
            {
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
