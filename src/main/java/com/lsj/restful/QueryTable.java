package com.lsj.restful;

import com.lsj.dao.T_dim_date;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * restful测试类
 *
 * @author
 * @create 2017-07-26 下午3:19
 */
@Path("query")
public class QueryTable {

    @GET
    @Path("tdimdate/detail")
    @Produces(MediaType.TEXT_HTML)
    public String getDetail(@QueryParam("year") String year,
                         @QueryParam("month") String month,
                         @QueryParam("start") Integer start,
                         @DefaultValue("10") @QueryParam("pageSize") Integer pageSize) {
        System.out.println("======================");
        System.out.println("year="+year+",month="+month+",start="+start+",pageSize="+pageSize);
        JSONArray json=new T_dim_date().get_data_detail(year,month,start,pageSize);
        System.out.print("================="+json.toString());
        return json.toString();
    }

    @GET
    @Path("tdimdate/count")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCnt(@QueryParam("year") String year,
                         @QueryParam("month") String month) {
        System.out.println("======================");
        System.out.println("year="+year+",month="+month);
        JSONObject json=new T_dim_date().get_data_cnt(year, month);
        System.out.print("================="+json.toString());
        return json.toString();
    }


}