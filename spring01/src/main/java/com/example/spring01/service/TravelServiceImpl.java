package com.example.spring01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring01.model.dao.TravelDAO;
import com.example.spring01.model.dto.TravelDTO;

@Service
public class TravelServiceImpl implements TravelService {

	@Inject
	TravelDAO travelDao;
	
	@Override
	public List<TravelDTO> travelList() {
		return travelDao.travelList();
	}

	@Override
	public void travelInsert(TravelDTO dto) {
		travelDao.travelInsert(dto);

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
	public void updateTravel(TravelDTO dto) {
		// TODO Auto-generated method stub

	}

}
