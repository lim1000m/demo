package com.ese.domain;

import java.io.Serializable;

public class GeneralMessage implements Serializable {
	private static final long serialVersionUID = 2657355430074131014L;
	
	private byte[] headerTyp = {};
    private byte[] header = {};
    private byte[] body = {};

    public GeneralMessage() {
    }

	public byte[] getHeader() {
		return header;
	}

	public void setHeader(byte[] header) {
		this.header = header;
	}
	
	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public byte[] getHeaderTyp() {
		return headerTyp;
	}

	public void setHeaderTyp(byte[] headerTyp) {
		this.headerTyp = headerTyp;
	}



}