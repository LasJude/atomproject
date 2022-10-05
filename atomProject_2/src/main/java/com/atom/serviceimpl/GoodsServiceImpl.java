package com.atom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.dto.GoodsDto;
import com.atom.mapper.GoodsMapper;
import com.atom.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	GoodsMapper goodsMapper;

	@Override
	public List<GoodsDto> selectAutoSearchGoodsList(GoodsDto goodsDto) throws Exception {
		// TODO Auto-generated method stub
		return goodsMapper.selectAutoSearchGoodsList(goodsDto);
	}

	@Override
	public List<GoodsDto> selectSearchGoodsList(GoodsDto goodsDto) throws Exception {
		// TODO Auto-generated method stub
		return goodsMapper.selectSearchGoodsList(goodsDto);
	}
	
	@Override
	public List<GoodsDto> selectAutoSearchModelList(String searchModelName) throws Exception {
		return goodsMapper.selectAutoSearchModelList(searchModelName);
	}

}
