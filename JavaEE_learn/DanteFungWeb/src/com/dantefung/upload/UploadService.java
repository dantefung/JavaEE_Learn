package com.dantefung.upload;

import java.util.List;

public class UploadService {
	
	UploadDao dao = new UploadDao();
	
	public boolean saveInfo(List<UploadBean> list)
	{
		return dao.saveInfo(list);
	}
}
