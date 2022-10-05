package com.atom.excel.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atom.excel.dto.ExcelDto;

@Controller
public class ExcelController {
	
	private String fileLocation;
	
	@RequestMapping(value="/excel/excelUploadView.do")
	public String excelUploadView() throws Exception {
		return "excel/excelUploadView";
	}

	/**
	 * 엑셀 업로드 화면으로 이동.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/excel/excelUpload.do")
	public String excelUpload() throws Exception {
		return "excel/excelUpload";
	}
	
	
	/**
	 * 
	 * 엑셀 파일 선택 후 읽기.
	 * @param request
	 * @param model
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value="/excel/excelUploadDone.do", method=RequestMethod.POST)
	public String listApplicant(HttpServletRequest request, ModelMap model, MultipartFile file) throws Exception {
		
//		FileInputStream fis = new FileInputStream("C:\\excelRead\\test02.xlsx");
//		InputStream filePath = file.getInputStream();
//		File currDir = new File(".");
//		String path = currDir.getAbsolutePath();
		
		
		InputStream in = file.getInputStream();
//	    fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
		fileLocation = "C:\\excelRead\\"+file.getOriginalFilename();
	    
	    FileOutputStream f = new FileOutputStream(fileLocation);
	    
	    System.out.println("fileLocation : > " + fileLocation);
	    
	    int ch = 0;
	    while ((ch = in.read()) != -1) {
	        f.write(ch);
	    }
	    f.flush();
	    f.close();
		
//		List<ExcelDto> dataList = new ArrayList<>();
//		
//		Workbook workbook = null;
//		
//		workbook = new XSSFWorkbook(fis);
//		
//		Sheet worksheet = workbook.getSheetAt(0);
//		
//		for(int i=1; i<worksheet.getPhysicalNumberOfRows();i++) {
//			Row row = worksheet.getRow(i);
//			
//			ExcelDto excelDto = new ExcelDto();
//			
//			excelDto.setName(row.getCell(0).getStringCellValue());
//			excelDto.setAddress(row.getCell(1).getStringCellValue());
//			excelDto.setPhone(row.getCell(2).getStringCellValue());
//			
//			dataList.add(excelDto);
//			
//		}
//		
//		model.addAttribute("dataList", dataList);
//		
//		System.out.println("dataList.size() >> " + dataList.size());
		
		
		return "excel/excelUpload";
	}
	
}
