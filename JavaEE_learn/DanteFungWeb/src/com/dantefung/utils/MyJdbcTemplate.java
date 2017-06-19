package com.winanet.netd.dao.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.winanet.netd.model.pm.PmProduct;
import com.winanet.netd.model.pm.PmProductSale;

/**
 * Created by Dante Fung on 2017/6/7.
 */
public class MyJdbcTemplate {

    public static void save(String sql, Object[] params){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JdbcUtil.getConnection();
            conn.createStatement();
            pstmt = conn.prepareStatement(sql);
            for(int idx = 1; idx <= params.length; idx ++){
                System.out.println(params[idx-1]);
                pstmt.setObject(idx, params[idx-1]);
            }
            if(!pstmt.execute()){
                System.out.println("保存成功!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != pstmt){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != conn){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    
    public static <T> T query(String sql, Class<T> clazz, Object[] params){
    	T t = null;
        try {
        	 QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());  
              t = (T) runner.query(sql,new BeanHandler(clazz), params);  
        } catch (Exception e) {
        	e.printStackTrace();
        }
    	return t;
    }
    
    public static <T> T query(String sql, Class<T> clazz){
    	T t = null;
    	try {
    		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());  
    		t = (T) runner.query(sql,new BeanHandler(clazz));  
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return t;
    }
    
    public static <T> T queryDynamicParams(String sql, Class<T> clazz, Object... params){
    	T t = null;
    	try {
    		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());  
    		t = (T) runner.query(sql,new BeanHandler(clazz),params);  
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return t;
    }
    public static <T> T queryProductSaleByProductNo( Class<T> clazz, String productNo){
    	T t = null;
    	try {
    		String sql=  "select * from pm_product_sale where product_no = ? and company_no = 'CN'";
    		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());  
    		t = (T) runner.query(sql,new BeanHandler(clazz),productNo);  
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return t;
    }
    
    public static Object[] queryArray(String sql) throws Exception{
    	 QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource()); 
    	 Object[] rs = runner.query(sql, new ArrayHandler());
    	 return rs;
    }
    
    //BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
    public static <T> List<T> queryForList(String sql,Class<T> clazz) throws Exception {
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        List<T> list = runner.query(sql, new BeanListHandler<T>(clazz));
        return list;
    }
    
  /*  public static PmProduct queryPmProduct(String sql){
    	
    	Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
          //1.获取Connection
          connection = JdbcUtil.getDataSource().getConnection();
          //2.获取Statement
          statement = connection.createStatement();
          //3.准备Sql
          //4.执行查询，得到ResultSet
//          rs = statement.executeQuery(sql);
          DatabaseMetaData metaData = connection.getMetaData(); 

          rs = metaData.getColumns(null, JdbcUtil.getUser(), "PM_PRODUCT_SALE", null); 
          //5.处理ResultSet
          while(rs.next()){
            //rs.get+数据库中对应的类型+(数据库中对应的列别名)
            String column = rs.getString("COLUMN_NAME");
            System.out.println(column);
            
          }
          return null;
        } catch (Exception e) {
          e.printStackTrace();
        }finally{
          //6.关闭数据库相应的资源
        	
        }
		return null;
    }*/
     

    /**
     * 定制方法...先这样吧....
     * @param callStr
     */
    public static String genPdOutWareNo(String callStr){
        Connection conn = null;
        CallableStatement cstmt = null;
        try{
            conn = JdbcUtil.getConnection();
            cstmt = conn.prepareCall(callStr);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.execute();
            String targetNo = cstmt.getString(1);
            System.out.println(targetNo);
            return cstmt.getString(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void call(String callStr){
        Connection conn = null;
        CallableStatement cstmt = null;
        try{
            conn = JdbcUtil.getConnection();
            cstmt = conn.prepareCall(callStr);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.execute();
            System.out.println(cstmt.getString(1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
    	PmProductSale query = MyJdbcTemplate.query("select * from pm_product_sale where product_no = ?", PmProductSale.class, new Object[]{"A040600CN1"});
    	System.out.println(query.getProductName());
    	Object[] array = MyJdbcTemplate.queryArray("select * from pm_product");
    	System.out.println(Arrays.asList(array));
    	PmProduct p = MyJdbcTemplate.query("select * from pm_product where product_no='A040600CN1'", PmProduct.class);
    	System.out.println(p.getProductName());
    	List<PmProduct> queryForList = MyJdbcTemplate.queryForList("select * from pm_product where product_no='A020007MY0'", PmProduct.class);
    	System.out.println(queryForList);
    	List<PmProductSale> queryForList2 = MyJdbcTemplate.queryForList("select * from pm_product_sale", PmProductSale.class);
    	System.out.println(queryForList2);
    	PmProduct queryDynamicParams = MyJdbcTemplate.queryDynamicParams("select * from pm_product where product_no = ?", PmProduct.class, "A020007MY0");
    	System.out.println("queryDynamicParams:" + queryDynamicParams);
    	PmProductSale pmProductSale = MyJdbcTemplate.queryProductSaleByProductNo(PmProductSale.class, "A040600CN1");
    	System.out.println(pmProductSale.getProductName());
	}
    public static Map callProcShipEfuboOrder(String companyCode, String orderNo,
			String shipName, String shipProvince, String shipCity,
			String shipAddress, String shipZipcode, String shipPhone,
			String shipMobile, String productCode, String quantity, String xId,
			String userCode, String supplierNo, String memo) {
		// TODO Auto-generated method stub
		/* 0:失败 1:成功 */
		CallableStatement cstmt = null;
		 Connection conn = null;
	
		Map<String, String> map = new HashMap<String, String>();
        try{
        	
        	
        	 conn = JdbcUtil.getConnection();
            cstmt = conn.prepareCall("{call Pack_EFUBO.SP_INPUT_SEND(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
           
            cstmt.setString(1, companyCode);
            cstmt.setString(2, orderNo);
            cstmt.setString(3, shipName);
            cstmt.setString(4, shipProvince);
            cstmt.setString(5, shipCity);
            cstmt.setString(6, shipAddress);
            cstmt.setString(7, shipZipcode);
            cstmt.setString(8, shipPhone);
            cstmt.setString(9, shipMobile);
            cstmt.setString(10, productCode);
            cstmt.setString(11, quantity);
            cstmt.setString(12, xId);
            cstmt.setString(13, userCode);
            cstmt.setString(14, supplierNo);
            cstmt.setString(15, memo);
            
            cstmt.registerOutParameter(16,java.sql.Types.VARCHAR);
            cstmt.registerOutParameter(17,java.sql.Types.VARCHAR);
            cstmt.registerOutParameter(18,java.sql.Types.VARCHAR);
         
            cstmt.execute();
            String o_return = cstmt.getString(16);
            String o_msg = cstmt.getString(17);
            String o_ow_no = cstmt.getString(18);
            
            map.put("o_return", o_return);
            map.put("o_msg", o_msg);
            map.put("o_ow_no", o_ow_no);
            
        }catch (Exception e){
            e.printStackTrace();
        }
		return map;
	}
}
