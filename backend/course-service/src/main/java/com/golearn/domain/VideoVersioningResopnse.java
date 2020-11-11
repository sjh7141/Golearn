package com.golearn.domain;

import java.util.Date;

public interface VideoVersioningResopnse {
	Long getVerNo();

	Long getIdxNo();

	Long getVidNo();

	Date getRegDt();
	
	String getVidTitle();
}
