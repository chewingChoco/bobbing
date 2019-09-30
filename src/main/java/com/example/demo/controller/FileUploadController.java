package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.review.ReviewService;
import com.example.demo.service.review.image.ReviewImageRepository;
import com.example.demo.service.review.image.ReviewImageService;
import com.example.demo.utils.MediaUtils;
import com.example.demo.utils.UploadFileUtils;

@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	ReviewService reviewService;

	@Autowired
	ReviewImageRepository reviewImgRepo;

	@Autowired
	ReviewImageService reviewImageService;	
	
	@Autowired
	ServletContext context;

	String uploadPath;

	@PostConstruct
	public void initController() {
		this.uploadPath = context.getRealPath("/");
	}

	// 업로드 흐름 : 업로드 버튼클릭-> 임시 디렉토리에 업로드 -> 지정된 디렉토리에 저장-> 파일정보가 file에 저장
	// -----------------------------------일반적인 방식의 업로드
	// 처리------------------------------------------------
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public String uploadForm() {
		// get매핑 포워드

		return "th/review/testImgUpload";
	}

	@RequestMapping(value = "uploadForm", method = RequestMethod.POST)
	public ModelAndView uploadForm(MultipartFile file, ModelAndView mav) throws Exception {
		// 파일의 원본이름 저장
		String saveName = file.getOriginalFilename();
		// 파일의 정보 로그 출력
		logger.info("파일이름 : " + file.getOriginalFilename());
		logger.info("파일크기: " + file.getSize());
		logger.info("컨텐츠 타입: " + file.getContentType());
		// 랜덤 생성 + 파일이름 저장하기 위해 파일명 랜덤생성 메서드 호출
		saveName = uploadFile(saveName, file.getBytes());
		mav.setViewName("uploadResult"); // 업로드 결과화면
		mav.addObject(saveName, saveName);

		return mav;
	}

	// 파일 명 랜덤생성 메서드
	private String uploadFile(String originalFileName, byte[] fileData) throws Exception { // uuid 생성 (universal unique
																							// identifier, 범용 고유 식별자)

//		MultipartHttpServletRequest request = null;
		UUID uuid = UUID.randomUUID();

		// 랜덤 생성 + 파일 이름 저장
		String saveName = uuid.toString() + "_" + originalFileName;
		File target = new File(uploadPath, saveName);
		// 임시 디렉토리에 저장된 업로드 된 파일을 다시 지정된 디렉토리에 복사
		// FileCopyUtils.copy(바이트 배열, 파일 객체)
		FileCopyUtils.copy(fileData, target);
		return saveName;
	}
	// -----------------------------------일반적인 방식의 업로드
	// 처리--------------------------------------

	// -----------------------------------ajax방식의 업로드
	// 처리----------------------------------------

	// ajax업로드 페이지 매핑
	/*
	 * @RequestMapping(value = "uploadAjax", method = RequestMethod.GET) public void
	 * uploadAjax() { //uploadAjax 화면으로 포워딩 }
	 */

	// ajax업로드 페이지 매핑
	// 파일의 한글 처리 produces = "text/plain;charset=UTF-8"
	@ResponseBody
	@RequestMapping(value = "/modifyUploadAjax/{reviewId}", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> modifyUploadAjax(MultipartFile file, @PathVariable Integer reviewId) throws Exception {
		logger.info("파일 이름 : " + file.getOriginalFilename().trim());
		logger.info("파일 크기 : " + file.getSize());
		logger.info("컨텐츠 타입 : " + file.getContentType());
		System.out.println(uploadPath);

		ResponseEntity<String> fileName = new ResponseEntity<String>(
				UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);

		int idx1 = fileName.toString().indexOf(",");
		int idx2 = fileName.toString().lastIndexOf(",");

		fileName.toString().substring(idx1 + 1, idx2).replace("s_", "");
		System.err.println(fileName.toString());
		System.out.println("파일 들어왔다 : 서버 : " + file.toString());
		return fileName;

	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		logger.info("파일 이름 : " + file.getOriginalFilename().trim());
		logger.info("파일 크기 : " + file.getSize());
		logger.info("컨텐츠 타입 : " + file.getContentType());
		System.out.println(uploadPath);
		

		ResponseEntity<String> fileName = new ResponseEntity<String>(
				UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);

		int idx1 = fileName.toString().indexOf(",");
		int idx2 = fileName.toString().lastIndexOf(",");

		fileName.toString().substring(idx1 + 1, idx2).replace("s_", "");
		System.err.println(fileName.toString());
		System.out.println("파일 들어왔다 : 서버 : " + file.toString());
		return fileName;

	}
	
	@ResponseBody // view가 아닌 data리턴
	@RequestMapping(value = "displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		// 서버의 파일을 다운로드 하기 위한 스트림
		InputStream in = null; // java.io
		ResponseEntity<byte[]> entity = null;

		try {
			// 확장자를 추출하여 formName에 저장
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			// 추출한 확장자를 MediaUtils클래스에서 이미지 파일여부를 검사하고 리ㅓㄴ밭아 mtype에 저장
			MediaType mType = MediaUtils.getMediaType(formatName);
			// 헤더 구성 객체 (외부에서 데이터를 주고밭을 때에는 header와 body를 구성해야하기 때문에)
			HttpHeaders headers = new HttpHeaders();
			// InputStream생성
			in = new FileInputStream(uploadPath + fileName);
			// 이미지 파일이면
			if (mType != null) {
				headers.setContentType(mType);
				// 이미지가 아니면
			} else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				// 다운로드용 컨텐츠 타입
				headers.setContentType(mType);
				//
				// 바이트 배열을 스트링으로 : new String(fileName.getBytes("UTF-8),"iso-8859")*iso-8859-1
				// 서유럽언어, 큰 따옴표 내부에 "\"내용\" "
				// 파일의 한글 깨짐 방지
				headers.add("Content-Disposition",
						"attachment; fileName=\"" + new String(fileName.getBytes("UTF-8"), "iso-8859-1") + "\"");
				// headers.add("Content-Disposition", "attachment; fileName='"+fileName"'");

			}
			// 바이트 배열, 헤더, HTTP상태코드
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}

		return entity;
	}

	// 파일 삭제 매핑
	@ResponseBody
	@RequestMapping(value = "deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		System.out.println(fileName);
		int index = fileName.lastIndexOf("s_");
		fileName.substring(index + 2);
		System.err.println(fileName);


		// 파일의 확장자 추출
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 이미지 파일 여부 검사
		MediaType mType = MediaUtils.getMediaType(formatName);
		// 이미지 의 경우(썸네일 + 원본파일 삭제 ), 이미지가 이니면 원본파일만 삭제
		// 이미지 파일이면
		if (mType != null) {
			// 썸네일 이미지 파일 추출
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			// 썸네일 이미지 삭제
			new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
		}
		// 원본 파일 삭제
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();

		// 데이터와 http상태 코드 전송
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	// -----------------------------------ajax방식의 업로드
	// 처리----------------------------------
	
	@ResponseBody
	@RequestMapping(value = "uploadedDeleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> uploadedDeleteFile(String fileName) {
		System.out.println(fileName);
		
		String reviewViewImgName = fileName;
		reviewImageService.deleteModifyReviewImg(reviewViewImgName);
		
		// 파일의 확장자 추출
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 이미지 파일 여부 검사
		MediaType mType = MediaUtils.getMediaType(formatName);
		// 이미지 의 경우(썸네일 + 원본파일 삭제 ), 이미지가 이니면 원본파일만 삭제
		// 이미지 파일이면
		if (mType != null) {
		// 썸네일 이미지 파일 추출
		String front = fileName.substring(0, 12);
		String end = fileName.substring(14);
		// 썸네일 이미지 삭제
		new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
		}
		// 원본 파일 삭제
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();

		// 데이터와 http상태 코드 전송
		return new ResponseEntity<String>("modifyDeleted", HttpStatus.OK);
	}
}
