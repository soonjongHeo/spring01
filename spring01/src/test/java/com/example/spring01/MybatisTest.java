package com.example.spring01;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = 
//{"file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml"})
public class MybatisTest {
	private static final Logger logger = 
			LoggerFactory.getLogger(MybatisTest.class);
	
	@Inject
	private SqlSessionFactory sqlFactory;
	
	@Test
	public void testFactory() {
		logger.info("sqlFactory: " + sqlFactory);
	}
	
	@Test
	public void testSession() {
		try{
			SqlSession sqlSession = sqlFactory.openSession();
			logger.info("sqlSession: " + sqlSession);
			logger.info("mybatis 연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
