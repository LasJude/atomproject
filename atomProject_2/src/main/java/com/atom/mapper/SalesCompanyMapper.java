package com.atom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.atom.dto.SalesCompanyDto;

@Mapper
public interface SalesCompanyMapper {
	
	List<SalesCompanyDto> selectSalesCompanyList() throws Exception;
	
	SalesCompanyDto selectSalesCompanyDetail(int seq) throws Exception;
	
	List<SalesCompanyDto> selectAutoCompanyName(String searchCompanyName) throws Exception;

}
