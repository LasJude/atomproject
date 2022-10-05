package com.atom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.dto.SalesCompanyDto;
import com.atom.mapper.SalesCompanyMapper;
import com.atom.service.SalesCompanyService;

@Service
public class SalesCompanyServiceImpl implements SalesCompanyService{
	
	@Autowired
	private SalesCompanyMapper salesCompanyMapper;

	@Override
	public List<SalesCompanyDto> selectSalesCompanyList() throws Exception {
		return salesCompanyMapper.selectSalesCompanyList();
	}

	@Override
	public SalesCompanyDto selectSalesCompanyDetail(int seq) throws Exception {
		return salesCompanyMapper.selectSalesCompanyDetail(seq);
	}

	@Override
	public List<SalesCompanyDto> selectAutoCompanyName(String searchCompanyName) throws Exception {
		return salesCompanyMapper.selectAutoCompanyName(searchCompanyName);
	}

}
