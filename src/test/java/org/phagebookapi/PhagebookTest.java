/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.phagebookapi;

import java.io.UnsupportedEncodingException;
import org.phagebookapi.Phagebook;
import org.phagebookapi.PhagebookConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.clothoapi.clotho3javaapi.Clotho;
import org.clothoapi.clotho3javaapi.ClothoConnection;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author prashantvaidyanathan
 */
public class PhagebookTest {
    
    private static String username1;
    private static String username2;
    private static String password;
    private static String personID1;
    private static String personID2;
    private static String testResult1;
    private static String testResult2;
    private static String projectID;
    private static Map res1 = new HashMap();
        public PhagebookTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
//    
    @Before
    public void setUp() {
        
    }
//    
    @After
    public void tearDown() {
    }
//    
//    @Test
//    public void login(){
//        PhagebookConnection conn = new PhagebookConnection(TestArgs.phagebookLocalAddress);
//        Phagebook phagebookObject = new Phagebook(conn);
//        ClothoConnection connect = new ClothoConnection(TestArgs.clothoLocation);
//        Clotho clothoObject = new Clotho(connect);
//        Map newUserMap = new HashMap();
//        /********************* PUT YOUR OWN USERNAME (not email) AND PW **************************/
//        // This test will fail if you do not use an existing user because 
//        // the log in will fail (because the user does not exist).
//        newUserMap.put("username", "asdf");
//        newUserMap.put("password", "12345");
//        clothoObject.createUser(newUserMap);
//        res1 = (Map) phagebookObject.login(newUserMap);
//        
//        System.out.println("Result 1 ::" + res1.toString());
//        conn.closeConnection();
//
//    }
//    
//    @Test
//    public void createStatus(){
//        PhagebookConnection conn = new PhagebookConnection(TestArgs.phagebookLocalAddress);
//        Phagebook phagebookObject = new Phagebook(conn);
//        
//        testResult1= "ReggaeSharkDNA";
//        
//        Map newUserMap1 = new HashMap();
//        newUserMap1.put("text", testResult1);
//        //JSONObject userObject = new JSONObject();
//        Map userObject = new HashMap();
//        System.out.println("Checkpoint1");
//        //System.out.print("res1::" + res1);
//        userObject = (Map) res1.get("personObject");
//       // System.out.println("res1 data ::" + userObject);
//        if(userObject.containsKey("id")){
//            String userID = (String) userObject.get("id");
//            System.out.println("res1 data ::" + userObject);
//            newUserMap1.put("personID", userID);
//            System.out.println("Added personID to newUserMap1");
//            
//        }
//     
//        Map res2 = (Map)phagebookObject.createStatus(newUserMap1);
//        System.out.println("Result 2 ::" + res2.toString());
//        username1 = "testUser1" + System.currentTimeMillis();
//        username2 = "testUser2" + System.currentTimeMillis();
//        password = "testPassword";
//        personID1 = "testID1";
//        projectID = "phagebookProject";
//        testResult1= "ReggaeSharkDNA";
//        testResult2= "created pBad-pTet";
//        
//        Map newUserMap1 = new HashMap();
//        newUserMap1.put("username", username1);
//        newUserMap1.put("password", password);
//        newUserMap1.put("personID", personID1);
//        newUserMap1.put("text", testResult1);
//        newUserMap1.put("projectID", projectID);
//        
//        Map newUserMap2 = new HashMap();
//        newUserMap2.put("username", username2);
//        newUserMap2.put("password", password);
//        newUserMap2.put("personID", personID2);
//        newUserMap2.put("text", testResult2);
//        newUserMap2.put("projectID", projectID);
//        
//        Object res1 = phagebookObject.createStatus(newUserMap1);
//        // clothoObject.logout();
//        Object res2 = phagebookObject.createStatus(newUserMap2);
//       // clothoObject.logout();
//        
//        System.out.println("Result 1 ::" + res1.toString());
//        System.out.println("Result 2 ::" + res2.toString());
//        
//        
//        conn.closeConnection();
//    }
//
//
//    @Test
//    public void queryOne()
//    {
//        String id = "testAPIquery" + System.currentTimeMillis();
//        PhagebookConnection conn = new PhagebookConnection(TestArgs.phagebookLocalAddress);
//        Phagebook clothoObject = new Phagebook(conn);
//        
//        Map loginMap = new HashMap();
//        loginMap.put("username", username1);
//        loginMap.put("credentials", password);
//        
//        clothoObject.login(loginMap);
//        
//        Map createPart = new HashMap();
//        createPart.put("name","createdPart");
//        createPart.put("sequence","atataagcgcaaa");
//        createPart.put("schema","org.clothocad.testapi");
//        createPart.put("id", id);
//        
//        Object result = clothoObject.create(createPart);
//        System.out.println("FROM CLOTHO!! "+result);
//        Map personMap = new HashMap();
//        personMap.put("id",id);
//        
//        Object ret = clothoObject.queryOne(personMap);
//        assertEquals(((JSONObject)ret).get("name").toString(),"createdPart");
//        assertEquals(((JSONObject)ret).get("id").toString(),id);
//        assertEquals(((JSONObject)ret).get("schema").toString(),"org.clothocad.testapi");
//        assertEquals(((JSONObject)ret).get("sequence").toString(),"atataagcgcaaa");
//        
//        clothoObject.logout();
//        
//        conn.closeConnection();
//    }
//    
//    
//    
//    @Test
//    public void query() //Tests Both Create All & Query
//    {
//        String id1 = "testAPIquery1" + System.currentTimeMillis() ;
//        String id2 = "testAPIquery2" + System.currentTimeMillis() ;
//        PhagebookConnection conn = new PhagebookConnection(TestArgs.phagebookLocalAddress);
//        Phagebook clothoObject = new Phagebook(conn);
//        
//        Map loginMap = new HashMap();
//        loginMap.put("username", username1);
//        loginMap.put("credentials", password);
//        
//        clothoObject.login(loginMap);
//        
//        List<Map> createAllMap = new ArrayList<Map>();
//        
//        Map createPart1 = new HashMap();
//        createPart1.put("name","createdPartQuery");
//        createPart1.put("sequence","atataagcgcaaa");
//        createPart1.put("schema","org.clothocad.testapi");
//        createPart1.put("id", id1);
//        
//        Map createPart2 = new HashMap();
//        createPart2.put("name","createdPartQuery");
//        createPart2.put("sequence","atataagcgcaaa");
//        createPart2.put("schema","org.clothocad.testapi");
//        createPart2.put("id", id2);
//        
//        createAllMap.add(createPart1);
//        createAllMap.add(createPart2);
//        
//        clothoObject.createAll(createAllMap);
//        
//        Map personMap = new HashMap();
//        personMap.put("name","createdPartQuery");
//        
//        Object ret = clothoObject.query(personMap);
//        assertEquals(((JSONObject)((JSONArray)ret).get(0)).get("id").toString(),id1);
//        assertEquals(((JSONObject)((JSONArray)ret).get(1)).get("id").toString(),id2);
//        assertEquals(((JSONObject)((JSONArray)ret).get(0)).get("name").toString(),"createdPartQuery");
//        assertEquals(((JSONObject)((JSONArray)ret).get(1)).get("name").toString(),"createdPartQuery");
//        
//       
//        conn.closeConnection();
//    }
//    
//    @Test
//    public void get()
//    {
//        String id = "testAPIget" + System.currentTimeMillis();
//        PhagebookConnection conn = new PhagebookConnection(TestArgs.phagebookLocalAddress);
//        Phagebook clothoObject = new Phagebook(conn);
//        
//        Map loginMap = new HashMap();
//        loginMap.put("username", username1);
//        loginMap.put("credentials", password);
//        
//        clothoObject.login(loginMap);
//        
//        Map createPart = new HashMap();
//        createPart.put("name","createdPartget");
//        createPart.put("sequence","atataagcgcaaa");
//        createPart.put("schema","org.clothocad.testapi");
//        createPart.put("id", id);
//        
//        clothoObject.create(createPart);
//        
//        Object ret = clothoObject.get(id);
//        assertEquals(((JSONObject)ret).get("name").toString(),"createdPartget");
//        assertEquals(((JSONObject)ret).get("id").toString(),id);
//        assertEquals(((JSONObject)ret).get("schema").toString(),"org.clothocad.testapi");
//        assertEquals(((JSONObject)ret).get("sequence").toString(),"atataagcgcaaa");
//        conn.closeConnection();
//    }
//    
//    @Test
//    public void getAll()
//    {
//        String id1 = "testAPIget1" + System.currentTimeMillis();
//        String id2 = "testAPIget2" + System.currentTimeMillis();
//        
//        PhagebookConnection conn = new PhagebookConnection(TestArgs.phagebookLocalAddress);
//        Phagebook clothoObject = new Phagebook(conn);
//        
//        Map loginMap = new HashMap();
//        loginMap.put("username", username1);
//        loginMap.put("credentials", password);
//        
//        clothoObject.login(loginMap);
//        
//        Map createPart1 = new HashMap();
//        createPart1.put("name","createdPartget1");
//        createPart1.put("sequence","atataagcgcaaa");
//        createPart1.put("schema","org.clothocad.testapi");
//        createPart1.put("id", id1);
//        
//        Map createPart2 = new HashMap();
//        createPart2.put("name","createdPartget2");
//        createPart2.put("sequence","atataagcgcaaa");
//        createPart2.put("schema","org.clothocad.testapi");
//        createPart2.put("id", id2);
//        
//        
//        clothoObject.set(createPart1);
//        clothoObject.set(createPart2);
//        
//        List<String> objIds = new ArrayList<String>();
//        objIds.add(id1);
//        objIds.add(id2);
//        
//        Object ret = clothoObject.getAll(objIds);
//        assertEquals(((JSONObject)((JSONArray)ret).get(0)).get("id").toString(),id1);
//        assertEquals(((JSONObject)((JSONArray)ret).get(1)).get("id").toString(),id2);
//        assertEquals(((JSONObject)((JSONArray)ret).get(0)).get("name").toString(),"createdPartget1");
//        assertEquals(((JSONObject)((JSONArray)ret).get(1)).get("name").toString(),"createdPartget2");
//        
//       
//        conn.closeConnection();
//    }
    
}
