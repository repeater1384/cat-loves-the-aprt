package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.HousedealDto;
import com.ssafy.vue.model.mapper.HousedealMapper;


@Service
public class HousedealServiceImpl implements HousedealService {
	@Autowired
	HousedealMapper housedealMapper;
	public List<HousedealDto> select(String gugunCode, String dealYear, String dealMonth) throws SQLException {
		Map<String, String> params = new HashMap<>();
		params.put("gugunCode", gugunCode);
		params.put("dealYear", dealYear);
		params.put("dealMonth", dealMonth);
		return housedealMapper.select(params);
	}

	public List<HousedealDto> selectAll(String dongCode) throws SQLException {
		return housedealMapper.selectAll(dongCode);
	}
	
}
