package com.atom.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.atom.dto.BasicDto;
import com.atom.dto.ItemGroupDto;
import com.atom.service.BasicService;
import com.atom.service.ItemGroupService;

@Controller
public class ItemGroupController {
	
	@Autowired
	private ItemGroupService itemGroupService;
	
	@Autowired
	private BasicService basicService;
	
	private String fileLocation;
	
	/**
	 * 품목그룹 1 리스트 확인.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/basic/itemGroupOneList.do")
	public ModelAndView itemGroupOneList() throws Exception {
		
		ModelAndView mv = new ModelAndView("itemGroup/itemGroupOne");
		
		BasicDto basicDto = new BasicDto();
		
		List<BasicDto> list = basicService.selectItemGroupOneList(basicDto);
		
		mv.addObject("list", list);
		
		return mv;
	}
	
	/**
	 * 품목그룹 1 리스트를 엑셀로 다운로드 한다.
	 * 
	 * @param response
	 * @throws Exception
	 */
	/*
	@RequestMapping(value="/itemGroupOne/itemGroupOneDownload.do")
	public void itemGroupOneDownload(HttpServletResponse response) throws Exception {
		
		BasicDto basicDto = new BasicDto();
		
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("품목그룹1");
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("코드");
		cell = row.createCell(1);
		cell.setCellValue("관리번호");
		cell = row.createCell(2);
		cell.setCellValue("코드명");
		cell = row.createCell(3);
		cell.setCellValue("비고");
		
		List<ItemGroupDto> list = itemGroupService.selectItemGroupOneList();
		
		for(int i=0;i<list.size();i++) {
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue(list.get(i).getSeq());
			cell = row.createCell(1);
			cell.setCellValue(list.get(i).getAdminCode());
			cell = row.createCell(2);
			cell.setCellValue(list.get(i).getCodeName());
			cell = row.createCell(3);
			cell.setCellValue(list.get(i).getEtc());
		}
		
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition","attachment;filename=itemGroupOne.xlsx");
		
		wb.write(response.getOutputStream());
		wb.close();
		
	}
	*/
	
	/**
	 * 품목그룹 1을 엑셀 파일로 DB에 List로 insert 처리한다.
	 * 
	 * @param request
	 * @param model
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value="/itemGroupOne/itemGroupOneInsert.do")
	public String itemGroupOneInsert(HttpServletRequest request, Model model, MultipartFile file) throws Exception {
		
		try {
			
			List<ItemGroupDto> list = new ArrayList<>();
			
			InputStream in = file.getInputStream();
			fileLocation = "C:\\excelUpload\\"+file.getOriginalFilename();
			  
			FileOutputStream f = new FileOutputStream(fileLocation);
			
			int ch = 0; while ((ch = in.read()) != -1) {
				f.write(ch);
			}
			f.flush();
			f.close();
			
			Workbook wb = null;
			
			wb = new XSSFWorkbook(file.getInputStream());
			  
			Sheet sheet = wb.getSheetAt(0);
			
			for(int i=1;i<sheet.getPhysicalNumberOfRows(); i++) {
				Row row = sheet.getRow(i);
					  
				ItemGroupDto itemGroupOneDto = new ItemGroupDto();
//				itemGroupOneDto.setAdminCode(row.getCell(0).getStringCellValue());
//				itemGroupOneDto.setCodeName(row.getCell(1).getStringCellValue());
//				itemGroupOneDto.setEtc(row.getCell(2).getStringCellValue());
				
				itemGroupOneDto.setSeq((int)row.getCell(0).getNumericCellValue());
				itemGroupOneDto.setAdminCode(row.getCell(1).getStringCellValue());
				itemGroupOneDto.setCodeName(row.getCell(2).getStringCellValue());
				itemGroupOneDto.setEtc(row.getCell(3).getStringCellValue());
				
				list.add(itemGroupOneDto);
			}
			
//			itemGroupOneService.insertItemGroupOne(list);
			itemGroupService.insertUpdateItemGroupOne(list);
			
			/*
			 * insert에 사용된 파일을 삭제한다.
			 */
			File tempFile = new File(fileLocation);
			tempFile.delete();
			
			
		} catch(Exception e) {
			
			e.getStackTrace();
		
		}
		  
	return "redirect:/basic/itemGroupOneList.do";

	}
	
	/**
	 * [품목그룹 1]에 대해서 insert 및 update를 같이 진행한다.
	 * seq값이 있다면 update,
	 * seq값이 없다면 insert를 진행한다.
	 * 
	 * @param request
	 * @param model
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value="/itemGroupOne/itemGroupOneUpdate.do")
	public String itemGroupOneInsertUpdate(HttpServletRequest request, Model model, MultipartFile file) throws Exception {
		
		int result = 0;
		
		try {
			
			List<ItemGroupDto> list = new ArrayList<>();
			
			InputStream in = file.getInputStream();
			fileLocation = "C:\\excelUpload\\"+file.getOriginalFilename();
			  
			FileOutputStream f = new FileOutputStream(fileLocation);
			
			int ch = 0; while ((ch = in.read()) != -1) {
				f.write(ch);
			}
			f.flush();
			f.close();
			
			Workbook wb = null;
			
			wb = new XSSFWorkbook(file.getInputStream());
			  
			Sheet sheet = wb.getSheetAt(0);
			
			for(int i=1;i<sheet.getPhysicalNumberOfRows(); i++) {
				Row row = sheet.getRow(i);
					  
				ItemGroupDto itemGroupOneDto = new ItemGroupDto();
				itemGroupOneDto.setSeq((int) row.getCell(0).getNumericCellValue());
				itemGroupOneDto.setAdminCode(row.getCell(1).getStringCellValue());
				itemGroupOneDto.setCodeName(row.getCell(2).getStringCellValue());
				itemGroupOneDto.setEtc(row.getCell(3).getStringCellValue());
				
				list.add(itemGroupOneDto);
				
			}
			
			result = itemGroupService.insertUpdateItemGroupOne(list);
			
			/*
			 * insert에 사용된 파일을 삭제한다.
			 */
			File tempFile = new File(fileLocation);
			tempFile.delete();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("insert or update result => " + result);
		
		return "redirect:/basic/itemGroupOneList.do";
	}
	
	/**
	 * 품목그룹 1 Detail.
	 * 
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="itemGroupOne/itemGroupDetail.do", method=RequestMethod.GET)
	public ModelAndView itemGroupDetail(String seq) throws Exception {
		
		ModelAndView mv = new ModelAndView("itemGroup/itemGroupOneDetail");
		
		ItemGroupDto itemGroupDto = new ItemGroupDto();
		
		itemGroupDto = itemGroupService.selectItemGroupOneDetail(seq);
		mv.addObject("itemGroupOne",itemGroupDto);
		
		return mv;
	}
	
	/**
	 * 품목그룹 1 Update.
	 * 
	 * @param itemGroupDto
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="itemGroupOne/itemGroupOneUpdate.do", method=RequestMethod.POST)
	public String itemGroupOneUpdate(ItemGroupDto itemGroupDto, HttpServletRequest request) throws Exception {
		
		itemGroupService.updateItemGroupOne(itemGroupDto);
		String redirect = "redirect:/itemGroupOne/itemGroupDetail.do?seq="+itemGroupDto.getSeq();
		
		return redirect;
	}

}
