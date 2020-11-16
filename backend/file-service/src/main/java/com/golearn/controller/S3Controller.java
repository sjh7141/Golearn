package com.golearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.golearn.service.S3Service;

@CrossOrigin("*")
@RestController
public class S3Controller {

	@Autowired
	private S3Service s3Service;
	
	@PostMapping("/upload")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @RequestParam("target") String place){
		String res = s3Service.fileUpload(file, place);
		return ResponseEntity.ok(res);
	}
	
	@GetMapping
	public ResponseEntity<String> test(){
		return ResponseEntity.ok("test");
	}
}
