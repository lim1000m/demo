package com.ese.domain; 

public class JsonResponse {

	private String status = null;
	private String message = null;
	private String objNm = null;
	private String code = null;
	private String fieldNm = null;
			
	public String getFieldNm() {
		return fieldNm;
	}

	public void setFieldNm(String fieldNm) {
		this.fieldNm = fieldNm;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getObjNm() {
		return objNm;
	}

	public void setObjNm(String objNm) {
		this.objNm = objNm;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
            return status;
    }
    
    public void setStatus(String status) {
            this.status = status;
    }
}