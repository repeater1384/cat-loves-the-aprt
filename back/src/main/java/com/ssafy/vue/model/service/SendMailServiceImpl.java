package com.ssafy.vue.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailServiceImpl implements SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail() {

		ArrayList<String> toUserList = new ArrayList<>();

		toUserList.add("repeater1384@naver.com");
		toUserList.add("rlatjdtjq627@naver.com");

		int toUserSize = toUserList.size();

		SimpleMailMessage simpleMessage = new SimpleMailMessage();

		simpleMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));

		simpleMessage.setSubject("Subject Sample");

		simpleMessage.setText("Text Sample");

		javaMailSender.send(simpleMessage);
	}

	public void sendInitializedPwd(String email, String initializedPwd) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();

		simpleMessage.setTo(email);

		simpleMessage.setSubject("[SSAFY 1학기 관통] 비밀번호 초기화 안내 메일");

		simpleMessage.setText(String.format("초기화된 비밀번호는 : < %s >입니다.", initializedPwd));
		
		simpleMessage.setFrom("Admin");
		javaMailSender.send(simpleMessage);
	}

}