package com.lsj.restful;

import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * restful测试类
 *
 * @author
 * @create 2017-07-26 下午3:19
 */
@Path("hello")
public class Hello {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(){
        return "Hello,I am text!";
    }


    @POST
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello,I am xml!" + "</hello>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        System.out.println("get:text_html");
        return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello,I am html!" + "</body></h1>" + "</html> ";
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello1(@PathParam("id") int id) {
        System.out.println("get:text_html,parameter{id}");
        return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello,I am html!===="+id + "</body></h1>" + "</html> ";
    }

    @Path("say")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String say() {
        System.out.println("hello world");
        return "hello world";
    }

    @Path("sayjson")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sayjson() {
        System.out.println("hello world,sayJson");
        String jsonStr="{\"age\":190,\"sex\":\"男\",\"userName\":\"周伯通\"}";

        return jsonStr;


    }


}