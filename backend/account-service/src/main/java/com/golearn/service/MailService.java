package com.golearn.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.golearn.mapper.MailMapper;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MailMapper mailMapper; 
	
	public int sendMail(String username, int userNo) {		
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(username);
		simpleMessage.setSubject("Go Learn Go Run 인증 메일입니다.");
		String code = makeCode();
		simpleMessage.setText("안녕하세요. Go Learn Go Run 입니다.\n"
							+ "회원님의 인증코드는 다음과 같습니다.\n\n"
							+ "인증코드 : " +code);
		javaMailSender.send(simpleMessage);
		
		return mailMapper.saveMessage(userNo, code);
	}
	
	public int checkMessage(int userNo, String message) {
		return mailMapper.checkMessage(userNo, message);
	}
	
	public void updateCheckMail(int userNo) {
		mailMapper.updateCheckMail(userNo);
	}
	
	private String makeCode() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
		do {
			num = ran.nextInt(75) + 48;
			if((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)){
				sb.append((char) num);
			}else {
				continue;
			}
		}while(sb.length()< 6);
		
		return sb.toString();
	}

}
