package com.ssafy.vue.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@Api("어드민 컨트롤러  API V1")
public class AdminUserController {

	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	private MemberService memberService;

	@Autowired
	public AdminUserController(MemberService memberService) {
		this.memberService = memberService;
	}

	@ApiOperation(value = "회원목록", notes = "회원의 <big>전체 목록</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping(value = "/user")
	public ResponseEntity<?> userList() {
		logger.debug("userList call");
		try {
			List<MemberDto> list = memberService.listMember();
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	@ApiOperation(value = "회원등록", notes = "회원의 정보를 받아 처리.")
	@PostMapping(value = "/user")
	public ResponseEntity<?> userRegister(@RequestBody MemberDto memberDto) {
		logger.debug("userRegister memberDto : {}", memberDto);
		try {
			memberService.joinMember(memberDto);
			List<MemberDto> list = memberService.listMember();
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
//			return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	@ApiOperation(value = "회원정보", notes = "회원한명에 대한 정보.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userid", value = "아이디", required = true, dataType = "String", paramType = "path")
//			@ApiImplicitParam(name = "param1", value = "파라미터1", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "param2", value = "파마미터2", required = false, dataType = "int", paramType = "query") 
	})
	@GetMapping(value = "/user/{userid}")
	public ResponseEntity<?> userInfo(@PathVariable("userid") String userId) { 
		logger.debug("userInfo userid : {}", userId);
		try {
			MemberDto memberDto = memberService.getMember(userId);
			if (memberDto != null)
				return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping(value = "/searchUser")
	public ResponseEntity<?> serach(@RequestParam Map<String,String> map) { 
		logger.debug("search param map: {}", map.toString());
		String selectedValue = map.get("selected"), searchValue = map.get("search")+"%";
		logger.debug("search value: {}", searchValue);
		try {
			List<MemberDto> list=null;
			if(selectedValue.equals("아이디")) {
				list = memberService.getMemberByUserid(searchValue);
			}
			else if(selectedValue.equals("사용자명")) {
				list = memberService.getMemberByUsername(searchValue);
			}
			else if(selectedValue.equals("이메일")) {
				list = memberService.getMemberByEmail(searchValue);
			}
			logger.debug("searchUser list size :{}",list!=null? list.size() : 0);
			if (list != null)
				return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "회원정보수정", notes = "회원정보를 수정합니다.")
	@PutMapping(value = "/user")
	public ResponseEntity<?> userModify(@RequestBody MemberDto memberDto) {
		logger.debug("userModify memberDto : {}", memberDto);
		try {
			memberService.updateMember(memberDto);
//			List<MemberDto> list = memberService.listMember();
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
	}

	@ApiOperation(value = "회원정보삭제", notes = "회원정보를 삭제합니다.")
	@DeleteMapping(value = "/user/{userid}")
	public ResponseEntity<?> userDelete(@PathVariable("userid") String userId) {
		logger.debug("userDelete userid : {}", userId);
		try {
			memberService.deleteMember(userId);
			List<MemberDto> list = memberService.listMember();
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
