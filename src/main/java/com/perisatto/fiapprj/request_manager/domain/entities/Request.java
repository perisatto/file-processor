package com.perisatto.fiapprj.request_manager.domain.entities;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.perisatto.fiapprj.request_manager.handler.exceptions.ValidationException;

public class Request {

	private String id;
	private String owner;
	private Integer interval;
	private RequestStatus status;
	private String videoFileName;
	private String remarks;
	private String videoUploadUrl;

	static final Logger logger = LogManager.getLogger(Request.class);

	public Request(String owner, Integer interval, String videoFileName) throws ValidationException {

		if((owner == null) || (owner.isEmpty()) || (owner.isBlank())) {
			logger.debug("Error validating request data: null or empty owner");
			throw new ValidationException("rqst-1001", "Error validating request data: null or empty owner");
		}

		if((interval == null) || (interval < 10)) {
			logger.debug("Error validating request data: invalid interval value");
			throw new ValidationException("rqst-1001", "Error validating request data: invalid interval value");
		}

		if((videoFileName == null) || (videoFileName.isEmpty()) || (videoFileName.isBlank())){
			logger.debug("Error validating request data: empty, null or blank videoFileName");
			throw new ValidationException("rqst-1001", "Error validating product data: empty, null or blank videoFileName");			
		}

		this.id = UUID.randomUUID().toString();		
		this.owner = owner;
		this.interval = interval;
		this.videoFileName = videoFileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) throws ValidationException {
		try {
			UUID.fromString(id);
		}catch (Exception IllegalArgumentException) {
			logger.debug("Error validating request data: invalid UUID");
			throw new ValidationException("rqst-1001", "Error validating request data: invalid UUID");
		}
		
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) throws ValidationException {		
		if((owner == null) || (owner.isEmpty()) || (owner.isBlank())) {
			logger.debug("Error validating request data: null or empty owner");
			throw new ValidationException("rqst-1001", "Error validating request data: null or empty owner");
		}		
		
		this.owner = owner;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) throws ValidationException {
		if((interval == null) || (interval < 10)) {
			logger.debug("Error validating request data: invalid interval value");
			throw new ValidationException("rqst-1001", "Error validating request data: invalid interval value");
		}
		
		this.interval = interval;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public String getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String videoFileName) throws ValidationException {
		if((videoFileName == null) || (videoFileName.isEmpty()) || (videoFileName.isBlank())){
			logger.debug("Error validating request data: empty, null or blank videoFileName");
			throw new ValidationException("rqst-1001", "Error validating request data: empty, null or blank videoFileName");			
		}
		
		this.videoFileName = videoFileName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getVideoUploadUrl() {
		return videoUploadUrl;
	}

	public void setVideoUploadUrl(String videoUploadUrl) {
		this.videoUploadUrl = videoUploadUrl;
	}
}
