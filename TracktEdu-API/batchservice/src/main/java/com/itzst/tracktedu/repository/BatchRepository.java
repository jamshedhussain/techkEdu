package com.itzst.tracktedu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.itzst.tracktedu.model.Batch;

public interface BatchRepository extends MongoRepository<Batch, String> {
	public Optional<Batch> findBatchBySchoolIdAndBatchCode(Long schoolId, String batchCode);

	public Optional<List<Batch>> findBatchesBySchoolId(Long schoolId);

	public Optional<List<Batch>> findBatchesBySchoolIdAndIsActive(Long schoolId, Boolean isActive);

}// EndOfClass
