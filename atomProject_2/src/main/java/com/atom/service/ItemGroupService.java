package com.atom.service;

import java.util.List;

import com.atom.dto.BasicDto;
import com.atom.dto.ItemGroupDto;

public interface ItemGroupService {
	
//	List<ItemGroupDto> selectItemGroupOneList() throws Exception;
	
	Integer insertUpdateItemGroupOne(List<ItemGroupDto> itemGroupDto) throws Exception;
	
	ItemGroupDto selectItemGroupOneDetail(String seq) throws Exception;
	
	Integer updateItemGroupOne(ItemGroupDto itemGroupDto) throws Exception;
}
