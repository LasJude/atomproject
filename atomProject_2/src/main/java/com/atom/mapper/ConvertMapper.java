package com.atom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.atom.dto.ConvertDto;

@Mapper
public interface ConvertMapper {
	
	Integer insertConvertSales(List<ConvertDto> converDto) throws Exception;
	
	Integer insertConvertCompany(List<ConvertDto> convertDto) throws Exception;
	
	Integer insertConvertGoods(List<ConvertDto> convertDto) throws Exception;
	
	List<ConvertDto> selectTbConvert() throws Exception;
	
	List<ConvertDto> selectEmptyCompany() throws Exception;
	
	List<ConvertDto> selectEmptyGoods() throws Exception;
	
	List<ConvertDto> selectEmptyAttri() throws Exception;
	
	List<ConvertDto> selectEmptyBuyCom() throws Exception;
	
	List<ConvertDto> selectEmptyMakeCom() throws Exception;
	
	List<ConvertDto> selectEmptyComDivi() throws Exception;
	
	List<ConvertDto> selectEmptyDepaGroup() throws Exception;
	
	List<ConvertDto> selectEmptyComDealType() throws Exception;
	
	List<ConvertDto> selectEmptyEmployee() throws Exception;
	
	List<ConvertDto> selectEmptyCourse() throws Exception;
	
	List<ConvertDto> selectEmptyItemGroupOne() throws Exception;
	
	List<ConvertDto> selectEmptyItemGroupTwo() throws Exception;
	
	List<ConvertDto> selectEmptyItemGroupThree() throws Exception;
	
	List<ConvertDto> selectEmptyItemGroupFour() throws Exception;
	
	List<ConvertDto> selectEmptyItemGroupFive() throws Exception;
	
	String deleteConvertCompanyTable() throws Exception;
	
	String deleteConvertTable() throws Exception;
	
	String deleteConvertGoodsTable() throws Exception;
	
	List<ConvertDto> selectConvertCompany() throws Exception;
	
	List<ConvertDto> selectConvertGoods() throws Exception;
	
	ConvertDto selectCategoryOne(String categoryOneName) throws Exception;
	
	ConvertDto selectCategoryTwo(String categoryTwoName) throws Exception;
	
	ConvertDto selectCategoryThree(String categoryThreeName) throws Exception;
	
	ConvertDto selectCategoryFour(String categoryFourName) throws Exception;
	
	ConvertDto selectConvertGoodsDetails(int seq) throws Exception;

	
	
}
