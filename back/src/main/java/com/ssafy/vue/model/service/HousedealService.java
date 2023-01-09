package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.model.HousedealDto;

public interface HousedealService {

	List<HousedealDto> select(String gugunCode, String dealYear, String dealMonth) throws SQLException;
	List<HousedealDto> selectAll(String dongCode) throws SQLException;

}

