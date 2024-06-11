package com.example.hr.dto.response;

import com.example.ddd.DataTransferObject;
import com.example.hr.domain.Photo;

@DataTransferObject(Photo.class)
public class PhotoResponse {
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
