package com.atom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.atom.dto.BasicDto;
import com.atom.dto.ItemGroupDto;

@Mapper
public interface BasicMapper {
	
	List<ItemGroupDto> selectItemGroupOneList() throws Exception;
	
	/**
	 * [품목그룹 1] 엑셀 insert or update 진행
	 * @param itemGroupDto
	 * @return
	 */
	Integer insertUpdateItemGroupOne(List<ItemGroupDto> itemGroupDto);
	
	ItemGroupDto selectItemGroupOneDetail(String seq);
	
	Integer updateItemGroupOne(ItemGroupDto itemGroupDto);
	
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
