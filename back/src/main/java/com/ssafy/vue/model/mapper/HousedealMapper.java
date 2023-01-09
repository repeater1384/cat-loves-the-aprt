package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.HousedealDto;


@Mapper
public interface HousedealMapper {

	List<HousedealDto> select(Map params) throws SQLException;

	List<HousedealDto> selectAll(String gugunCode) throws SQLException;

}
