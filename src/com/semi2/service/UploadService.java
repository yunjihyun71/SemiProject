package com.semi2.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.semi2.dto.DTO;

public class UploadService {
	
	String photoPath = "upload/"; //사진 저장 위치
	HttpServletRequest request = null;
	String savePath= null;
	
	public UploadService(HttpServletRequest request) {
		this.request = request;
		String root = request.getSession().getServletContext().getRealPath("/");
		savePath = root+"upload";
		System.out.println(savePath);
	}

	//파일 등록 메서드
	public DTO regist() {
		DTO dto = new DTO();
		File dir = new File(savePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, 1024*1024*10, "UTF-8");
		
			dto.setBbs_writer(multi.getParameter("bbs_writer"));
			dto.setBbs_title(multi.getParameter("bbs_title"));
			dto.setBbs_content(multi.getParameter("bbs_content"));
			dto.setSubject_id(Integer.parseInt(multi.getParameter("subject_id")));
			dto.setBbssort_type(multi.getParameter("bbssort_type"));
			
			
			//파일명 추출
			String oriFileName = multi.getFilesystemName("uploadfile");
			if(oriFileName != null) {
				//확장자 추출
				String ext = oriFileName.substring(oriFileName.indexOf("."));
				
				//새파일명 만들기(새파일명 + 확장자)
				String newFileName = dto.getBbs_writer() +"/"+dto.getBbssort_type()+"/"+System.currentTimeMillis()+ext;
				System.out.println(newFileName);
				
				//파일명 변경
				//파일을 객체화하여 사용할 때에는 File 변수명 = new File(파일 경로)
				File oldFile = new File(savePath+"/"+oriFileName);
				File newFile = new File(savePath+"/"+newFileName);
				oldFile.renameTo(newFile);
				
				photoPath += newFileName; //사진 다운 경로
				//변경된 파일명 DTO에 추가
				dto.setUpload_name(newFileName);
			}
			String bbs_id = multi.getParameter("idx");
			if(bbs_id != null) {
				dto.setBbs_id(Integer.parseInt(bbs_id));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}

	//파일 이름 삭제
	public void del(String oldFileName) {
		System.out.println("삭제주소 : "+savePath);
		File file = new File(savePath+"/"+oldFileName);
		if(file.exists()) {
			System.out.println("파일 삭제에 성공했는가? : "+file.delete());
		}
	}

}
