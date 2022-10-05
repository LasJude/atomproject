package com.atom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.dto.BasicDto;
import com.atom.mapper.BasicMapper;
import com.atom.service.BasicService;

@Service
public class BasicServiceImpl implements BasicService {
	
	@Autowired
	BasicMapper basicMapper;

	@Override
	public List<BasicDto> selectCategoryOneList(BasicDto basicDto) throws Exception {
		return basicMapper.selectCategoryOneList(basicDto);
	}
	
	@Override
	public List<BasicDto> selectCategoryTwoList(BasicDto basicDto) throws Exception {
		return basicMapper.selectCategoryTwoList(basicDto);
	}
	
	@Override
	public List<BasicDto> selectCategoryThreeList(BasicDto basicDto) throws Exception {
		return basicMapper.selectCategoryThreeList(basicDto);
	}
	
	@Override
	public List<BasicDto> selectCategoryFourList(BasicDto basicDto) throws Exception {
		return basicMapper.selectCategoryFourList(basicDto);
	}
	
	@Override
	public List<BasicDto> selectItemGroupOneList(BasicDto basicDto) throws Exception {
		return basicMapper.selectItemGroupOneList(basicDto);
	}
	
	@Override
	public List<BasicDto> selectItemGroupTwoList(BasicDto basicDto) throws Exception {
		return basicMapper.selectItemGroupTwoList(basicDto);
	}
	
	@Override
	public List<BasicDto> selectItemGroupThreeList(BasicDto basicDto) throws Exception {
		return basicMapper.selectItemGroupThreeList(basicDto);
	}
	
	@Override
	public List<BasicDto> selectItemGroupFourList(BasicDto basicDto) throws Exception {
		return basicMapper.selectItemGroupFourList(basicDto);
	}

	@Override
	public List<BasicDto> selectItemGroupFiveList(BasicDto basicDto) throws Exception {
		return basicMapper.selectItemGroupFiveList(basicDto);
	}
}
