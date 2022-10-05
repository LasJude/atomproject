package com.atom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atom.dto.BasicDto;
import com.atom.service.BasicService;

@Controller
public class BasicController {
	
	@Autowired
	BasicService basicService;
	
	/**
	 * 기초코드관리 리스트.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/main/basicCodeList.do")
	public String basicList() throws Exception {
		return "basic/basicList";
	}
	
	/**
	 *  분류코드값 가지고오기.
	 *  분류중 1개라도 없음으로 나오면, 분류 검증 텍스트로 대체
	 *  
	 * @param category
	 * @return
	 * @throws Exception
	 */
	public String convertCategory(String category) throws Exception {
		
		BasicDto basicDto = new BasicDto();
		
		String categoryTotal[] = null;
		
		String categoryCodeTotal = "";
		
		String categoryCode1 = "";
		String categoryCode2 = "";
		String categoryCode3 = "";
		String categoryCode4 = "";
		
		category = category.replaceAll(" ", "");
		categoryTotal = category.split(">");
		
		basicDto.setCategoryOneName(categoryTotal[0]);
		basicDto.setCategoryTwoName(categoryTotal[1]);
		basicDto.setCategoryThreeName(categoryTotal[2]);
		basicDto.setCategoryFourName(categoryTotal[3]);
		
		List<BasicDto> categoryOneList = categoryOneList(basicDto);
		categoryCode1 = categoryOneList.get(0).getCategoryOneAdminCode();
		
		List<BasicDto> categoryTwoList = categoryTwoList(basicDto);
		categoryCode2 = categoryTwoList.get(0).getCategoryTwoAdminCode();
		
		List<BasicDto> categoryThreeList = categoryThreeList(basicDto);
		categoryCode3 = categoryThreeList.get(0).getCategoryThreeAdminCode();
		
		List<BasicDto> categoryFourList = categoryFourList(basicDto);
		categoryCode4 = categoryFourList.get(0).getCategoryFourAdminCode();
		
		categoryCodeTotal = categoryCode1+categoryCode2+categoryCode3+categoryCode4;
		
		if(categoryCodeTotal.contains("없음")) {
			categoryCodeTotal = "분류 검증";
		}
		
		return categoryCodeTotal;
	}
	
	/**
	 * 분류 1 검색 리스트.
	 * @param basicDto
	 * @return
	 * @throws Exception
	 */
	public List<BasicDto> categoryOneList(BasicDto basicDto) throws Exception {
		
		List<BasicDto> list = basicService.selectCategoryOneList(basicDto);
		
		if(list.size() == 0 || list == null) {
			basicDto.setCategoryOneAdminCode("없음");
			list.add(0, basicDto);
		}
		
		return list;
	}
	
	/**
	 * 분류 2 검색 리스트.
	 * @param basicDto
	 * @return
	 * @throws Exception
	 */
	public List<BasicDto> categoryTwoList(BasicDto basicDto) throws Exception {
		
		List<BasicDto> list = basicService.selectCategoryTwoList(basicDto);
		
		if(list.size() == 0 || list == null) {
			basicDto.setCategoryTwoAdminCode("없음");
			list.add(0, basicDto);
		}
		
		return list;
	}
	
