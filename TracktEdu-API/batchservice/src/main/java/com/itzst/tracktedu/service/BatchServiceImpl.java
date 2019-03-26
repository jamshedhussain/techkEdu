package com.itzst.tracktedu.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itzst.tracktedu.exception.BatchAllReadyExistException;
import com.itzst.tracktedu.exception.BatchNotCreatedException;
import com.itzst.tracktedu.exception.BatchNotFoundException;
import com.itzst.tracktedu.model.Batch;
import com.itzst.tracktedu.repository.BatchRepository;

@Service
public class BatchServiceImpl implements BatchService{

	@Autowired
	private BatchRepository batchRepository;

	public BatchServiceImpl(BatchRepository batchRepository) {
		this.batchRepository = batchRepository;
	}

	public Batch createBatch(Batch batch) throws BatchAllReadyExistException, BatchNotCreatedException {
		Optional<Batch> optBatch = batchRepository.findBatchBySchoolIdAndBatchCode(batch.getSchoolId(),
				batch.getBatchCode());
		if (optBatch.isPresent()) {
			if (batch.getBatchCode().equalsIgnoreCase(optBatch.get().getBatchCode())) {
				throw new BatchAllReadyExistException("Batch all ready exist.");
			}
		}
		batch.setBatchCreated(LocalDateTime.now());

		return batchRepository.insert(batch);
	}// ------------------------------------------------------------------------EndOfcreateMethod

	public Batch updateBatch(Batch batch, String batchId) throws BatchAllReadyExistException, BatchNotFoundException {
		Optional<Batch> optBatch = batchRepository.findById(batchId);
		if(optBatch.isPresent()) {
			optBatch = batchRepository.findBatchBySchoolIdAndBatchCode(batch.getSchoolId(), batch.getBatchCode());
			if(optBatch.isPresent() && !optBatch.get().getBatchId().equals(batchId)) {
				throw new BatchAllReadyExistException("Batch allready exist.");
			}
			return batchRepository.save(batch);
		}else {
			throw new BatchNotFoundException("Batch not found for update");
		}
	}// -------------------------------------------------------------------------EndOfUpdateMethod
	
	public Boolean deleteBatch(String batchId) throws BatchNotFoundException{
		Optional<Batch> optBatch = batchRepository.findById(batchId);
		if(optBatch.isPresent()) {
			batchRepository.deleteById(batchId);
			return true;
		}else {
			throw new BatchNotFoundException("Batch not found for delete.");
		}
		
	}// -------------------------------------------------------------------------EndOfDeleteMethod
	
	public Batch getBatchByBatchId(String batchId) throws BatchNotFoundException {
		Optional<Batch> optBatch = batchRepository.findById(batchId);
		if(optBatch.isPresent()) {
			return optBatch.get();
		}else {
			throw new BatchNotFoundException("Batch not found.");
		}
	}// --------------------------EndOfgetBatchByIdMethod
	
	public Batch getBatchByCodeForSchool(Long schoolId, String batchCode) throws BatchNotFoundException {
		Optional<Batch> optBatch = batchRepository.findBatchBySchoolIdAndBatchCode(schoolId, batchCode);
		if(optBatch.isPresent()) {
			return optBatch.get();
		}else {
			throw new BatchNotFoundException("Batch not found.");
		}
	}// --------------------------EndOfgetBatchBySchoolCode

	public List<Batch> getBatchesBySchoolId(Long schoolId) throws BatchNotFoundException {
Optional<List<Batch>> optListOfBatches = batchRepository.findBatchesBySchoolId(schoolId);
		
		if(optListOfBatches.isPresent()) {
			return optListOfBatches.get();
		}else {
			throw new BatchNotFoundException("Course not found");
		}
	}// --------------------------EndOfgetBatchBySchoolId

	public List<Batch> getActiveBatchesBySchoolId(Long schoolId) throws BatchNotFoundException {
Optional<List<Batch>> optListOfBatches = batchRepository.findBatchesBySchoolIdAndIsActive(schoolId, true);
		
		if(optListOfBatches.isPresent()) {
			return optListOfBatches.get();
		}else {
			throw new BatchNotFoundException("Course not found");
		}
	}// --------------------------EndOfActiveBacth
}// EndOfClass
