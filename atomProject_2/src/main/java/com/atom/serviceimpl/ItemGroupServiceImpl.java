package com.atom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.dto.ItemGroupDto;
import com.atom.mapper.BasicMapper;
import com.atom.service.ItemGroupService;

@Service
public class ItemGroupServiceImpl implements ItemGroupService {
	
	@Autowired
	private BasicMapper itemGroupMapper;

	/**
	@Override
	public List<ItemGroupDto> selectItemGroupOneList() throws Exception {
		return itemGroupMapper.selectItemGroupOneList();
	}
	*/
	
	/**
	 * [품목그룹 1] 엑셀로 insert or update를 진행한다.
	 * seq값이 있다면 update,
	 * seq값이 없다면 insert를 진행한다.
	 */
	@Override
	public Integer insertUpdateItemGroupOne(List<ItemGroupDto> itemGroupDto) throws Exception {
		
		int result = 0;
		
		try {
			result = itemGroupMapper.insertUpdateItemGroupOne(itemGroupDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ItemGroupDto selectItemGroupOneDetail(String seq) throws Exception {
		
		ItemGroupDto itemGroupDto = new ItemGroupDto();
		
		try {
			itemGroupDto = itemGroupMapper.selectItemGroupOneDetail(seq);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return itemGroupDto;
	}
	
	@Override
	public Integer updateItemGroupOne(ItemGroupDto itemGroupDto) throws Exception {
		int result = 0;
		
		try {
			itemGroupMapper.updateItemGroupOne(itemGroupDto);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
