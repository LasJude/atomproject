package com.atom.service;

import java.util.List;

import com.atom.dto.BasicDto;

public interface BasicService {
	
	List<BasicDto> selectCategoryOneList(BasicDto basicDto) throws Exception;
	
	List<BasicDto> selectCategoryTwoList(BasicDto basicDto) throws Exception;
	
	List<BasicDto> selectCategoryThreeList(BasicDto basicDto) throws Exception;
	
	List<BasicDto> selectCategoryFourList(BasicDto basicDto) throws Exception;
	
	List<BasicDto> selectItemGroupOneList(BasicDto basicDto) throws Exception;
	
	List<BasicDto> selectItemGroupTwoList(BasicDto basicDto) throws Exception;
	
	List<BasicDto> selectItemGroupThreeList(BasicDto basicDto) throws Exception;
	
	List<BasicDto> selectItemGroupFourList(BasicDto basicDto) throws Exception;
	
	List<BasicDto> selectItemGroupFiveList(BasicDto basicDto) throws Exception;

}
