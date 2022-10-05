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

import com.atom.dto.GoodsDto;
import com.atom.service.GoodsService;

@Controller
public class GoodsController {
	
	@Autowired
	GoodsService goodsService;
	
	/**
	 *  상품관리 페이지 이동.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goods/goodsListMove.do")
	public String goodsListMove() throws Exception {
		return "goods/goodsList";
	}
	
	/**
	 * 자동완성.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goods/goodsAutoSearch.do", method = RequestMethod.GET)
	@ResponseBody
	public String autoSearchGoodsList(HttpServletRequest request) throws Exception {

		String searchGoodsName = (String) request.getParameter("searchGoodsName");
		String searchModelName = (String) request.getParameter("searchModelName");
		
		GoodsDto goodsDto = new GoodsDto();
		
		List<GoodsDto> list = new ArrayList<GoodsDto>();
		
		if(searchGoodsName != null) {
			goodsDto.setSearchGoodsName(searchGoodsName);
			list = goodsService.selectAutoSearchGoodsList(goodsDto);
		} else if(searchModelName != null) {
			goodsDto.setSearchModelName(searchModelName);
			list = goodsService.selectAutoSearchGoodsList(goodsDto);
		}
		
		ArrayList<GoodsDto> arrayList = new ArrayList<GoodsDto>();
		
		arrayList.addAll(list);
		
		JSONArray jArray = new JSONArray();
		
			for(int i=0; i<arrayList.size(); i++) {
				JSONObject sObject = new JSONObject();
				sObject.put("seq", arrayList.get(i).getSeq());
				sObject.put("adminSeq", arrayList.get(i).getAdminSeq());
				sObject.put("goodsName1", arrayList.get(i).getGoodsName1());
				sObject.put("standard", arrayList.get(i).getStandard());
				sObject.put("modelName", arrayList.get(i).getModelName());
				jArray.put(sObject);
			}
			
		return jArray.toString();
	}
	
	/**
	 *  검색 리스트.
	 * @param goodsDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goods/searchGoodsList.do", method=RequestMethod.POST)
	public ModelAndView searchGoodsList(GoodsDto goodsDto) throws Exception {
		
		goodsDto.setAdminSeq(goodsDto.getSearchAdminSeq());
		System.out.println(" >>> " + goodsDto.getSearchGoodsName());
		
		ModelAndView mv = new ModelAndView("goods/goodsList");
		
		List<GoodsDto> list = goodsService.selectSearchGoodsList(goodsDto);
		
		mv.addObject("goodsList", list);
		mv.addObject("goodsDto", goodsDto);
		
		return mv;
	}

}
