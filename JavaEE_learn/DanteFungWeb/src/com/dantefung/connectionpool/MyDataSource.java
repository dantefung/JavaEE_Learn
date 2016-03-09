package gz.itcast.a_pool;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.sql.DataSource;
//模拟连接池的原理（实现DataSource接口）
public class MyDataSource implements DataSource{
	
	private static String url = "jdbc:mysql://localhost:3306/day18";
	private static String driverClass = "com.mysql.jdbc.Driver";
	private static String user = "root";
	private static String password = "root";
	
	private static int initCount = 5;//初始化连接数
	
	private static int maxCount = 10;//最大并发连接数
	
	private static int currentCount = 1;//用户记录当前有几个连接对象了
	
	//设计一个容器用于存储多个连接对象
	private static LinkedList<Connection> pool = new LinkedList<Connection>();
	
	static{
		try {
			Class.forName(driverClass);
			//初始化若干个连接对象
			for(int i=1;i<=initCount;i++){
				pool.addLast(createConnection());
				currentCount++;
			}
		}  catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//内部使用的获取连接的方法
	private static Connection createConnection() throws Exception{
		final Connection conn =  DriverManager.getConnection(url, user, password);//真实的Connection
		/*//创建一个Connection的静态代理类
		Connection myConn = new MyConnection(conn,pool);*/
		
		//创建一个Connection的动态代理类
		Connection myConn = (Connection)Proxy.newProxyInstance(
								conn.getClass().getClassLoader(),
								new Class[]{Connection.class}, 
								 new InvocationHandler() {
									
									@Override
									public Object invoke(Object proxy, Method method, Object[] args)
											throws Throwable {
										//close方法需要重新，其他方法调回原来的方法
										if("close".equals(method.getName())){
											//把连接放回连接池
											pool.addLast(conn);
										}else{
											//其他方法调回原来的方法,返回结果
											return method.invoke(conn, args);
										}
										return null;
									}
								});
		
		
		//返回是代理类实例
		return myConn;
	}

	//给外部使用的获取连接对象
	@Override
	public Connection getConnection() {
		//5个或5个以下的情况
		//1)如果容器中还有连接对象，则取出第一个返回  
		if(pool.size()>0){
			return pool.removeFirst();
		}
		
		//5个以上10个以下
		if(currentCount<=maxCount){
			try {
				currentCount++;
				return createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//超过10个
		throw new RuntimeException("已经超过最大连接数，请稍后再来...");
		
	}
	
	//给外部使用的释放连接的方法
	public void release(Connection conn){
		//程序用完连接之后，放回池中
		pool.addLast(conn);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
	}
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
	}
	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}
	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return null;
	}

}
