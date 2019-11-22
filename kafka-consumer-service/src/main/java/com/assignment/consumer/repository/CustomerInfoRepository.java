package com.assignment.consumer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.consumer.domain.CustomerInfo;

@Repository
public interface CustomerInfoRepository extends CrudRepository<CustomerInfo, Integer> {

}
