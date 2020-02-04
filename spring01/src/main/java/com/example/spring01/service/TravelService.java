package com.example.spring01.service;

import java.util.List;

import com.example.spring01.model.dto.TravelDTO;

public interface TravelService {
	public List<TravelDTO> travelList();
	public void travelInsert(TravelDTO dto);
	public TravelDTO viewTravel(String travelId);
	public void deleteTravel(String travelId);
	public void updateTravel(TravelDTO dto); 
}
