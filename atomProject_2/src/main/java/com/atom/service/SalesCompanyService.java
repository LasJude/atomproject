package com.atom.service;

import java.util.List;

import com.atom.dto.SalesCompanyDto;

public interface SalesCompanyService {
	
	List<SalesCompanyDto> selectSalesCompanyList() throws Exception;
	
	SalesCompanyDto selectSalesCompanyDetail(int seq) throws Exception;
	
	List<SalesCompanyDto> selectAutoCompanyName(String searchCompanyName) throws Exception;

}
