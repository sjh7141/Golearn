# DB 생성
CREATE DATABASE IF NOT EXISTS golearn;



# DB 사용
USE golearn;



# 사용자 member 테이블 생성
CREATE TABLE `gl_member` (
  `mbr_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '회원 일련 번호',
  `mbr_id` varchar(50) DEFAULT NULL COMMENT '아이디',
  `mbr_email` varchar(100) DEFAULT NULL COMMENT '이메일',
  `mbr_pwd` varchar(100) DEFAULT NULL COMMENT '패스워드',
  `mbr_nickname` varchar(50) NOT NULL COMMENT '닉네임',
  `mbr_profile` varchar(200) DEFAULT 'profile_default.png' COMMENT '프로필 사진 url',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일',
  `chg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '변경 일',
  `ck_email` tinyint(1) NOT NULL DEFAULT '0' COMMENT '이메일 인증 여부\\n0: 미 인증, 1: 인증',
  `ck_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '회원 활성화 여부\n0: 비 활성화, 1: 활성화',
  `mbr_banner` varchar(200) NOT NULL DEFAULT 'profile_banner_default.png',
  PRIMARY KEY (`mbr_no`),
  UNIQUE KEY `mbr_id_UNIQUE` (`mbr_id`),
  UNIQUE KEY `mbr_email_UNIQUE` (`mbr_email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

# 회원 테이블 데이터 삽입
INSERT INTO `gl_member` VALUES (1,'test','test@test.com','$2a$10$eQ2TB.Q7pV/l3X9cKrPr6O5H34J59knlQK6KtmGeDVjTPS0k8ZR6.','test','default_profile.png','2020-10-26 11:16:22','2020-10-26 11:16:22',0,1);



# 권한 gl_authority 테이블 생성
CREATE TABLE `gl_authority` (
  `auth_no` int(11) NOT NULL AUTO_INCREMENT,
  `mbr_role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`auth_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

# 권한 데이터 삽입
INSERT INTO `gl_authority` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');



# 사용자 권한 gl_member_authority 테이블 생성
CREATE TABLE `gl_member_authority` (
  `mbr_no` int(11) NOT NULL,
  `auth_no` int(11) NOT NULL,
  KEY `authority_no_fk_idx` (`auth_no`),
  KEY `authority_member_no_fk_idx` (`mbr_no`),
  CONSTRAINT `authority_member_no_fk` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `authority_no_fk` FOREIGN KEY (`auth_no`) REFERENCES `gl_authority` (`auth_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 권한 입력
INSERT INTO `gl_member_authority` VALUES (1,1);



# 영상 gl_video 테이블 생성
CREATE TABLE `gl_video` (
  `vid_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '영상 일련 번호',
  `mbr_no` int(11) NOT NULL COMMENT '영상 생성자',
  `vid_pno` int(11) NOT NULL COMMENT '영상 부모 출처',
  `vid_title` varchar(100) DEFAULT NULL COMMENT '제목',
  `vid_content` varchar(100) DEFAULT NULL COMMENT '내용, 설명',
  `vid_url` varchar(200) DEFAULT NULL COMMENT '영상 url',
  `vid_view` int(11) NOT NULL DEFAULT '0' COMMENT '영상 조회수',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '영상 생성일',
  `vid_hide` tinyint(1) NOT NULL DEFAULT '0' COMMENT '영상 숨기기\\n0:안숨김 1:숨김',
  `vid_thumbnail` varchar(200) NOT NULL DEFAULT 'video_default_thumbnail.png' COMMENT '영상 썸네일',
  `vid_length` int(11) NOT NULL DEFAULT '0' COMMENT '영상의 종 길이',
  PRIMARY KEY (`vid_no`),
  KEY `gl_fk_vm_no_idx` (`mbr_no`),
  CONSTRAINT `gl_fk_vm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 영상 좋아요 gl_video_like 테이블 생성
CREATE TABLE `gl_video_like` (
  `vid_no` int(11) NOT NULL COMMENT '영상 일련 번호',
  `mbr_no` int(11) NOT NULL COMMENT '회원 일련 번호',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일',
  KEY `gl_fk_vlv_no_idx` (`vid_no`),
  KEY `gl_fk_vlm_no_idx` (`mbr_no`),
  CONSTRAINT `gl_fk_vlm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_vlv_no` FOREIGN KEY (`vid_no`) REFERENCES `gl_video` (`vid_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 영상 댓글 gl_video_comment 테이블 생성
CREATE TABLE `gl_video_comment` (
  `vid_cmt_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '영상 댓글 일련 번호',
  `vid_no` int(11) NOT NULL COMMENT '영상 일련 번호',
  `mbr_no` int(11) NOT NULL COMMENT '회원 일련 번호',
  `vid_cmt_pno` int(11) NOT NULL COMMENT '상위 댓글 일련 번호',
  `vid_comment` text NOT NULL COMMENT '댓글',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일',
  `chg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 일',
  PRIMARY KEY (`vid_cmt_no`),
  KEY `gl_fk_vcv_no_idx` (`vid_no`),
  KEY `gl_fk_vcm_no_idx` (`mbr_no`),
  CONSTRAINT `gl_fk_vcm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_vcv_no` FOREIGN KEY (`vid_no`) REFERENCES `gl_video` (`vid_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




# 영상 기록 gl_video_record 테이블 생성
CREATE TABLE `gl_video_record` (
  `mbr_no` int(11) NOT NULL COMMENT '회원 일련 번호',
  `vid_no` int(11) NOT NULL COMMENT '영상 일련 번호',
  `vid_rec_time` timestamp NULL DEFAULT NULL COMMENT '시청 시간',
  KEY `gl_fk_vrm_no_idx` (`mbr_no`),
  KEY `gl_fk_vrv_no_idx` (`vid_no`),
  CONSTRAINT `gl_fk_vrm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_vrv_no` FOREIGN KEY (`vid_no`) REFERENCES `gl_video` (`vid_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




# 게시판 gl_board 테이블 생성
CREATE TABLE `gl_board` (
  `brd_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '게시글 일련번호',
  `mbr_no` int(11) NOT NULL COMMENT '작성자',
  `brd_title` varchar(100) NOT NULL COMMENT '제목',
  `brd_content` text NOT NULL COMMENT '내용',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일',
  `chg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 일',
  `brd_type` tinyint(4) NOT NULL COMMENT '게시판 타입',
  `brd_view` int(11) NOT NULL DEFAULT '0' COMMENT '조회수',
  PRIMARY KEY (`brd_no`),
  KEY `gl_fk_bm_no_idx` (`mbr_no`),
  CONSTRAINT `gl_fk_bm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 게시판 댓글 gl_board_comment 테이블 생성
CREATE TABLE `gl_board_comment` (
  `brd_cmt_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '게시판 댓글 일련번호',
  `brd_no` int(11) NOT NULL COMMENT '게시글 일련번호',
  `brd_cmt_pno` int(11) NOT NULL COMMENT '상위 댓글 일련번호',
  `mbr_no` int(11) NOT NULL COMMENT '회원 일련번호',
  `brd_comment` text NOT NULL COMMENT '댓글',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `chg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (`brd_cmt_no`),
  KEY `gl_fk_bcb_no_idx` (`brd_no`),
  KEY `gl_fk_bcm_no_idx` (`mbr_no`),
  CONSTRAINT `gl_fk_bcb_no` FOREIGN KEY (`brd_no`) REFERENCES `gl_board` (`brd_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_bcm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 코스 gl_course 테이블 생성
CREATE TABLE `gl_course` (
  `cos_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '코스 일련번호',
  `mbr_no` int(11) NOT NULL COMMENT '코스 생성자',
  `cos_title` varchar(100) DEFAULT NULL COMMENT '제목',
  `cos_content` text COMMENT '내용, 설명',
  `cos_thumbnail` varchar(200) NOT NULL DEFAULT 'course_default_thumbnail.png' COMMENT '썸네일',
  `cos_banner` varchar(200) NOT NULL DEFAULT 'course_banner_default.png',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `chg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '변경일',
  PRIMARY KEY (`cos_no`),
  KEY `gl_fk_cm_no_idx` (`mbr_no`),
  CONSTRAINT `gl_fk_cm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;



# 코스 좋아요 gl_course_like 테이블 생성
CREATE TABLE `gl_course_like` (
  `mbr_no` int(11) NOT NULL COMMENT '회원 일련번호',
  `cos_no` int(11) NOT NULL COMMENT '코스 일련번호',
  PRIMARY KEY (`mbr_no`,`cos_no`),
  KEY `gl_fk_clm_no_idx` (`mbr_no`),
  KEY `gl_fk_clc_no_idx` (`cos_no`),
  CONSTRAINT `gl_fk_clc_no` FOREIGN KEY (`cos_no`) REFERENCES `gl_course` (`cos_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_clm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 코스 관리자 gl_course_manager 테이블 생성
CREATE TABLE `gl_course_manager` (
  `mbr_no` int(11) NOT NULL COMMENT '회원 일련번호',
  `cos_no` int(11) NOT NULL COMMENT '코스 일련번호',
  PRIMARY KEY (`mbr_no`,`cos_no`),
  KEY `gl_fk_cmm_no_idx` (`mbr_no`),
  KEY `gl_fk_cmc_no_idx` (`cos_no`),
  CONSTRAINT `gl_fk_cmc_no` FOREIGN KEY (`cos_no`) REFERENCES `gl_course` (`cos_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_cmm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 로드맵 코스 gl_loadmap_course 테이블 생성
CREATE TABLE `gl_loadmap_course` (
  `cos_no` int(11) NOT NULL,
  `ldm_no` int(11) NOT NULL,
  `ldm_order` int(11) DEFAULT NULL,
  KEY `gl_fk_lcc_no_idx` (`cos_no`),
  KEY `gl_fk_lcl_idx` (`ldm_no`),
  CONSTRAINT `gl_fk_lcc_no` FOREIGN KEY (`cos_no`) REFERENCES `gl_course` (`cos_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_lcl_no` FOREIGN KEY (`ldm_no`) REFERENCES `gl_loadmap` (`ldm_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 로드맵 gl_loadmap 테이블 생성
CREATE TABLE `gl_loadmap` (
  `ldm_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '로드맵 일련번호',
  `mbr_no` int(11) NOT NULL COMMENT '회원 일련번호',
  `ldm_title` varchar(100) NOT NULL COMMENT '제목',
  `ldm_content` text NOT NULL COMMENT '내용, 설명',
  `ldm_thumbnail` varchar(200) NOT NULL DEFAULT 'loadmap_default_thumbnail.png' COMMENT '썸네일',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `chg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '변경일',
  PRIMARY KEY (`ldm_no`),
  KEY `gl_fk_lm_no_idx` (`mbr_no`),
  CONSTRAINT `gl_fk_lm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 코스 태그 gl_course_tag 테이블 생성
CREATE TABLE `gl_course_tag` (
  `cos_no` int(11) NOT NULL COMMENT '코스 일련번호',
  `tag_no` int(11) NOT NULL COMMENT '태그 일련번호',
  KEY `gl_fk_ctc_no_idx` (`cos_no`),
  KEY `gl_fk_ctt_no_idx` (`tag_no`),
  CONSTRAINT `gl_fk_ctc_no` FOREIGN KEY (`cos_no`) REFERENCES `gl_course` (`cos_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_ctt_no` FOREIGN KEY (`tag_no`) REFERENCES `gl_tag` (`tag_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 태그 gl_tag 테이블 생성
CREATE TABLE `gl_tag` (
  `tag_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '태그 일련번호',
  `tag_name` varchar(30) NOT NULL COMMENT '태그 이름',
  PRIMARY KEY (`tag_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 영상 태그 gl_video_tag 테이블 생성
CREATE TABLE `gl_video_tag` (
  `vid_no` int(11) NOT NULL COMMENT '영상 일련번호',
  `tag_no` int(11) NOT NULL COMMENT '태그 일련번호',
  KEY `tag_no_idx` (`tag_no`),
  KEY `gl_fk_vtv_no_idx` (`vid_no`),
  CONSTRAINT `gl_fk_vtt_no` FOREIGN KEY (`tag_no`) REFERENCES `gl_tag` (`tag_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_vtv_no` FOREIGN KEY (`vid_no`) REFERENCES `gl_video` (`vid_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 목차 gl_index 테이블 생성
CREATE TABLE `gl_index` (
  `idx_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '목차 일련번호',
  `cos_no` int(11) NOT NULL COMMENT '코스 일련번호',
  `vid_no` int(11) NOT NULL COMMENT '영상 일련번호',
  `idx_title` varchar(100) NOT NULL COMMENT '목차 제목',
  `idx_order` int(11) DEFAULT NULL COMMENT '순서',
  PRIMARY KEY (`idx_no`),
  KEY `gl_fk_ic_no_idx` (`cos_no`),
  KEY `gl_fk_iv_no_idx` (`vid_no`),
  CONSTRAINT `gl_fk_ic_no` FOREIGN KEY (`cos_no`) REFERENCES `gl_course` (`cos_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_iv_no` FOREIGN KEY (`vid_no`) REFERENCES `gl_video` (`vid_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 수강자 목록 gl_course_viewer 테이블 생성
CREATE TABLE `gl_course_viewer` (
  `mbr_no` int(11) NOT NULL COMMENT '회원 일련번호',
  `cos_no` int(11) NOT NULL COMMENT '코스 일련번호',
  `idx_no` int(11) NOT NULL COMMENT '목차 일련번호',
  PRIMARY KEY (`mbr_no`,`cos_no`,`idx_no`),
  KEY `gl_cvm_no_idx` (`mbr_no`),
  KEY `gl_fk_cvc_no_idx` (`cos_no`),
  KEY `gl_fk_cvi_no_idx` (`idx_no`),
  CONSTRAINT `gl_fk_cvc_no` FOREIGN KEY (`cos_no`) REFERENCES `gl_course` (`cos_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_cvi_no` FOREIGN KEY (`idx_no`) REFERENCES `gl_index` (`idx_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_cvm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 영상 버저닝 gl_video_version 테이블 생성
CREATE TABLE `gl_video_version` (
  `ver_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '버전 일련번호',
  `idx_no` int(11) NOT NULL COMMENT '목차 일련번호',
  `vid_no` int(11) NOT NULL COMMENT '영상 일련번호',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`ver_no`),
  KEY `gl_fk_vvi_no_idx` (`idx_no`),
  KEY `gl_fk_vvv_no_idx` (`vid_no`),
  CONSTRAINT `gl_fk_vvi_no` FOREIGN KEY (`idx_no`) REFERENCES `gl_index` (`idx_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_vvv_no` FOREIGN KEY (`vid_no`) REFERENCES `gl_video` (`vid_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 구독 gl_subscribe 테이블 생성
CREATE TABLE `gl_member_subscribe` (
  `mbr_no` int(11) NOT NULL COMMENT '구독을 누른 사람 일련번호',
  `mbr_is_subscribed` int(11) NOT NULL COMMENT '구독을 당하는 사람 일련번호',
  KEY `gl_fk_msm1_no_idx` (`mbr_no`),
  KEY `gl_fk_msm2_no_idx` (`mbr_is_subscribed`),
  CONSTRAINT `gl_fk_msm1_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_msm2_no` FOREIGN KEY (`mbr_is_subscribed`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 알림 gl_notification 테이블 생성
CREATE TABLE `gl_notification` (
  `noti_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '알림 일련번호',
  `mbr_no` int(11) NOT NULL COMMENT '알림 받는 사람',
  `noti_type` tinyint(4) NOT NULL COMMENT '알림 타입',
  `noti_path` varchar(100) DEFAULT NULL COMMENT '리다이렉트 경로',
  `noti_msg` text NOT NULL COMMENT '메시지',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `noti_read` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 : 안읽음 , 1 : 읽음',
  PRIMARY KEY (`noti_no`),
  KEY `gl_fk_nm_no_idx` (`mbr_no`),
  CONSTRAINT `gl_fk_nm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='알림';



# 영상 보관함 gl_save_video 테이블 생성
CREATE TABLE `gl_save_video` (
  `mbr_no` int(11) NOT NULL COMMENT '회원 일련번호',
  `vid_no` int(11) DEFAULT NULL COMMENT '영상 일련번호',
  KEY `gl_fk_svm_no_idx` (`mbr_no`),
  KEY `gl_fk_svv_no_idx` (`vid_no`),
  CONSTRAINT `gl_fk_svm_no` FOREIGN KEY (`mbr_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_svv_no` FOREIGN KEY (`vid_no`) REFERENCES `gl_video` (`vid_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='영상 보관함';



# 영상 요청 목록 gl_video_request 테이블 생성
CREATE TABLE `gl_video_request` (
  `vid_req_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '영상 요청 목록 일련번호',
  `vid_no` int(11) NOT NULL COMMENT '요청 영상 링크',
  `mbr_req_no` int(11) NOT NULL COMMENT '요청자',
  `cos_no` int(11) NOT NULL COMMENT '코스 일련변호',
  `idx_no` int(11) NOT NULL COMMENT '목차 일련번호',
  `vid_req_comment` text COMMENT '커밋 메시지',
  `vid_req_accept_yn` tinyint(4) NOT NULL DEFAULT '0' COMMENT '영상 요청 승인 여부\n0: 처리중, 1: 승인, 2: 반려',
  `mbr_admin_no` int(11) DEFAULT NULL COMMENT '관리자',
  `vid_res_comment` text,
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `chg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '변경일',
  PRIMARY KEY (`vid_req_no`),
  KEY `gl_fk_vrv_no_idx` (`vid_no`),
  KEY `gl_fk_vrm_req_no_idx` (`mbr_req_no`),
  KEY `gl_fk_vrc_no_idx` (`cos_no`),
  KEY `gl_fk_vri_no_idx` (`idx_no`),
  KEY `gl_fk_vrm_admin_no_idx` (`mbr_admin_no`),
  CONSTRAINT `gl_fk_vqc_no` FOREIGN KEY (`cos_no`) REFERENCES `gl_course` (`cos_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_vqi_no` FOREIGN KEY (`idx_no`) REFERENCES `gl_index` (`idx_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_vqm_admin_no` FOREIGN KEY (`mbr_admin_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_vqm_req_no` FOREIGN KEY (`mbr_req_no`) REFERENCES `gl_member` (`mbr_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gl_fk_vqv_no` FOREIGN KEY (`vid_no`) REFERENCES `gl_video` (`vid_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;