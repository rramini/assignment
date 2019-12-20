package com.assignment.consumer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.consumer.domain.AuditInfo;

@Repository
public interface AuditInfoRepository extends CrudRepository<AuditInfo, Integer> {

}
