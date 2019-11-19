package com.claimplat.adminapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claimplat.adminapi.repository.ParameterConfigRepository;

@Service
@Transactional
public class ParameterConfigService extends BaseService{

	@Autowired
	private ParameterConfigRepository parameterConfigRepository;
}
