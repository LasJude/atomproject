package com.atom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.atom.dto.GoodsDto;

@Mapper
public interface GoodsMapper {
	
	List<GoodsDto> selectAutoSearchGoodsList(GoodsDto goodsDto) throws Exception;
	
	List<GoodsDto> selectSearchGoodsList(GoodsDto goodsDto) throws Exception;
	
	List<GoodsDto> selectAutoSearchModelList(String searchModelName) throws Exception;

}
