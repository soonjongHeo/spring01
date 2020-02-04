package com.example.spring01.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service //서비스 빈으로 등록
public class EmailServiceImpl implements EmailService {

	@Inject
	JavaMailSender mailSender; //root-context.xml에 설정한 bean
	
	@Override
	public void sendMail(Map<String, Object> map, HttpServletRequest request) {
		try {
			ArrayList<String> RecipientArr = new ArrayList();
			//이메일 객체
			MimeMessage msg=mailSender.createMimeMessage();
			
			//받는 사람
//			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveMail()));
			Set key = map.keySet();
			for (Iterator iterator = key.iterator(); iterator.hasNext();) {
			   String keyName = (String) iterator.next();
			   String valueName = (String) map.get(keyName);
			   if(keyName.startsWith("receiveMail")) {
				   RecipientArr.add(valueName);
			   }   
			 } 
			InternetAddress[] toAddr = new InternetAddress[RecipientArr.size()];
			for (int i = 0; i < toAddr.length; i++) {
				toAddr[i] = new InternetAddress (RecipientArr.get(i)); 
			}
			//기존의 setRecipient() 메소드에서 setRecipients 로 수정되었습니다. 여러명에게 발송하는거라 s가 붙었네요. 
			msg.setRecipients(RecipientType.TO, toAddr ); //수신자 셋팅
 
			//보내는 사람(이메일주소+이름)
			msg.addFrom(new InternetAddress[] {
					new InternetAddress((String)map.get("senderMail"), (String)map.get("senderName"))
			});
			//이메일 제목
			msg.setSubject((String)map.get("subject"),"utf-8");
			//이메일 본문
			if(null == map.get("attachment")){
//				msg.setText((String)mailData.get("body"));
				msg.setText((String)map.get("message"), "utf-8");
			} else {
				//첨부파일이 있으면
//				BodyPart body = new MimeBodyPart();
//				BodyPart attachment = (BodyPart)map.get("attachment");
//				body.setText((String)mailData.get("body"));
//				msg.setText((String)map.get("message"), "utf-8");
//				MimeMultipart multipart = new MimeMultipart();
//				multipart.addBodyPart(body);
//				multipart.addBodyPart(attachment);
//				msg.setContent(multipart, "text/plain; charset=UTF-8");
				
				DataSource source = new FileDataSource("D:\\upload\\images\\\\"+(String)map.get("attachment"));
				BodyPart body = new MimeBodyPart();
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName((String)map.get("attachment"));
                body.setText((String)map.get("message"));
				MimeMultipart multipart = new MimeMultipart();
				multipart.addBodyPart(body);
				multipart.addBodyPart(messageBodyPart);
				msg.setContent(multipart, "text/plain; charset=UTF-8");
			}
//html로 보낼 경우			
//			MimeMessage message = mailSender.createMimeMessage();
//			MimeMessageHelper helper 
//= new MimeMessageHelper(message, true);
//			helper.setTo("test@host.com");
//
//			helper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);
			//첨부파일이 없으면 내용만 전송
			//이메일 보내기
			mailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
