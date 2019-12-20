package com.assignment.consumer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.consumer.domain.ErrorInfo;

@Repository
public interface ErrorInfoRepository extends CrudRepository<ErrorInfo, Integer> {

}
