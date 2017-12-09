package com.lsj.dao;

import com.lsj.tools.c3po.C3p0Utils;
import com.lsj.tools.c3po.DBUtil_BO;
import com.lsj.tools.c3po.DBUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;

/**
 * Created by lsj on 2017/12/9.
 */
public class T_dim_date {
    private final String detailSql = "select date1,year,month,day,week,quarter from (select row_number() over(order by t.date1) rn ,t.* from t_dim_date t where year=? and month=? order by t.date1) tmp where rn >=? limit ?";
    private final String cntSql = "select count(*) as cnt from t_dim_date t where year=? and month=? ";
    private JSONObject result_data;
    private JSONArray result_datas;

    public JSONArray get_data_detail(String year, String month, Integer start, Integer pagSize) {
        DBUtil_BO dbBo = new DBUtil_BO();
        try {
            System.out.println("==============================接口:T_dim_date");
            dbBo.conn = C3p0Utils.getConnection("hive");//取用一个连接
            dbBo.st = dbBo.conn.prepareStatement(this.detailSql);
            dbBo.st.setString(1, year);
            dbBo.st.setString(2, month);
            dbBo.st.setInt(3, start);
            if (pagSize == null) {
                pagSize = DBUtils.PAGE_SIZE;
            }
            dbBo.st.setInt(4, pagSize);
            dbBo.rs = dbBo.st.executeQuery();

            //将结果转为Json对象
            result_datas = DBUtils.resultSetToJsonArry(dbBo.rs);
        } catch (SQLException e1) {
            e1.printStackTrace();
            //结果集遍历完了，手动释放连接回连接池
            DBUtils.realseSource(dbBo);
        }
        //结果集遍历完了，手动释放连接回连接池
        DBUtils.realseSource(dbBo);
        return result_datas;
    }

    public JSONObject get_data_cnt(String year, String month) {
        DBUtil_BO dbBo = new DBUtil_BO();
        try {
            dbBo.conn = C3p0Utils.getConnection("hive");//取用一个连接
            dbBo.st = dbBo.conn.prepareStatement(this.cntSql);
            dbBo.st.setString(1, year);
            dbBo.st.setString(2, month);
            dbBo.rs = dbBo.st.executeQuery();

            //将结果转为Json对象
            result_data = DBUtils.resultSetToJsonObject(dbBo.rs);
        } catch (SQLException e1) {
            e1.printStackTrace();
            //结果集遍历完了，手动释放连接回连接池
            DBUtils.realseSource(dbBo);
        }
        //结果集遍历完了，手动释放连接回连接池
        DBUtils.realseSource(dbBo);
        return result_data;
    }

    public static void main(String arg[]) {

        T_dim_date tdimdate = new T_dim_date();
        JSONArray json = tdimdate.get_data_detail("2017", "01", 0, 10);
        System.out.println("=================:\n" + json);
    }

}
