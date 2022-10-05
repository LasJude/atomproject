package com.atom.service;

import java.util.List;

import com.atom.dto.GoodsDto;

public interface GoodsService {
	
	List<GoodsDto> selectAutoSearchGoodsList(GoodsDto goodsDto) throws Exception;
	
	List<GoodsDto> selectSearchGoodsList(GoodsDto goodsDto) throws Exception;
	
	List<GoodsDto> selectAutoSearchModelList(String searchModelName) throws Exception;

}
