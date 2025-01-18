package com.perisatto.fiapprj.request_manager.application.usecases;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.perisatto.fiapprj.request_manager.application.interfaces.FileProcessingManagement;
import com.perisatto.fiapprj.request_manager.application.interfaces.RequestRepository;
import com.perisatto.fiapprj.request_manager.domain.entities.Request;
import com.perisatto.fiapprj.request_manager.domain.entities.RequestStatus;
import com.perisatto.fiapprj.request_manager.handler.exceptions.NotFoundException;

public class UpdateRequestUseCase {
	
	static final Logger logger = LogManager.getLogger(UpdateRequestUseCase.class);
	
	private final RequestRepository requestRepository;	
	private final FileProcessingManagement fileProcessingManagement;
	
	public UpdateRequestUseCase(RequestRepository requestRepository,FileProcessingManagement fileProcessingManagement) {
		this.requestRepository = requestRepository;
		this.fileProcessingManagement = fileProcessingManagement;
	}
	
	public Request updateRequest(String id, String remarks, RequestStatus status) throws Exception {
		
		Optional<Request> request = requestRepository.getRequestById(id);
		
		if(request.isEmpty()) {
			throw new NotFoundException("rqst-1001", "Customer not found");
		}
		
		Request updatedRequest = request.get();
		
		if(remarks != null) {
			updatedRequest.setRemarks(remarks);
		}
		
		if(status == RequestStatus.PENDING_PROCESS) {
			fileProcessingManagement.createProcessingRequest(updatedRequest);
		}
				
		updatedRequest.setStatus(status);
		
		requestRepository.updateRequest(updatedRequest);
		
		return updatedRequest;
	}
}
