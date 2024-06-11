package com.example.hr.dto.response;

import com.example.ddd.DataTransferObject;
import com.example.ddd.TransferType;
import com.example.hr.domain.Photo;

@DataTransferObject(value=Photo.class,type = TransferType.RESPONSE)
public class PhotoResponse {
	private String data;

	public PhotoResponse(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
