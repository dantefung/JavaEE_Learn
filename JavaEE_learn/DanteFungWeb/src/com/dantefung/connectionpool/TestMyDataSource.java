package com.dantefung.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

//使用自定义连接池
public class TestMyDataSource {

	public static void main(String[] args) throws Exception {
		//1)创建数据源（连接池）对象
		MyDataSource ds = new MyDataSource();
		
		//2)从数据源中获取连接对象
		for(int i=1;i<=11;i++){
			Connection conn = ds.getConnection();
			System.out.println(conn);
			
			//用完连接之后，怎么办？
			//放回连接池中
			//注意：如果从连接池中获取了连接对象使用完后，记住一定要放回连接池中！并且不能直接关闭连接，如果关闭连接，就失去了连接池的意义！！
			//问题：如果客户用完之后，不小心调用了Connection.close()方法，怎么办？
			
			//解决办法：重写Connection的close方法，让其能够拥有放回连接到连接池中的功能。
			
			/*if(i==4){//模拟第4个用户释放连接
				ds.release(conn);
			}*/
			
			if(i==4){
				conn.close();
			}
		}
	}
}
