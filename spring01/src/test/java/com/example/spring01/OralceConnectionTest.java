package com.example.spring01;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OralceConnectionTest {
	private static final Logger logger = 
		LoggerFactory.getLogger(OralceConnectionTest.class);
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@192.168.111.100:1521/orcl";
	private static final String USER = "soon9086";
	private static final String PW = "jong0735";
	
	@Test
	public void testConnection() throws Exception{
		Class.forName(DRIVER);
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			System.out.println("오라클에 연결되었습니다.");
			logger.info("오라클에 연결되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
