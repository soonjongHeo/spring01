package com.example.spring01.service;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.example.spring01.model.dto.CartDTO;

@Service
public class GoogleChartServiceImpl implements GoogleChartService {

	@Inject //의존관계 주입(장바구니 서비스)
	CartService cartService; 
// 	ArrayList => json 객체
	@Override
	public JSONObject getChartData() {
		List<CartDTO> items=cartService.cartMoney();
		//리턴할 최종 json 객체
		JSONObject data=new JSONObject();
		//컬럼을 정의할 json 객체
		JSONObject col1=new JSONObject();
		JSONObject col2=new JSONObject();
		JSONArray title=new JSONArray();
		
		col1.put("label", "상품명");
		col1.put("type", "string");
		col2.put("label", "금액");
		col2.put("type", "number");
		// json 배열에 json 객체 추가
		title.add(col1);
		title.add(col2);
		data.put("cols", title);
		//json의 rows 객체구성
		JSONArray body=new JSONArray();
		for(CartDTO dto : items) {
			JSONObject name=new JSONObject();
			name.put("v", dto.getProductName());
			JSONObject money=new JSONObject();
			money.put("v", dto.getMoney());
			JSONArray row=new JSONArray();
			row.add(name);
			row.add(money);
			JSONObject cell=new JSONObject();
			cell.put("c", row);
			body.add(cell);
		}
		data.put("rows", body);
		
		return data;
	}

}