	/**
	 *  분류2 Ajax 처리
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/category/categoryOne.do", method=RequestMethod.GET)
	@ResponseBody
	public String categoryOneSelect(HttpServletRequest request) throws Exception {
		
		BasicDto basicDto = new BasicDto();
		String oneSelect = (String) request.getParameter("oneSelect");
		String oneHidden = (String) request.getParameter("oneHidden");
		
		//basicDto.setCategoryOneAdminCode(oneSelect);
		basicDto.setCategoryOneAdminCode(oneHidden);
		
		System.out.println("oneHidden >>>> " + oneHidden);
		
		List<BasicDto> list = basicService.selectCategoryTwoList(basicDto);
		
		if(list.size() == 0 || list == null) {
			basicDto.setCategoryTwoAdminCode("없음");
			list.add(0, basicDto);
		}
		
		ArrayList<BasicDto> arrayList = new ArrayList<BasicDto>();
		
		arrayList.addAll(list);
		
		JSONArray jArray = new JSONArray();
		
		for(int i=0; i<arrayList.size(); i++) {
			JSONObject sObject = new JSONObject();
			sObject.put("adminCode", arrayList.get(i).getCategoryTwoAdminCode());
			sObject.put("twoName", arrayList.get(i).getCategoryTwoName());
			jArray.put(sObject);
		}
		
		return jArray.toString();
	}
	
	/**
	 * 분류3 Ajax 처리
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/category/categoryTwo.do", method=RequestMethod.GET)
	@ResponseBody
	public String categoryTwoSelect(HttpServletRequest request) throws Exception {
		
		BasicDto basicDto = new BasicDto();
		
		String oneHidden = (String) request.getParameter("oneHidden");
		String twoHidden = (String) request.getParameter("twoHidden");
		
		basicDto.setCategoryOneAdminCode(oneHidden);
		basicDto.setCategoryTwoAdminCode(twoHidden);
		
		List<BasicDto> list = basicService.selectCategoryThreeList(basicDto);
		
		if(list.size() == 0 || list == null) {
			basicDto.setCategoryThreeAdminCode("없음");
			list.add(0, basicDto);
		}
		
		ArrayList<BasicDto> arrayList = new ArrayList<BasicDto>();
		
		arrayList.addAll(list);
		
		JSONArray jArray = new JSONArray();
		
		for(int i=0; i<arrayList.size(); i++) {
			JSONObject sObject = new JSONObject();
			sObject.put("adminCode", arrayList.get(i).getCategoryThreeAdminCode());
			sObject.put("threeName", arrayList.get(i).getCategoryThreeName());
			jArray.put(sObject);
		}
		
		return jArray.toString();
	}
	
	/**
	 * 분류 4 Ajax 처리.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/category/categoryThree.do", method=RequestMethod.GET)
	@ResponseBody
	public String categoryThreeSelect(HttpServletRequest request) throws Exception {
		
		BasicDto basicDto = new BasicDto();
		
		String oneHidden = (String) request.getParameter("oneHidden");
		String twoHidden = (String) request.getParameter("twoHidden");
		String threeHidden = (String) request.getParameter("threeHidden");
		
		basicDto.setCategoryOneAdminCode(oneHidden);
		basicDto.setCategoryTwoAdminCode(twoHidden);
		basicDto.setCategoryThreeAdminCode(threeHidden);
		
		List<BasicDto> list = basicService.selectCategoryFourList(basicDto);
		
		if(list.size() == 0 || list == null) {
			basicDto.setCategoryFourAdminCode("없음");
			list.add(0, basicDto);
		}
		
		ArrayList<BasicDto> arrayList = new ArrayList<BasicDto>();
		
		arrayList.addAll(list);
		
		JSONArray jArray = new JSONArray();
		
		for(int i=0; i<arrayList.size(); i++) {
			JSONObject sObject = new JSONObject();
			sObject.put("adminCode", arrayList.get(i).getCategoryFourAdminCode());
			sObject.put("fourName", arrayList.get(i).getCategoryFourName());
			jArray.put(sObject);
		}
		
		return jArray.toString();
	}
	
	/**
	 * 분류 3 검색 리스트.
	 * @param basicDto
	 * @return
	 * @throws Exception
	 */
	public List<BasicDto> categoryThreeList(BasicDto basicDto) throws Exception {
		
		List<BasicDto> list = basicService.selectCategoryThreeList(basicDto);
		
		if(list.size() == 0 || list == null) {
			basicDto.setCategoryThreeAdminCode("없음");
			list.add(0, basicDto);
		}
		
		return list;
	}
	
	/**
	 * 분류 4 검색 리스트.
	 * @param basicDto
	 * @return
	 * @throws Exception
	 */
	public List<BasicDto> categoryFourList(BasicDto basicDto) throws Exception {
		
		List<BasicDto> list = basicService.selectCategoryFourList(basicDto);
		
		if(list.size() == 0 || list == null) {
			basicDto.setCategoryFourAdminCode("없음");
			list.add(0, basicDto);
		}
		
		return list;
	}
	
	/**
	 * 품목그룹 1 리스트.
	 * @param basicDto
	 * @return
	 * @throws Exception
	 */
	public List<BasicDto> itemGroupOneList(BasicDto basicDto) throws Exception {
		
		List<BasicDto> list = basicService.selectItemGroupOneList(basicDto);
		
		if(list.size() == 0 || list == null) {
			basicDto.setAdminCode("없음");
			list.add(0, basicDto);
		}
		
		return list;
	}
	
	/**
	 * 품목그룹 2 리스트.
	 * @param basicDto
	 * @return
	 * @throws Exception
	 */
	public List<BasicDto> itemGroupTwoList(BasicDto basicDto) throws Exception {
		
		List<BasicDto> list = basicService.selectItemGroupTwoList(basicDto);
		
		if(list.size() == 0 || list == null) {
			list.get(0).setAdminCode("없음");
		}
		
		return list;
	}
	
	/**
	 * 품목그룹 3 리스트.
	 * @param basicDto
	 * @return
	 * @throws Exception
	 */
	public List<BasicDto> itemGroupThreeList(BasicDto basicDto) throws Exception {
		
		List<BasicDto> list = basicService.selectItemGroupThreeList(basicDto);
		
		if(list.size() == 0 || list == null) {
			list.get(0).setAdminCode("없음");
		}
		
		return list;
	}
	
	/**
	 * 품목그룹 4 리스트.
	 * @param basicDto
	 * @return
	 * @throws Exception
	 */
	public List<BasicDto> itemGroupFourList(BasicDto basicDto) throws Exception {
		
		List<BasicDto> list = basicService.selectItemGroupFourList(basicDto);
		
		if(list.size() == 0 || list == null) {
			list.get(0).setAdminCode("없음");
		}
		
		return list;
	}
	
	/**
	 * 품목그룹 5 리스트.
	 * @param basicDto
	 * @return
	 * @throws Exception
	 */
	public List<BasicDto> itemGroupFiveList(BasicDto basicDto) throws Exception {
		
		List<BasicDto> list = basicService.selectItemGroupFiveList(basicDto);
		
		if(list.size() == 0 || list == null) {
			list.get(0).setAdminCode("없음");
		}
		return list;
	}

	/**
	 * 분류 이동.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/basic/categoryList.do", method = RequestMethod.GET)
	public ModelAndView categoryList(BasicDto basicDto) throws Exception {
		
		ModelAndView mv = new ModelAndView("basic/categoryList");
		
		//List<BasicDto> itemOneList = itemGroupOneList(basicDto);
		List<BasicDto> oneList = categoryOneList(basicDto);
		
		mv.addObject("oneList", oneList);
		
		return mv;
	}
}
