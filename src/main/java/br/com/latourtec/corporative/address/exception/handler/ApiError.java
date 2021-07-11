package br.com.latourtec.corporative.address.exception.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

public class ApiError implements Serializable {
	
	@JsonIgnore
	private HttpStatus httpStatus;
	private Integer status;
	private Date timestamp;
	private String message;
	private String cause;
	private String path;
	
	public ApiError(HttpStatus httpStatus, String message, String path) {
		this.httpStatus = httpStatus;
		this.status = httpStatus.value();
		this.cause = httpStatus.getReasonPhrase();
		this.message = message;
		this.timestamp = new Date();
		this.path = path;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getCause() {
		return cause;
	}
	
	public String getPath() {
		return path;
	}
}
