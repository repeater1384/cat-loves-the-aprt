package com.ssafy.vue.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.HousedealDto;
import com.ssafy.vue.model.service.HousedealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/housedeal")
@Api("하우스딜 컨트롤러  API V1")
public class HousedealController {

	private final Logger logger = LoggerFactory.getLogger(HousedealController.class);

	@Autowired
	HousedealService housedealService;

	@GetMapping("/{LAWD_CD}")
	@ApiOperation(value = "전체거래정보", notes = "해당 동 코드의  <big>전체 전체거래정보</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "전체거래정보 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	public ResponseEntity<?> selectAll(@PathVariable("LAWD_CD") String dongCode) throws SQLException {
		logger.info("!!!!!selectAll() 실행 , Param : {}", dongCode);
		List<HousedealDto> list = housedealService.selectAll(dongCode);
		logger.info("list 크기 : {}", list.size());
		return new ResponseEntity<List<HousedealDto>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "기간별 전체 거래정보", notes = "해당 동 코드의  <big>기간별 전체 전체거래정보</big>을 반환해 줍니다.")
	@GetMapping(value = "/{GUGUN_CODE}/{DEAL_YMD}", produces = "application/json;charset=utf-8")
	public ResponseEntity<List<HousedealDto>> select(@PathVariable("GUGUN_CODE") String gugunCode,
			@PathVariable("DEAL_YMD") String dealYmd) throws IOException, SQLException {
		String dealYear = dealYmd.substring(0, 4);
		String dealMonth = dealYmd.substring(4, 6);
		logger.info("!!!!!select() 실행 , Param : {}, {}, {}", gugunCode, dealYear, dealMonth);
		List<HousedealDto> list = housedealService.select(gugunCode, dealYear, dealMonth);
		logger.info("list 크기 : {}", list.size());
		return new ResponseEntity<List<HousedealDto>>(list, HttpStatus.OK);
	}
}

//	@GetMapping
//	@ApiResponses({ @ApiResponse(code = 200, message = "전체거래정보 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
//			@ApiResponse(code = 500, message = "서버에러!!") })
//	public ResponseEntity<?> select(@RequestParam("LAWD_CD") String dongCode, @RequestParam("DEAL_YMD") String dealYMD)
//			throws SQLException {