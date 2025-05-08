package com.marondal.marondalgram.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	public static final String FILE_UPLOAD_PATH = "/home/ec2-user/upload/marondalgram"; 
	
	public static String saveFile(int userId, MultipartFile file) {
		// 파일 이름 유지 
		// 폴더를 생성해서 파일을 저장 
		// 사용자 정보를 폴더 이름으로 사용 한다 
		// 시간 정보를 포함 
		// UNIX TIME : 1970년 1월 1일 0시 0분 0 초 부터 흐른 시간을 milli second(1/1000) 단위로 표현한 값
		// ex ) 5_312497120
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		// 디렉토리 (폴더) 만들기 
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		try {
			byte[] bytes = file.getBytes();
			
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			// 파일 저장 실패 
			return null;
		}
		
		// 실제 파일 저장 위치와 url 경로를 매칭하는 규칙 
		// E:\\dulumaryT\\web\\20241114\\project\\upload\\marondalgram
		// /images/
		
		// E:\\dulumaryT\\web\\20241114\\project\\upload\\marondalgram/5_132900923/test.png
		// /images/5_132900923/test.png
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();
	}
	
	// 파일 삭제 기능
	public static boolean removeFile(String imagePath) {  // /images/6_1742553879532/goose-9453257_640.jpg
		
		if(imagePath == null) {
			return false;
		}
		 String fullFilePath = FILE_UPLOAD_PATH + imagePath.replace("/images", "");
		 
		 Path path = Paths.get(fullFilePath);
		// E:\\dulumaryT\\web\\20241114\\project\\upload\\memo/6_1742553879532
		 Path directoryPath = path.getParent();
		 try {
			Files.delete(path);
			Files.delete(directoryPath);
		} catch (IOException e) {
			return false;
		}
		
		 return true;
	}

}
