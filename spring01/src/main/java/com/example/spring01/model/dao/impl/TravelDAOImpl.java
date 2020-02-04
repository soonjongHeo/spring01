package com.example.spring01.model.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.spring01.model.dao.TravelDAO;
import com.example.spring01.model.dto.TravelDTO;

@Repository
public class TravelDAOImpl implements TravelDAO {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(TravelDAOImpl.class);
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<TravelDTO> travelList() {
		logger.info("travelList called...");
		return sqlSession.selectList("travel.travelList");
	}

	@Override
	public void travelInsert(TravelDTO dto) {
		logger.info("travelWrite called...");
		sqlSession.insert("travel.travelInsert", dto); 
	}

	@Override
	public TravelDTO viewTravel(String travelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTravel(String travelId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTravel(TravelDTO vo) {
		// TODO Auto-generated method stub

	}

}
