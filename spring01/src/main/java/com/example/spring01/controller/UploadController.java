package com.example.spring01.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.common.common.CommandMap;
import com.example.spring01.service.BoardService;

@Controller
public class UploadController {
	private static final Logger logger = 
			LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	@RequestMapping(value="upload/uploadForm", method=RequestMethod.GET)
    public void uploadForm() throws Exception{
		logger.info("업로드 페이지");
		// upload 포워딩
    }
	
	@RequestMapping(value="upload/uploadForm", method=RequestMethod.POST)
	public ModelAndView uploadForm(MultipartFile file, ModelAndView mav) throws Exception{
		logger.info("업로드 등록");
		logger.info("파일이름: " + file.getOriginalFilename());
		String savedName = file.getOriginalFilename();
		logger.info("파일크기: " + file.getSize());
		logger.info("컨텐트타입: " + file.getContentType());
		savedName = uploadFile(savedName, file.getBytes());
		mav.setViewName("upload/uploadResult");
		mav.addObject("savedName", savedName);
		return mav;
	}
	
	// 파일이름이 중복되지 않도록 처리
	private String uploadFile(String originalName, byte[] fileData) throws IOException {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
	
	@ResponseBody
	@RequestMapping("imageUpload.do")
	public void imageUpload(HttpServletRequest request,
			HttpServletResponse response, 
			MultipartFile upload) throws Exception{
		logger.info("이미지 업로드");
		// http header 설정
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// http body
		// 업로드한 파일 이름
		String fileName = upload.getOriginalFilename();
		// 바이트 배열로 변환
		byte[] bytes = upload.getBytes();
		// 이미지를 업로드할 디렉토리
		String uploadPath = "D:\\upload\\images\\";
		OutputStream out = new FileOutputStream(new File(uploadPath+fileName)); //java.io
		// 서버에 저장됨
		out.write(bytes);
		out.flush();
		
		String callback = request.getParameter("CKEditorFuncNum");
		PrintWriter printWriter = response.getWriter();
		String fileUrl = "D:/upload/images/" + fileName;
		System.out.println("fileUrl: " + fileUrl);
		
		ArrayList<String> fileUploadAllowExtension = new ArrayList();
		fileUploadAllowExtension.add("jpg");
		fileUploadAllowExtension.add("png");
		fileUploadAllowExtension.add("jpeg");
		String fileExtension = fileUrl.substring(fileUrl.lastIndexOf(".") + 1).toLowerCase();
//		String contextPath = request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf( "/" )); 
//		String webPath = contextPath + "/images/" + fileName;
		String webPath = request.getContextPath() + "/images/" + fileName;
		 for (int i = 0; i < fileUploadAllowExtension.size(); i++) {
			 if (fileUploadAllowExtension.contains(fileExtension)) {
				 System.out.println("fileUploadAllowExtension(i): " + fileUploadAllowExtension.get(i));
				 printWriter.println(
						 "<script>\nwindow.parent.CKEDITOR.tools.callFunction("
								 + callback + ",'" + webPath + "','이미지가 업로드되었습니다.')"
								 + "\n</script>");
				 break;
			 } else {
				 printWriter.println("<script type='text/javascript'>\nalert('허용되지 않은 파일 유형입니다.');\n</script>");
				 break;
			 }
		}
		  
		// 스트림 닫기
		printWriter.flush();
	}
}
