package com.ssafy.vue.model.service;

public interface SendMailService {
	public void sendMail();
	public void sendInitializedPwd(String email, String initializedPwd);
}
