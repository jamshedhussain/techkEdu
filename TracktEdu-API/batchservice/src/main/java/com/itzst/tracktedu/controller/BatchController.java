package com.itzst.tracktedu.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itzst.tracktedu.exception.BatchAllReadyExistException;
import com.itzst.tracktedu.exception.BatchNotCreatedException;
import com.itzst.tracktedu.exception.BatchNotFoundException;
import com.itzst.tracktedu.model.Batch;
import com.itzst.tracktedu.service.BatchService;


@RestController
@RequestMapping("/api/v1/")
public class BatchController {

	@Autowired
	private BatchService batchService;
	
	public BatchController(BatchService batchService) {
		this.batchService = batchService;
	}
	
	@PostMapping("batch")
	public ResponseEntity<?> createBatch(@RequestBody Batch batch){
		
		try {
			Batch b = batchService.createBatch(batch);
			if(b != null) {
				return new ResponseEntity<>(b, HttpStatus.CREATED);
			}
		} catch (BatchAllReadyExistException |BatchNotCreatedException e) {
			return new ResponseEntity<>("Unable to create batch.\n "+e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("Unable to create batch. ", HttpStatus.INTERNAL_SERVER_ERROR);
	}//EndOfPostMapping
	
	@PutMapping("batch/{id}")
	public ResponseEntity<?> updateBatch(@RequestBody Batch batch, @PathVariable("id") String batchId){
		
		if(!batchId.equals(batch.getBatchId())) {
			return new ResponseEntity<>("Unable to update batch. ", HttpStatus.BAD_REQUEST);
		}
		
		try {
			Batch b = batchService.updateBatch(batch, batchId);
			if(b != null) {
				return new ResponseEntity<>(b, HttpStatus.OK);
			}
		} catch (BatchNotFoundException | BatchAllReadyExistException e) {
			return new ResponseEntity<>("Unable to update batch.\n "+e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("Unable to update batch. ", HttpStatus.INTERNAL_SERVER_ERROR);
	}//EndOfPutMapping
	
	@DeleteMapping("batch/{id}")
	public ResponseEntity<?> deleteBatch(@PathVariable("id") String batchId){
		
		try {
			Boolean flag = batchService.deleteBatch(batchId);
			if(flag) {
				return new ResponseEntity<>("Batch deleted successfully.", HttpStatus.OK);
			}
		} catch (BatchNotFoundException e) {
			return new ResponseEntity<>("Unable to delete batch.\n "+e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Unable to delete batch.", HttpStatus.INTERNAL_SERVER_ERROR);
	}//EndOfDeleteMapping
	
	@GetMapping("batch/{id}")
	public ResponseEntity<?> getBatchById(@PathVariable("id") String batchId){
		try {
			Batch batch = batchService.getBatchByBatchId(batchId);
			return new ResponseEntity<>(batch, HttpStatus.OK);
		} catch (BatchNotFoundException e) {
			return new ResponseEntity<>("Unable to get course.\n"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}//EndOFGetBatchById
	
	@GetMapping("batches/all/{id}")
	public ResponseEntity<?> getBatchesBySchoolId(@PathVariable("id") Long schoolId){
		
		try {
			List<Batch> listOfBatches = batchService.getBatchesBySchoolId(schoolId);
			return new ResponseEntity<>(listOfBatches, HttpStatus.OK);
		} catch (BatchNotFoundException e) {
			return new ResponseEntity<>("Unable to get batch.\n"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}//endOfBatchesBySchoolId
	
	@GetMapping("test")
	public ResponseEntity<?> getTestData(){
		Batch b = new Batch();
		b.setBatchCode("ABC");
		b.setBatchName("ABC Baatch");
		b.setBatchDescription("ABC batch description");
		b.setSchoolId(1001L);
		b.setBatchCreated(LocalDateTime.now());
		b.setIsActive(true);
		
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
}//ENdOFController
