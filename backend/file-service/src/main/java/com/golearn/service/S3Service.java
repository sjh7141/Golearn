package com.golearn.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;

@Service
public class S3Service {
	
	@Autowired
	private AmazonS3 s3;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public String fileUpload(MultipartFile file, String place) {
		String fileName = UUID.randomUUID() + file.getOriginalFilename();
		String key = place + "/" + fileName;
		
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentType(file.getContentType());
		omd.setContentLength(file.getSize());
		omd.setHeader("fileName", file.getOriginalFilename());
		
		try {
			s3.putObject(new PutObjectRequest(bucket, key, file.getInputStream(), omd)
					.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return s3.getUrl(bucket, key).toString();
	}
}
