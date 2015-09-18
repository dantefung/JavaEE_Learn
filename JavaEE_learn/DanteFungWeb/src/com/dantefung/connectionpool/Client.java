package com.dantefung.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;

public class Client {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		MyPool pool = new MyPool();
		Connection conn = null;
		for(int i=0; i<11; i++)
		{
			conn = pool.getConnection();
			System.out.println(conn);
		}
	}

}
