package com.itzst.tracktedu.service;

import java.util.List;

import com.itzst.tracktedu.exception.BatchAllReadyExistException;
import com.itzst.tracktedu.exception.BatchNotCreatedException;
import com.itzst.tracktedu.exception.BatchNotFoundException;
import com.itzst.tracktedu.model.Batch;

public interface BatchService {
public Batch createBatch(Batch batch) throws BatchAllReadyExistException, BatchNotCreatedException;
	
	public Batch updateBatch(Batch batch, String batchId) throws BatchAllReadyExistException, BatchNotFoundException;
	
	public Boolean deleteBatch(String batchId) throws BatchNotFoundException;
	
	public Batch getBatchByBatchId(String batchId) throws BatchNotFoundException;
	
	public Batch getBatchByCodeForSchool(Long schoolId, String batchCode) throws BatchNotFoundException;
	
	public List<Batch> getBatchesBySchoolId(Long schoolId) throws BatchNotFoundException;
	
	public List<Batch> getActiveBatchesBySchoolId(Long schoolId) throws BatchNotFoundException;
}
