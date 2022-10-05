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

import com.atom.dto.SalesCompanyDto;
import com.atom.service.SalesCompanyService;

@Controller
public class SalesCompanyController {
	
	@Autowired
	private SalesCompanyService salesCompanyService;
	
	/**
	 *  매출처 리스트.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/company/salesCompany.do", method=RequestMethod.GET)
	public ModelAndView salesCompanyList() throws Exception  {
		
		ModelAndView mv = new ModelAndView("company/salesCompanyList");
		
		List<SalesCompanyDto> selectSalesCompanyList = salesCompanyService.selectSalesCompanyList();
		
		mv.addObject("companyList", selectSalesCompanyList);
		
		return mv;
	}
	
	/**
	 * 매출처 상세페이지.
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/company/salesCompanyDetail.do", method=RequestMethod.GET)
	public ModelAndView salesCompanyDetail(int seq) throws Exception {
		ModelAndView mv = new ModelAndView("company/salesCompanyDetail");
		
		try {

			
			SalesCompanyDto companyDto = new SalesCompanyDto();
			
			companyDto = salesCompanyService.selectSalesCompanyDetail(seq);
			
			mv.addObject("companyDto",companyDto);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 자동완성.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/company/companyAutoSearch.do", method = RequestMethod.GET)
	@ResponseBody
	public String accountList(HttpServletRequest request) throws Exception {

		String searchCompanyName = (String) request.getParameter("searchCompanyName");
		
		List<SalesCompanyDto> list = salesCompanyService.selectAutoCompanyName(searchCompanyName);
		ArrayList<SalesCompanyDto> arrayList = new ArrayList<SalesCompanyDto>();
		
		arrayList.addAll(list);
		
//		JSONObject obj = new JSONObject();
		
		JSONArray jArray = new JSONArray();
		
			for(int i=0; i<arrayList.size(); i++) {
				JSONObject sObject = new JSONObject();
				sObject.put("seq", arrayList.get(i).getSeq());
				sObject.put("adminCode", arrayList.get(i).getAdminCode());
				sObject.put("name", arrayList.get(i).getName1());
				jArray.put(sObject);
			}
			
//		obj.add("accountSearchList", jArray);
//		System.out.println(" >>> " + obj.toString());
		
		return jArray.toString();
	}
}
