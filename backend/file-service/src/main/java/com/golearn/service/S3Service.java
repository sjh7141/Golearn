package com.golearn.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

@Service
public class S3Service {
	
	private static final Logger log = LoggerFactory.getLogger(S3Service.class);
	
	@Autowired
	private AmazonS3 s3;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public String fileUpload(MultipartFile file, String place) {
	    int pos = file.getOriginalFilename().lastIndexOf(".");
        String format = file.getOriginalFilename().substring(pos);
		String fileName = UUID.randomUUID() + format;
		
		String key = place + "/" + fileName;
		
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentType(file.getContentType());
		omd.setContentLength(file.getSize());
		omd.setHeader("fileName", fileName);
		
		try {
			s3.putObject(new PutObjectRequest(bucket, key, file.getInputStream(), omd)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			return s3.getUrl(bucket, key).toString();
		} catch(Exception e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
	}
}
