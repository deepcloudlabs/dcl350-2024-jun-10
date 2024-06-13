package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmReactiveService {
	private final CustomerRepository customerRepository;
	
	public CrmReactiveService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Mono<CustomerDocument> findCustomerByEmail(String email) {
		return customerRepository.findById(email);
	}

	public Flux<CustomerDocument> findAllByPage(int pageNo, int pageSize) {
		return customerRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	public Mono<CustomerDocument> acquireCustomer(CustomerDocument customer) {
		return customerRepository.insert(customer);
	}

	public Mono<CustomerDocument> updateCustomer(String email, CustomerDocument customer) {
		return customerRepository.save(customer);
	}

	public Mono<CustomerDocument> releaseCustomer(String email) {
		return customerRepository.findById(email)
		                  .doOnSuccess( customer -> {
		                	  customerRepository.delete(customer);
		                  });
	}

}
