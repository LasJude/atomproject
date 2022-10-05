package com.atom.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.atom.dto.BasicDto;
import com.atom.dto.ConvertDto;
import com.atom.service.BasicService;
import com.atom.service.ConvertService;

import groovyjarjarantlr4.v4.parse.GrammarTreeVisitor.outerAlternative_return;

@Controller
public class ConvertController {
	
	
	private static final String categoryTotal = null;

	@Autowired
	private ConvertService convertService;
	
	@Autowired
	private BasicService basicService;
	
	@Autowired
	private BasicController basicController;
	
	private String fileLocation;

	/**
	 *  검증 입력 페이지 이동.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/convert/convertInsert.do")
	public String convertInsert() throws Exception {
		return "convert/convertInsert";
	}
	
	/**
	 *  매출 검증 이동.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/convert/convertSalesList.do")
	public String convertSales() throws Exception {
		return "convert/convertSalesList";
	}
	
	/**
	 *  매출 검증 양식 다운로드.
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/convert/convertSalesDownload.do")
	public void convertSalesDownload(HttpServletResponse response) throws Exception {
		
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("매출입력");
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("매출지점");
		cell = row.createCell(1);
		cell.setCellValue("일자");
		cell = row.createCell(2);
		cell.setCellValue("거래처연동코드");
		cell = row.createCell(3);
		cell.setCellValue("거래처(부서)");
		cell = row.createCell(4);
		cell.setCellValue("관리번호");
		cell = row.createCell(5);
		cell.setCellValue("상품코드");
		cell = row.createCell(6);
		cell.setCellValue("책자코드");
		cell = row.createCell(7);
		cell.setCellValue("상품명");
		cell = row.createCell(8);
		cell.setCellValue("규격");
		cell = row.createCell(9);
		cell.setCellValue("단위");
		cell = row.createCell(10);
		cell.setCellValue("단위수량");
		cell = row.createCell(11);
		cell.setCellValue("매입처");
		cell = row.createCell(12);
		cell.setCellValue("제조사");
		cell = row.createCell(13);
		cell.setCellValue("판매수량");
		cell = row.createCell(14);
		cell.setCellValue("금액");
		cell = row.createCell(15);
		cell.setCellValue("소비자가");
		cell = row.createCell(16);
		cell.setCellValue("고객속성");
		cell = row.createCell(17);
		cell.setCellValue("고객분류");
		cell = row.createCell(18);
		cell.setCellValue("부서그룹");
		cell = row.createCell(19);
		cell.setCellValue("거래분류");
		cell = row.createCell(20);
		cell.setCellValue("영업담당");
		cell = row.createCell(21);
		cell.setCellValue("매출코스");
		cell = row.createCell(22);
		cell.setCellValue("품목구분");
		cell = row.createCell(23);
		cell.setCellValue("품목그룹1");
		cell = row.createCell(24);
		cell.setCellValue("품목그룹2");
		cell = row.createCell(25);
		cell.setCellValue("품목그룹3");
		cell = row.createCell(26);
		cell.setCellValue("품목그룹4");
		cell = row.createCell(25);
		cell.setCellValue("품목그룹5");
		cell = row.createCell(26);
		cell.setCellValue("비고");
		cell = row.createCell(27);
		cell.setCellValue("전표메모");
		cell = row.createCell(28);
		cell.setCellValue("관리자메모");
		
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition","attachment;filename=convertSales.xlsx");
		
		wb.write(response.getOutputStream());
		wb.close();
		
	}
	
	
	/**
	 * 상품 검증 입력.
	 * @param request
	 * @param model
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value="/convert/goodsConvertInsert.do")
	public String convertGoodsInsert(HttpServletRequest request, Model model, MultipartFile file) throws Exception {

		int result = 0;
		
		try {
			List<ConvertDto> list = new ArrayList<>();
			InputStream in = file.getInputStream();
			fileLocation = "C:\\excelUpload\\"+file.getOriginalFilename();
			
			FileOutputStream f = new FileOutputStream(fileLocation);
			
			int ch = 0;
			
			while((ch = in.read()) != -1) {
				f.write(ch);
			}
			f.flush();
			f.close();

			Workbook wb = null;
			
			wb = new XSSFWorkbook(file.getInputStream());
			
			Sheet sheet = wb.getSheetAt(0);
			
			Row name = sheet.getRow(0);
			
			for(int i=1; i<sheet.getPhysicalNumberOfRows(); i++) {
				Row row = sheet.getRow(i);
				ConvertDto convertDto = new ConvertDto();
				String colData = "";
				String colName = "";
				String standardSeq = "";
				String unitSeq = "";
				
				for(int j=0; j<sheet.getRow(0).getPhysicalNumberOfCells(); j++) {
					colName = name.getCell(j).toString();
					if(row.getCell(j) == null) {
						colData = "";
					} else {
						colData = row.getCell(j).toString();
					}
					
					switch(colName) {
					case "관리번호":
						convertDto.setConvertGoodsAdminSeq(colData.substring(0, colData.length()-2));
						break;
					case "기준코드":
						standardSeq = colData;
						convertDto.setConvertGoodsStandardSeq(colData);
						break;
					case "단위코드":
						unitSeq = colData;
						convertDto.setConvertGoodsUnitSeq(colData);
						break;
					case "바코드":
						convertDto.setConvertGoodsBarcode(colData);
						break;
					case "품번":
						convertDto.setConvertGoodsNameCode1(colData);
						break;
					case "품번2":
						convertDto.setConvertGoodsNameCode2(colData);
						break;
					case "품번3":
						convertDto.setConvertGoodsNameCode3(colData);
						break;
					case "품번4":
						convertDto.setConvertGoodsNameCode4(colData);
						break;
					case "책자코드":
						convertDto.setConvertGoodsModelName(colData);
						break;
					case "품명":
						convertDto.setConvertGoodsGoodsName1(colData);
						break;
					case "품명2":
						convertDto.setConvertGoodsGoodsName2(colData);
						break;
					case "규격":
						convertDto.setConvertGoodsStandard(colData);
						break;
					case "단위":
						convertDto.setConvertGoodsUnit(colData);
						break;
					case "단위수량":
						convertDto.setConvertGoodsUnitCount(colData);
						break;
					case "상품그룹":
						convertDto.setConvertGoodsGoodsGroup(colData);
						break;
					case "분류":
						convertDto.setConvertGoodsCategory(colData);
						break;
					case "매입사":
						convertDto.setConvertGoodsBuyCom(colData);
						break;
					case "제조사":
						convertDto.setConvertGoodsMakeCom(colData);
						break;
					case "매입가":
						convertDto.setConvertGoodsBuyPrice(colData.substring(0, colData.length()-2));
						break;
					case "실원가":
						convertDto.setConvertGoodsPrimePrice(colData.substring(0, colData.length()-2));
						break;
					case "소비자가":
						convertDto.setConvertGoodsCustomerPrice(colData.substring(0, colData.length()-2));
						break;
					case "판매가1":
						convertDto.setConvertGoodsSellPrice1(colData.substring(0, colData.length()-2));
						break;
					case "판매가2":
						convertDto.setConvertGoodsSellPrice2(colData.substring(0, colData.length()-2));
						break;
					case "판매가3":
						convertDto.setConvertGoodsSellPrice3(colData.substring(0, colData.length()-2));
						break;
					case "판매가4":
						convertDto.setConvertGoodsSellPrice4(colData.substring(0, colData.length()-2));
						break;
					case "판매가5":
						convertDto.setConvertGoodsSellPrice5(colData.substring(0, colData.length()-2));
						break;
					case "판매가6":
						convertDto.setConvertGoodsSellPrice6(colData.substring(0, colData.length()-2));
						break;
					case "판매가7":
						convertDto.setConvertGoodsSellPrice7(colData.substring(0, colData.length()-2));
						break;
					case "판매가8":
						convertDto.setConvertGoodsSellPrice8(colData.substring(0, colData.length()-2));
						break;
					case "판매가9":
						convertDto.setConvertGoodsSellPrice9(colData.substring(0, colData.length()-2));
						break;
					case "판매가10":
						convertDto.setConvertGoodsSellPrice10(colData.substring(0, colData.length()-2));
						break;
					case "판매가11":
						convertDto.setConvertGoodsSellPrice11(colData.substring(0, colData.length()-2));
						break;
					case "판매가12":
						convertDto.setConvertGoodsSellPrice12(colData.substring(0, colData.length()-2));
						break;
					case "판매가13":
						convertDto.setConvertGoodsSellPrice13(colData.substring(0, colData.length()-2));
						break;
					case "판매가14":
						convertDto.setConvertGoodsSellPrice14(colData.substring(0, colData.length()-2));
						break;
					case "단종":
						convertDto.setConvertGoodsDiscontinue(colData);
						break;
					case "단종일자":
						convertDto.setConvertGoodsDiscontinueDt(colData);
						break;
					case "원산지":
						convertDto.setConvertGoodsCountry(colData);
						break;
					case "비고":
						convertDto.setConvertGoodsEtc(colData);
						break;
					case "등록일":
						convertDto.setConvertGoodsEnterDt(colData);
						break;
					case "품목그룹1":
						convertDto.setConvertGoodsItemGroup1(colData);
						break;
					case "품목그룹2":
						convertDto.setConvertGoodsItemGroup2(colData);
						break;
					case "품목그룹3":
						convertDto.setConvertGoodsItemGroup3(colData);
						break;
					case "품목그룹4":
						convertDto.setConvertGoodsItemGroup4(colData);
						break;
					case "품목그룹5":
						convertDto.setConvertGoodsItemGroup5(colData);
						break;
					case "단위구분":
						if(standardSeq.equals(unitSeq)) {
							convertDto.setConvertGoodsUnitType("기준");
						} else {
							convertDto.setConvertGoodsUnitType("단위");
						}
						break;
					}
				}
				list.add(convertDto);
			}
			
			convertService.deleteConvertGoodsTable();		//	ConvertGoods 테이블의 자료를 삭제한다.
			
			result = convertService.insertConvertGoods(list);
			
			/*
			 * insert에 사용된 파일을 삭제한다.
			 */
			File tempFile = new File(fileLocation);
			tempFile.delete();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/convert/convertGoodsResult.do";
	}
	
	
	
	
	/**
	 *  거래처 검증 입력.
	 * @param request
	 * @param model
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value="/convert/companyConvertInsert.do")
	public String convertCompanyInsert(HttpServletRequest request, Model model, MultipartFile file) throws Exception {
		
		int result = 0;
		
		try {
			List<ConvertDto> list = new ArrayList<>();
			InputStream in = file.getInputStream();
			fileLocation = "C:\\excelUpload\\"+file.getOriginalFilename();
			
			FileOutputStream f = new FileOutputStream(fileLocation);
			
			int ch = 0;
			
			while((ch = in.read()) != -1) {
				f.write(ch);
			}
			f.flush();
			f.close();
			
			Workbook wb = null;
			
			wb = new XSSFWorkbook(file.getInputStream());
			
			Sheet sheet = wb.getSheetAt(0);
			
			Row name = sheet.getRow(0);
			
			for(int i=1; i<sheet.getPhysicalNumberOfRows(); i++) {
				Row row = sheet.getRow(i);
				ConvertDto convertDto = new ConvertDto();
				String colData = "";
				String colName = "";
				for(int j=0; j<sheet.getRow(0).getPhysicalNumberOfCells(); j++) {
					colName = name.getCell(j).toString();
					if(row.getCell(j) == null) {
						colData = null;
					} else {
						colData = row.getCell(j).toString();
					}
					
					switch(colName) {
					case "거래구분":
						convertDto.setConvertComDealDivi(colData);
						break;
					case "관리코드":
						convertDto.setConvertComAdminCode(colData);
						break;
					case "코드":
						convertDto.setConvertComCode(colData);
						break;
					case "고객카드":
						convertDto.setConvertComCard(colData);
						break;
					case "거래처":
						convertDto.setConvertComName1(colData);
						break;
					case "부서":
						convertDto.setConvertComDepartment(colData);
						break;
					case "담당자":
						convertDto.setConvertComCharge(colData);
						break;
					case "담당전화":
						convertDto.setConvertComChargePhone1(colData);
						break;
					case "휴대전화":
						convertDto.setConvertComPhone2(colData);
						break;
					case "전화번호":
						convertDto.setConvertComPhone1(colData);
						break;
					case "팩스":
						convertDto.setConvertComFax(colData);
						break;
					case "이메일":
						convertDto.setConvertComEmail(colData);
						break;
					case "부서그룹":
						convertDto.setConvertComDepaGroup(colData);
						break;
					case "고객속성":
						convertDto.setConvertComAttri(colData);
						break;
					case "고객분류":
						convertDto.setConvertComDivi(colData);
						break;
					case "거래분류":
						convertDto.setConvertComDealType(colData);
						break;
					case "코스":
						convertDto.setConvertComCourse(colData);
						break;
					case "영업담당":
						convertDto.setConvertComSalesStaff(colData);
						break;
					case "주소":
						convertDto.setConvertComDeliAddress(colData);
						break;
					case "상세주소":
						convertDto.setConvertComDeliAddressEtc(colData);
						break;
					case "사업자상호":
						convertDto.setConvertComName2(colData);
						break;
					case "법인번호":
						convertDto.setConvertComCorporNumber(colData);
						break;
					case "종사업장번호":
						convertDto.setConvertComPlaceNumber(colData);
						break;
					case "대표자":
						convertDto.setConvertComCeo(colData);
						break;
					case "계산서담당":
						convertDto.setConvertComTaxCharge(colData);
						break;
					case "업태":
						convertDto.setConvertComStatus(colData);
						break;
					case "종목":
						convertDto.setConvertComType(colData);
						break;
					case "주소2":
						convertDto.setConvertComAddress(colData);
						break;
					case "상세주소2":
						convertDto.setConvertComAddressEtc(colData);
						break;
					case "계산서담당전화번호":
						convertDto.setConvertComTaxPhone1(colData);
						break;
					case "계산서담당핸드폰":
						convertDto.setConvertComTaxPhone2(colData);
						break;
					case "계산서이메일":
						convertDto.setConvertComTaxEmail(colData);
						break;
					case "거래중지":
						convertDto.setConvertComDealYn(colData);
						break;
					case "비고":
						convertDto.setConvertComEtc(colData);
						break;
					case "행사제외":
						convertDto.setConvertComEventYn(colData);
						break;
					case "폐업여부":
						convertDto.setConvertComAShutDownYn(colData);
						break;
					case "폐업일":
						convertDto.setConvertComAShutDownDt(colData);
						break;
					}
				}
				list.add(convertDto);
			}
			
			
			convertService.deleteConvertCompanyTable();		//	ConvertComapny 테이블의 자료를 삭제한다.
			
			result = convertService.insertConvertCompany(list);
			
			/*
			 * insert에 사용된 파일을 삭제한다.
			 */
			File tempFile = new File(fileLocation);
			tempFile.delete();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 *  매출 정보를 엑셀로 입력한다.
	 *  입력 후 사용된 엑셀 파일을 삭제한다.
	 *  정상적으로 입력이 되면, Result 화면으로 이동한다.
	 *  
	 * @param request
	 * @param model
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value="/convert/salesConvertInsert.do")
	public String convertSalesInsert(HttpServletRequest request, Model model, MultipartFile file) throws Exception {
		
		int result = 0;
		
		try {
			
			List<ConvertDto> list = new ArrayList<>();
			
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
				ConvertDto convertDto = new ConvertDto();
				convertDto.setComCharge(row.getCell(0).getStringCellValue());										//	매출지점.
				convertDto.setSalesDt(row.getCell(1).getStringCellValue());											//	일자.
				convertDto.setComCode(Integer.toString((int)row.getCell(2).getNumericCellValue()));		//	거래처연동코드.	//	String 처리.
				convertDto.setComCompany(row.getCell(3).getStringCellValue());									//	거래처(부서).
				convertDto.setGoodsCode(Integer.toString((int)row.getCell(4).getNumericCellValue()));	//	관리번호.			//	String 처리.
				convertDto.setBarcode(row.getCell(5).getStringCellValue());											//	상품코드.
				convertDto.setModelCode(row.getCell(6).getStringCellValue());										//	책자코드.
				convertDto.setGoodsName(row.getCell(7).getStringCellValue());									//	상품명.
				convertDto.setStandard(row.getCell(8).getStringCellValue());										//	규격.
				convertDto.setUnit(row.getCell(9).getStringCellValue());												//	단위.
				convertDto.setUnitCount((int)row.getCell(10).getNumericCellValue());							//	단위수량.
				convertDto.setBuyCompany(row.getCell(11).getStringCellValue());									//	매입처.
				convertDto.setMakeCompany(row.getCell(12).getStringCellValue());								//	제조사.
				convertDto.setSalesCount((int)row.getCell(13).getNumericCellValue());							//	판매수량.
				convertDto.setPrice((int)row.getCell(14).getNumericCellValue());									//	단가.
				convertDto.setSalesPrice((int)row.getCell(15).getNumericCellValue());							//	금액.
				convertDto.setCustomerPrice((int)row.getCell(16).getNumericCellValue());						//	소비자가.
				convertDto.setComAttri(row.getCell(17).getStringCellValue());										//	고객속성.
				convertDto.setComDivi(row.getCell(18).getStringCellValue());										//	고객분류.
				convertDto.setComDepaGroup(row.getCell(19).getStringCellValue());								//	부서그룹.
				convertDto.setComDealType(row.getCell(20).getStringCellValue());								//	거래분류.
				convertDto.setSalesEmployee(row.getCell(21).getStringCellValue());								//	영업담당.
				convertDto.setSalesCourse(row.getCell(22).getStringCellValue());									//	매출코스.
				convertDto.setGoodsType(row.getCell(23).getStringCellValue());									//	품목구분.
				convertDto.setItemGroupOne(row.getCell(24).getStringCellValue());								//	품목그룹1.
				convertDto.setItemGroupTwo(row.getCell(25).getStringCellValue());								//	품목그룹2.
				convertDto.setItemGroupThree(row.getCell(26).getStringCellValue());								//	품목그룹3.
				convertDto.setItemGroupFour(row.getCell(27).getStringCellValue());								//	품목그룹4.
				convertDto.setItemGroupFive(row.getCell(28).getStringCellValue());								//	품목그룹5.
				convertDto.setEtc(row.getCell(29).getStringCellValue());												//	비고.
				convertDto.setStatementMemo(row.getCell(30).getStringCellValue());								//	전표메모.
				convertDto.setAdminMemo(row.getCell(31).getStringCellValue());									//	관리자메모.
				
				list.add(convertDto);
			
			}
			convertService.deleteConvertTable();
			
			result = convertService.insertConvertSales(list);
			
			/*
			 * insert에 사용된 파일을 삭제한다.
			 */
			File tempFile = new File(fileLocation);
			tempFile.delete();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		
		}
		
		return "redirect:/convert/convertSalesResult.do";
	}
	
	
	/**
	 *  매출 검증 된 내용 정리.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/convert/convertSalesResult.do")
	public ModelAndView convertSalesResult() throws Exception {
		
		ModelAndView mv = new ModelAndView("convert/convertSalesResult");
		
		try {
			
			ConvertDto convertDto = new ConvertDto();
			
			List<ConvertDto> convertList = convertService.selectTbConvert();
			
			List<ConvertDto> emptyCompanyList = convertService.selectEmptyCompany();						//	매출처
			List<ConvertDto> emptyGoodsList = convertService.selectEmptyGoods();								//	상품
			List<ConvertDto> emptyAttriList = convertService.selectEmptyAttri();									//	고객속성
			List<ConvertDto> emptyBuyComList = convertService.selectEmptyBuyCom();							//	매입처
			List<ConvertDto> emptyMakeComList = convertService.selectEmptyMakeCom();						//	제조사
			List<ConvertDto> emptyComDiviList = convertService.selectEmptyComDivi();							//	고객분류
			List<ConvertDto> emptyDepaGroupList = convertService.selectEmptyDepaGroup();					//	부서그룹
			List<ConvertDto> emptyComDealTypeList = convertService.selectEmptyComDealType();			//	거래분류
			List<ConvertDto> emptyEmployeeList = convertService.selectEmptyEmployee();						//	사원
			List<ConvertDto> emptyCourseList = convertService.selectEmptyCourse();								//	매출코스
			List<ConvertDto> emptyItemGroupOneList = convertService.selectEmptyItemGroupOne();		//	품목그룹1
			List<ConvertDto> emptyItemGroupTwoList = convertService.selectEmptyItemGroupTwo();		//	품목그룹2
			List<ConvertDto> emptyItemGroupThreeList = convertService.selectEmptyItemGroupThree();	//	품목그룹3
			List<ConvertDto> emptyItemGroupFourList = convertService.selectEmptyItemGroupFour();		//	품목그룹4
			List<ConvertDto> emptyItemGroupFiveList = convertService.selectEmptyItemGroupFive();		//	품목그룹5
			
			/**
			 *  등록 안된 값에 대한 검증 작업.
			 */
			for(int i=0; i<convertList.size();i++) {
				if(emptyCompanyList.size()>0) {							// 등록 안된 거래처 체크. 
					for(int j=0;j<emptyCompanyList.size(); j++) {
						if(convertList.get(i).getComCode().equals(emptyCompanyList.get(j).getEmptyCompany())) {
							convertList.get(i).setVerify("검증");						
						}
					}
				}
				
				if(emptyGoodsList.size()>0) {								//	등록 안된 상품 체크.
					for(int j=0; j<emptyGoodsList.size(); j++) {
						if(convertList.get(i).getGoodsCode().equals(emptyGoodsList.get(j).getEmptyGoods())) {
							convertList.get(i).setVerify("검증");
						}
					}					
				}
				
				if(emptyAttriList.size()>0) {									//	등록 안된 고객 속성.
					for(int j=0; j<emptyAttriList.size(); j++) {
						if(convertList.get(i).getComAttri().equals(emptyAttriList.get(j).getEmptyAttri())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
				
				if(emptyBuyComList.size()>0) {							//	등록 안된 매입처 체크.
					for(int j=0; j<emptyBuyComList.size(); j++) {
						if(convertList.get(i).getBuyCompany().equals(emptyBuyComList.get(j).getEmptyBuyCom())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
				
				if(emptyMakeComList.size()>0) {							//	등록 안된 제조사 체크.
					for(int j=0;j<emptyMakeComList.size(); j++) {
						if(convertList.get(i).getMakeCompany().equals(emptyMakeComList.get(j).getEmptyMakeCom())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
				
				if(emptyComDiviList.size()>0) {							//	등록 안된 고객분류 체크.
					for(int j=0; j<emptyComDiviList.size(); j++) {
						if(convertList.get(i).getComDivi().equals(emptyComDiviList.get(j).getEmptyComDivi())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
				
				if(emptyDepaGroupList.size()>0) {						//	등록 안된 부서그룹 체크.
					for(int j=0; j<emptyDepaGroupList.size(); j++) {
						if(convertList.get(i).getComDepaGroup().equals(emptyDepaGroupList.get(j).getEmptyDepaGroup())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
				
				if(emptyComDealTypeList.size()>0) {						//	등록 안된 거래분류 체크.
					for(int j=0; j<emptyComDealTypeList.size(); j++) {
						if(convertList.get(i).getComDealType().equals(emptyComDealTypeList.get(j).getEmptyComDealType())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
				
				if(emptyEmployeeList.size()>0) {							//	등록 안된 사원 체크.
					for(int j=0; j<emptyEmployeeList.size(); j++) {
						if(convertList.get(i).getSalesEmployee().equals(emptyEmployeeList.get(j).getEmptyEmployee())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
				
				if(emptyCourseList.size()>0) {								//	등록 안된 코스 체크.
					for(int j=0; j<emptyCourseList.size(); j++) {
						if(convertList.get(i).getSalesCourse().equals(emptyCourseList.get(j).getSalesCourse())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
				
				if(emptyItemGroupOneList.size()>0) {					//	등록 안된 품목그룹1 체크.
					for(int j=0; j<emptyItemGroupOneList.size(); j++) {
						if(convertList.get(i).getItemGroupOne().equals(emptyItemGroupOneList.get(j).getEmptyItemGroupOne())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
				
				if(emptyItemGroupTwoList.size()>0) {					//	등록 안된 품목그룹2 체크.
					for(int j=0; j<emptyItemGroupTwoList.size(); j++) {
						if(convertList.get(i).getItemGroupTwo().equals(emptyItemGroupTwoList.get(j).getEmptyItemGroupTwo())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
				
				if(emptyItemGroupThreeList.size()>0) {					//	등록 안된 품목그룹3 체크.
					for(int j=0; j<emptyItemGroupThreeList.size(); j++) {
						if(convertList.get(i).getItemGroupThree().equals(emptyItemGroupThreeList.get(j).getEmptyItemGroupThree())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
				
				if(emptyItemGroupFourList.size()>0) {					//	등록 안된 품목그룹4 체크.
					for(int j=0;j<emptyItemGroupFourList.size(); j++) {
						if(convertList.get(i).getItemGroupFour().equals(emptyItemGroupFourList.get(j).getEmptyItemGroupFour())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
				
				if(emptyItemGroupFiveList.size()>0) {					//	등록 안된 품목그룹5 체크.
					for(int j=0;j<emptyItemGroupFiveList.size(); j++) {
						if(convertList.get(i).getItemGroupFive().equals(emptyItemGroupFiveList.get(j).getEmptyItemGroupFive())) {
							convertList.get(i).setVerify("검증");
						}
					}
				}
			}
			
			convertDto.setEmptyCompanySize(emptyCompanyList.size());						// 매출처
			convertDto.setEmptyGoodsSize(emptyGoodsList.size());								//	상품
			convertDto.setEmptyAttriSize(emptyAttriList.size());									//	고객속성
			convertDto.setEmptyBuyComSize(emptyBuyComList.size());							//	매입처
			convertDto.setEmptyMakeComSize(emptyMakeComList.size());						//	제조사
			convertDto.setEmptyComDiviSize(emptyComDiviList.size());							//	고객분류
			convertDto.setEmptyDepaGroupSize(emptyDepaGroupList.size());					//	부서그룹
			convertDto.setEmptyComDealTypeSize(emptyComDealTypeList.size());			//	거래분류
			convertDto.setEmptyEmployeeSize(emptyEmployeeList.size());						//	사원
			convertDto.setEmptyCourseSize(emptyCourseList.size());								//	매출코스
			convertDto.setEmptyItemGroupOneSize(emptyItemGroupOneList.size());		//	품목그룹1
			convertDto.setEmptyItemGroupTwoSize(emptyItemGroupTwoList.size());		//	품목그룹2
			convertDto.setEmptyItemGroupThreeSize(emptyItemGroupThreeList.size());	//	품목그룹3
			convertDto.setEmptyItemGroupFourSize(emptyItemGroupFourList.size());		//	품목그룹4
			convertDto.setEmptyItemGroupFiveSize(emptyItemGroupFiveList.size());		//	품목그룹5
			
			mv.addObject("convertList", convertList);
			
			mv.addObject("emptyCompanyList", emptyCompanyList);
			mv.addObject("emptyGoodsList", emptyGoodsList);
			mv.addObject("emptyAttriList", emptyAttriList);
			mv.addObject("emptyBuyComList", emptyBuyComList);
			mv.addObject("emptyMakeComList", emptyMakeComList);
			mv.addObject("emptyComDiviList", emptyComDiviList);
			mv.addObject("emptyDepaGroupList", emptyDepaGroupList);
			mv.addObject("emptyComDealTypeList", emptyComDealTypeList);
			mv.addObject("emptyEmployeeList", emptyEmployeeList);
			mv.addObject("emptyCourseList", emptyCourseList);
			mv.addObject("emptyItemGroupOneList", emptyItemGroupOneList);
			mv.addObject("emptyItemGroupTwoList", emptyItemGroupTwoList);
			mv.addObject("emptyItemGroupThreeList", emptyItemGroupThreeList);
			mv.addObject("emptyItemGroupFourList", emptyItemGroupFourList);
			mv.addObject("emptyItemGroupFiveList", emptyItemGroupFiveList);
			
			mv.addObject(convertDto);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	
	}
	
	/**
	 *  매출 검증에서 검증이 필요한 내역 정리.
	 * @param verifyType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/convert/verifyList.do", method = RequestMethod.GET)
	public ModelAndView verityType(String verifyType) throws Exception {
		
		List<ConvertDto> verifyList = new ArrayList<ConvertDto>();
		ModelAndView mv = new ModelAndView("convert/verityList");
		
		if(verifyType.equals("emptyCompany")) {								//	거래처 검증.
			verifyList = convertService.selectEmptyCompany();
		} else if(verifyType.equals("emptyGoods")) {							//	상품 검증.
			verifyList = convertService.selectEmptyGoods();
		} else if(verifyType.equals("emptyAttri")) {								//	고객속성 검증.
			verifyList = convertService.selectEmptyAttri();
		} else if(verifyType.equals("emptyBuyCom")) {						//	매입처 검증.
			verifyList = convertService.selectEmptyBuyCom();
		} else if(verifyType.equals("emptyMakeCom")) {						//	제조사 검증.
			verifyList = convertService.selectEmptyMakeCom();
		} else if(verifyType.equals("emptyComDivi")) {						//	고객분류 검증.
			verifyList = convertService.selectEmptyComDivi();
		} else if(verifyType.equals("emptyDepaGroup")) {					//	부서그룹 검증.
			verifyList = convertService.selectEmptyDepaGroup();
		} else if(verifyType.equals("emptyEmployee")) {						//	사원 검증.
			verifyList = convertService.selectEmptyEmployee();
		} else if(verifyType.equals("emptyCourse")) {							//	매출 코스 검증.
			verifyList = convertService.selectEmptyCourse();
		} else if(verifyType.equals("emptyItemGroupOne")) {				//	품목그룹1 검증.
			verifyList = convertService.selectEmptyItemGroupOne();
		} else if(verifyType.equals("emptyItemGroupTwo")) {				//	품목그룹2 검증.
			verifyList = convertService.selectEmptyItemGroupTwo();
		} else if(verifyType.equals("emptyItemGroupThree")) {				//	품목그룹3 검증.
			verifyList = convertService.selectEmptyItemGroupThree();
		} else if(verifyType.equals("emptyItemGroupFour")) {				//	품목그룹4 검증.
			verifyList = convertService.selectEmptyItemGroupFour();
		} else if(verifyType.equals("emptyItemGroupFive")) {				//	품목그룹5 검증.
			verifyList = convertService.selectEmptyItemGroupFive();
		}
		
		mv.addObject("verifyList", verifyList);
		mv.addObject("verifyType", verifyType);
		
		return mv;
	}


	/**
	 *  거래처 정보 입력 양식을 다운로드 한다.
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/convert/convertCompanyDownload.do")
	public void convertCompanyDownload(HttpServletResponse response) throws Exception {
		
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("거래처입력");
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("거래구분");
		cell = row.createCell(1);
		cell.setCellValue("관리코드");
		cell = row.createCell(2);
		cell.setCellValue("코드");
		cell = row.createCell(3);
		cell.setCellValue("고객카드");
		cell = row.createCell(4);
		cell.setCellValue("거래처");
		cell = row.createCell(5);
		cell.setCellValue("부서");
		cell = row.createCell(6);
		cell.setCellValue("담당자");
		cell = row.createCell(7);
		cell.setCellValue("담당전화");
		cell = row.createCell(8);
		cell.setCellValue("휴대전화");
		cell = row.createCell(9);
		cell.setCellValue("전화번호");
		cell = row.createCell(10);
		cell.setCellValue("팩스번호");
		cell = row.createCell(11);
		cell.setCellValue("이메일");
		cell = row.createCell(12);
		cell.setCellValue("부서그룹");
		cell = row.createCell(13);
		cell.setCellValue("고객속성");
		cell = row.createCell(14);
		cell.setCellValue("고객분류");
		cell = row.createCell(15);
		cell.setCellValue("거래분류");
		cell = row.createCell(16);
		cell.setCellValue("코스");
		cell = row.createCell(17);
		cell.setCellValue("영업담당");
		cell = row.createCell(18);
		cell.setCellValue("주소");
		cell = row.createCell(19);
		cell.setCellValue("상세주소");
		cell = row.createCell(20);
		cell.setCellValue("사업자상호");
		cell = row.createCell(21);
		cell.setCellValue("법인번호");
		cell = row.createCell(22);
		cell.setCellValue("사업자번호");
		cell = row.createCell(23);
		cell.setCellValue("종사업장번호");
		cell = row.createCell(24);
		cell.setCellValue("대표자");
		cell = row.createCell(25);
		cell.setCellValue("계산서담당");
		cell = row.createCell(26);
		cell.setCellValue("업태");
		cell = row.createCell(27);
		cell.setCellValue("종목");
		cell = row.createCell(28);
		cell.setCellValue("주소2");
		cell = row.createCell(29);
		cell.setCellValue("상세주소2");
		cell = row.createCell(30);
		cell.setCellValue("계산서전화");
		cell = row.createCell(31);
		cell.setCellValue("계산서핸드폰");
		cell = row.createCell(32);
		cell.setCellValue("계산서이메일");
		cell = row.createCell(33);
		cell.setCellValue("매출중지");
		cell = row.createCell(34);
		cell.setCellValue("비고");
		cell = row.createCell(35);
		cell.setCellValue("행사제외");
		cell = row.createCell(36);
		cell.setCellValue("폐업여부");
		cell = row.createCell(37);
		cell.setCellValue("폐업일");

		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition","attachment;filename=convertCompany.xlsx");
		
		wb.write(response.getOutputStream());
		wb.close();
	}
	
	/**
	 *  상품 검증 양식을 다운로드 한다.
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/convert/convertGoodsDownload.do")
	public void convertGoodsDownload(HttpServletResponse response, String goodsType) throws Exception {
		
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("상품정보입력");
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("관리번호");
		cell = row.createCell(1);
		cell.setCellValue("기준코드");
		cell = row.createCell(2);
		cell.setCellValue("단위코드");
		cell = row.createCell(3);
		cell.setCellValue("바코드");
		cell = row.createCell(4);
		cell.setCellValue("품번");
		cell = row.createCell(5);
		cell.setCellValue("품번2");
		cell = row.createCell(6);
		cell.setCellValue("품번3");
		cell = row.createCell(7);
		cell.setCellValue("품번4");
		cell = row.createCell(8);
		cell.setCellValue("책자코드");
		cell = row.createCell(9);
		cell.setCellValue("품명");
		cell = row.createCell(10);
		cell.setCellValue("품명2");
		cell = row.createCell(11);
		cell.setCellValue("규격");
		cell = row.createCell(12);
		cell.setCellValue("단위");
		cell = row.createCell(13);
		cell.setCellValue("단위수량");
		cell = row.createCell(14);
		cell.setCellValue("상품그룹");
		cell = row.createCell(15);
		cell.setCellValue("분류");
		cell = row.createCell(16);
		cell.setCellValue("매입사");
		cell = row.createCell(17);
		cell.setCellValue("제조사");
		cell = row.createCell(18);
		cell.setCellValue("매입가");
		cell = row.createCell(19);
		cell.setCellValue("실원가");
		cell = row.createCell(20);
		cell.setCellValue("소비자가");
		cell = row.createCell(21);
		cell.setCellValue("판매가1");
		cell = row.createCell(22);
		cell.setCellValue("판매가2");
		cell = row.createCell(23);
		cell.setCellValue("판매가3");
		cell = row.createCell(24);
		cell.setCellValue("판매가4");
		cell = row.createCell(25);
		cell.setCellValue("판매가5");
		cell = row.createCell(26);
		cell.setCellValue("판매가6");
		cell = row.createCell(27);
		cell.setCellValue("판매가7");
		cell = row.createCell(28);
		cell.setCellValue("판매가8");
		cell = row.createCell(29);
		cell.setCellValue("판매가9");
		cell = row.createCell(30);
		cell.setCellValue("판매가10");
		cell = row.createCell(31);
		cell.setCellValue("판매가11");
		cell = row.createCell(32);
		cell.setCellValue("판매가12");
		cell = row.createCell(33);
		cell.setCellValue("판매가13");
		cell = row.createCell(34);
		cell.setCellValue("판매가14");
		cell = row.createCell(35);
		cell.setCellValue("단종");
		cell = row.createCell(36);
		cell.setCellValue("단종일자");
		cell = row.createCell(37);
		cell.setCellValue("원산지");
		cell = row.createCell(38);
		cell.setCellValue("비고");
		cell = row.createCell(39);
		cell.setCellValue("등록일");
		cell = row.createCell(40);
		cell.setCellValue("품목그룹1");
		cell = row.createCell(41);
		cell.setCellValue("품목그룹2");
		cell = row.createCell(42);
		cell.setCellValue("품목그룹3");
		cell = row.createCell(43);
		cell.setCellValue("품목그룹4");
		cell = row.createCell(44);
		cell.setCellValue("품목그룹5");
		cell = row.createCell(45);
		cell.setCellValue("단위구분");
		
		/*
		if(goodsType.equals("basicCode") || goodsType.equals("unitCode")) {
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("관리번호");
			cell = row.createCell(1);
			cell.setCellValue("기준코드");
			cell = row.createCell(2);
			cell.setCellValue("바코드");
			cell = row.createCell(3);
			cell.setCellValue("품번");
			cell = row.createCell(4);
			cell.setCellValue("품번2");
			cell = row.createCell(5);
			cell.setCellValue("품번3");
			cell = row.createCell(6);
			cell.setCellValue("품번4");
			cell = row.createCell(7);
			cell.setCellValue("책자코드");
			cell = row.createCell(8);
			cell.setCellValue("상품그룹");
			cell = row.createCell(9);
			cell.setCellValue("품명");
			cell = row.createCell(10);
			cell.setCellValue("품명2");
			cell = row.createCell(11);
			cell.setCellValue("규격");
			cell = row.createCell(12);
			cell.setCellValue("단위");
			cell = row.createCell(13);
			cell.setCellValue("단위수량");
			cell = row.createCell(14);
			cell.setCellValue("내입수량");
			cell = row.createCell(15);
			cell.setCellValue("매입가");
			cell = row.createCell(16);
			cell.setCellValue("실원가");
			cell = row.createCell(17);
			cell.setCellValue("매입DC");
			cell = row.createCell(18);
			cell.setCellValue("소비자가");
			cell = row.createCell(19);
			cell.setCellValue("판매가1");
			cell = row.createCell(20);
			cell.setCellValue("판매가2");
			cell = row.createCell(21);
			cell.setCellValue("판매가3");
			cell = row.createCell(22);
			cell.setCellValue("판매가4");
			cell = row.createCell(23);
			cell.setCellValue("판매가5");
			cell = row.createCell(24);
			cell.setCellValue("판매가6");
			cell = row.createCell(25);
			cell.setCellValue("판매가7");
			cell = row.createCell(26);
			cell.setCellValue("판매가8");
			cell = row.createCell(27);
			cell.setCellValue("판매가9");
			cell = row.createCell(28);
			cell.setCellValue("판매가10");
			cell = row.createCell(29);
			cell.setCellValue("판매가11");
			cell = row.createCell(30);
			cell.setCellValue("판매가12");
			cell = row.createCell(31);
			cell.setCellValue("판매가13");
			cell = row.createCell(32);
			cell.setCellValue("판매가14");
			cell = row.createCell(33);
			cell.setCellValue("분류");
			cell = row.createCell(34);
			cell.setCellValue("비고");
		} else if(goodsType.equals("detailCode")) {
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("관리번호");
			cell = row.createCell(1);
			cell.setCellValue("매입사");
			cell = row.createCell(2);
			cell.setCellValue("제조사");
			cell = row.createCell(3);
			cell.setCellValue("품목그룹1");
			cell = row.createCell(4);
			cell.setCellValue("품목그룹2");
			cell = row.createCell(5);
			cell.setCellValue("품목그룹3");
			cell = row.createCell(6);
			cell.setCellValue("품목그룹4");
			cell = row.createCell(7);
			cell.setCellValue("품목그룹5");
			cell = row.createCell(8);
			cell.setCellValue("B2C");
			cell = row.createCell(9);
			cell.setCellValue("단종");
			cell = row.createCell(10);
			cell.setCellValue("단종일자");
		} else if(goodsType.equals("allCode")) {
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("관리번호");
			cell = row.createCell(1);
			cell.setCellValue("기준코드");
			cell = row.createCell(2);
			cell.setCellValue("단위코드");
			cell = row.createCell(3);
			cell.setCellValue("바코드");
			cell = row.createCell(4);
			cell.setCellValue("품번");
			cell = row.createCell(5);
			cell.setCellValue("품번1");
			cell = row.createCell(6);
			cell.setCellValue("품번2");
			cell = row.createCell(7);
			cell.setCellValue("품번3");
			cell = row.createCell(8);
			cell.setCellValue("품번4");
			cell = row.createCell(9);
			cell.setCellValue("책자코드");
			cell = row.createCell(10);
			cell.setCellValue("상품그룹");
			cell = row.createCell(11);
			cell.setCellValue("상품명");
			cell = row.createCell(12);
			cell.setCellValue("상품명2");
			cell = row.createCell(13);
			cell.setCellValue("이미지저장URL");
			cell = row.createCell(14);
			cell.setCellValue("이미지저장URL2");
			cell = row.createCell(15);
			cell.setCellValue("규격");
			cell = row.createCell(16);
			cell.setCellValue("단위");
			cell = row.createCell(17);
			cell.setCellValue("단위수량");
			cell = row.createCell(18);
			cell.setCellValue("매입사");
			cell = row.createCell(19);
			cell.setCellValue("제조사");
			cell = row.createCell(20);
			cell.setCellValue("매입분류");
			cell = row.createCell(21);
			cell.setCellValue("브랜드");
			cell = row.createCell(22);
			cell.setCellValue("내입수량");
			cell = row.createCell(23);
			cell.setCellValue("매입가");
			cell = row.createCell(24);
			cell.setCellValue("실원가");
			cell = row.createCell(25);
			cell.setCellValue("매입DC");
			cell = row.createCell(26);
			cell.setCellValue("소비자가");
			cell = row.createCell(27);
			cell.setCellValue("판매가1");
			cell = row.createCell(28);
			cell.setCellValue("판매가2");
			cell = row.createCell(29);
			cell.setCellValue("판매가3");
			cell = row.createCell(30);
			cell.setCellValue("판매가4");
			cell = row.createCell(31);
			cell.setCellValue("판매가5");
			cell = row.createCell(32);
			cell.setCellValue("판매가6");
			cell = row.createCell(33);
			cell.setCellValue("판매가7");
			cell = row.createCell(34);
			cell.setCellValue("판매가8");
			cell = row.createCell(35);
			cell.setCellValue("판매가9");
			cell = row.createCell(36);
			cell.setCellValue("판매가10");
			cell = row.createCell(37);
			cell.setCellValue("판매가11");
			cell = row.createCell(38);
			cell.setCellValue("판매가12");
			cell = row.createCell(39);
			cell.setCellValue("판매가13");
			cell = row.createCell(40);
			cell.setCellValue("판매가14");
			cell = row.createCell(41);
			cell.setCellValue("품목그룹1");
			cell = row.createCell(42);
			cell.setCellValue("품목그룹2");
			cell = row.createCell(43);
			cell.setCellValue("품목그룹3");
			cell = row.createCell(44);
			cell.setCellValue("품목그룹4");
			cell = row.createCell(45);
			cell.setCellValue("품목그룹5");
			cell = row.createCell(46);
			cell.setCellValue("분류");
			cell = row.createCell(47);
			cell.setCellValue("원산지");
			cell = row.createCell(48);
			cell.setCellValue("입고날짜");
			cell = row.createCell(49);
			cell.setCellValue("단종여부");
			cell = row.createCell(50);
			cell.setCellValue("단종날짜");
			cell = row.createCell(51);
			cell.setCellValue("담당자");
			cell = row.createCell(52);
			cell.setCellValue("비고");
			cell = row.createCell(53);
			cell.setCellValue("단위구분");
		}
		
		response.setContentType("ms-vnd/excel");
		
		if(goodsType.equals("basicCode")) {
			response.setHeader("Content-Disposition","attachment;filename=convertGoodsBasicCode.xlsx");	
		} else if(goodsType.equals("unitCode")) {
			response.setHeader("Content-Disposition","attachment;filename=convertGoodsUnitCode.xlsx");
		} else if(goodsType.equals("detailCode")) {
			response.setHeader("Content-Disposition","attachment;filename=convertGoodsDetailCode.xlsx");
		} else if(goodsType.equals("allCode")) {
			response.setHeader("Content-Disposition","attachment;filename=convertGoodsAllCode.xlsx");
		}
		*/
		
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition","attachment;filename=convertGoodsCode.xlsx");
		
		wb.write(response.getOutputStream());
		wb.close();
	}

	@RequestMapping(value="/convert/convertCheck.do")
	public String convertCheck() throws Exception {
		return "convert/convertCheck";
	}
	
	/**
	 *  거래처 검증 리스트.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/convert/convertCompanyResult.do")
	public ModelAndView convertComapnyResult() throws Exception {
		ModelAndView mv = new ModelAndView("convert/convertCompanyResult");
		
		List<ConvertDto> list = convertService.selectConvertCompany();
		
		mv.addObject("list", list);
		
		return mv;
	}
	
	/**
	 * 상품 검증 리스트.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/convert/convertGoodsResult.do")
	public ModelAndView convertGoodsResult() throws Exception {
		ModelAndView mv = new ModelAndView("convert/convertGoodsResult");
		
		String category = "";
		
		List<ConvertDto> list = convertService.selectConvertGoods();
		
		for(int i=0; i<list.size(); i++) {
			BasicDto basicDto = new BasicDto();
			
			String categoryTotal = "";
			
			String verify = "";
			
			category = list.get(i).getConvertGoodsCategory();
			categoryTotal = basicController.convertCategory(category);
			
			basicDto.setItemGroupOneName(list.get(i).getConvertGoodsItemGroup1());
			basicDto.setItemGroupTwoName(list.get(i).getConvertGoodsItemGroup2());
			basicDto.setItemGroupThreeName(list.get(i).getConvertGoodsItemGroup3());
			basicDto.setItemGroupFourName(list.get(i).getConvertGoodsItemGroup4());
			basicDto.setItemGroupFiveName(list.get(i).getConvertGoodsItemGroup5());
			
			String itemGroupCodeTotal = itemGroupCodeTotal(basicDto);
			
			if(categoryTotal.contains("검증")) {
				verify = categoryTotal;
			}
			
			if(itemGroupCodeTotal.contains("검증")) {
				verify = verify + " / " + itemGroupCodeTotal;
			}
			
			if(verify.contains("검증")) {
				list.get(i).setConvertVerify(verify);
			}
			
		}
		
		mv.addObject("list", list);
		
		return mv;
	}
	
	/**
	 * 품목그룹 검증.
	 * @param basicDto
	 * @return
	 * @throws Exception
	 */
	public String itemGroupCodeTotal(BasicDto basicDto) throws Exception {
		
		String codeTotal = "";
		String codeOne = "";
		String codeTwo = "";
		String codeThree = "";
		String codeFour = "";
		String codeFive = "";
		
		List<BasicDto> oneList = basicController.itemGroupOneList(basicDto);
		List<BasicDto> twoList = basicController.itemGroupTwoList(basicDto);
		List<BasicDto> threeList = basicController.itemGroupThreeList(basicDto);
		List<BasicDto> fourList = basicController.itemGroupFourList(basicDto);
		List<BasicDto> fiveList = basicController.itemGroupFiveList(basicDto);
		
		codeOne = oneList.get(0).getAdminCode();
		codeTwo = twoList.get(0).getAdminCode();
		codeThree = threeList.get(0).getAdminCode();
		codeFour = fourList.get(0).getAdminCode();
		codeFive = fiveList.get(0).getAdminCode();
		
		codeTotal = codeOne + "|" + codeTwo + "|" + codeThree + "|" + codeFour + "|" + codeFive;
		
		if(codeTotal.contains("없음")) {
			codeTotal = "품목그룹 검증";
		}
		
		return codeTotal;
	}
	
	/**
	 * 상품 검증 디테일.
	 * @param convertDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/convert/convertGoodsDetails.do")
	public ModelAndView convertGoodsDetails(int seq) throws Exception {
		
		ModelAndView mv = new ModelAndView("convert/convertGoodsDetails");
		
		BasicDto basicDto = new BasicDto();
		
		ConvertDto convertResult = convertService.selectConvertGoodsDetails(seq);
		
		List<BasicDto> itemGroupOneList = basicController.categoryOneList(basicDto);
		List<BasicDto> itemGroupTwoList = basicController.categoryTwoList(basicDto);
		List<BasicDto> itemGroupThreeList = basicController.categoryThreeList(basicDto);
		List<BasicDto> itemGroupFourList = basicController.categoryFourList(basicDto);
		
		mv.addObject("convert",convertResult);
		mv.addObject("itemOneList", itemGroupOneList);
		mv.addObject("itemTwoList", itemGroupTwoList);
		mv.addObject("itemThreeList", itemGroupThreeList);
		mv.addObject("itemFourList", itemGroupFourList);
		
		return mv;
	}
	
}
