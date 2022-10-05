package com.atom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.dto.ConvertDto;
import com.atom.mapper.BasicMapper;
import com.atom.mapper.ConvertMapper;
import com.atom.service.ConvertService;

@Service
public class ConvertServiceImpl implements ConvertService{
	
	@Autowired
	private ConvertMapper convertMapper;
	
	@Override
	public List<ConvertDto> selectTbConvert() throws Exception {
		return convertMapper.selectTbConvert();
	}

	@Override
	public Integer insertConvertSales(List<ConvertDto> convertDto) throws Exception {
		int result = 0;
		
		try {
			result = convertMapper.insertConvertSales(convertDto);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Integer insertConvertCompany(List<ConvertDto> convertDto) throws Exception {
		int result = 0;
		
		try {
			result = convertMapper.insertConvertCompany(convertDto);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Integer insertConvertGoods(List<ConvertDto> convertDto) throws Exception {
		int result = 0;
		
		try {
			result = convertMapper.insertConvertGoods(convertDto);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ConvertDto> selectEmptyCompany() throws Exception {
		return convertMapper.selectEmptyCompany();
	}

	@Override
	public List<ConvertDto> selectEmptyGoods() throws Exception {
		return convertMapper.selectEmptyGoods();
	}

	@Override
	public List<ConvertDto> selectEmptyAttri() throws Exception {
		return convertMapper.selectEmptyAttri();
	}

	@Override
	public List<ConvertDto> selectEmptyBuyCom() throws Exception {
		return convertMapper.selectEmptyBuyCom();
	}

	@Override
	public List<ConvertDto> selectEmptyMakeCom() throws Exception {
		return convertMapper.selectEmptyMakeCom();
	}

	@Override
	public List<ConvertDto> selectEmptyComDivi() throws Exception {
		return convertMapper.selectEmptyComDivi();
	}

	@Override
	public List<ConvertDto> selectEmptyDepaGroup() throws Exception {
		return convertMapper.selectEmptyDepaGroup();
	}

	@Override
	public List<ConvertDto> selectEmptyComDealType() throws Exception {
		return convertMapper.selectEmptyComDealType();
	}

	@Override
	public List<ConvertDto> selectEmptyEmployee() throws Exception {
		return convertMapper.selectEmptyEmployee();
	}

	@Override
	public List<ConvertDto> selectEmptyCourse() throws Exception {
		return convertMapper.selectEmptyCourse();
	}

	@Override
	public List<ConvertDto> selectEmptyItemGroupOne() throws Exception {
		return convertMapper.selectEmptyItemGroupOne();
	}

	@Override
	public List<ConvertDto> selectEmptyItemGroupTwo() throws Exception {
		return convertMapper.selectEmptyItemGroupTwo();
	}

	@Override
	public List<ConvertDto> selectEmptyItemGroupThree() throws Exception {
		return convertMapper.selectEmptyItemGroupThree();
	}

	@Override
	public List<ConvertDto> selectEmptyItemGroupFour() throws Exception {
		return convertMapper.selectEmptyItemGroupFour();
	}

	@Override
	public List<ConvertDto> selectEmptyItemGroupFive() throws Exception {
		return convertMapper.selectEmptyItemGroupFive();
	}

	@Override
	public String deleteConvertCompanyTable() throws Exception {
		return convertMapper.deleteConvertCompanyTable();
	}
	
	@Override
	public String deleteConvertTable() throws Exception {
		return convertMapper.deleteConvertTable();
	}
	
	@Override
	public String deleteConvertGoodsTable() throws Exception {
		return convertMapper.deleteConvertGoodsTable();
	}
	
	@Override
	public List<ConvertDto> selectConvertCompany() throws Exception {
		return convertMapper.selectConvertCompany();
	}

	@Override
	public List<ConvertDto> selectConvertGoods() throws Exception {
		return convertMapper.selectConvertGoods();
	}
	
	@Override
	public ConvertDto selectCategoryOne(String categoryOneName) throws Exception {
		return convertMapper.selectCategoryOne(categoryOneName);
	}
	
	@Override
	public ConvertDto selectCategoryTwo(String categoryTwoName) throws Exception {
		return convertMapper.selectCategoryTwo(categoryTwoName);
	}
	
	@Override
	public ConvertDto selectCategoryThree(String categoryThreeName) throws Exception {
		return convertMapper.selectCategoryThree(categoryThreeName);
	}
	
	@Override
	public ConvertDto selectCategoryFour(String categoryFourName) throws Exception {
		return convertMapper.selectCategoryFour(categoryFourName);
	}
	
	@Override
	public ConvertDto selectConvertGoodsDetails(int seq) throws Exception {
		return convertMapper.selectConvertGoodsDetails(seq);
	}
}
