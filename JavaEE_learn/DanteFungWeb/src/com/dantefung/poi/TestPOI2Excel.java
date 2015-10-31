package com.dantefung.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOI2Excel {

	@Test
	public void write03Excel() throws Exception {
		
		//1、创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//2、创建工作表
		HSSFSheet sheet = workbook.createSheet("Hello World");
		//3、创建行；创建第三行
		HSSFRow row = sheet.createRow(2);
		//4、创建单元格；创建第三行第三列
		HSSFCell cell = row.createCell(2);
		cell.setCellValue("Hello World");
		
		FileOutputStream fileOutputStream = new FileOutputStream("D:\\itcast\\测试.xls");
		workbook.write(fileOutputStream);
		workbook.close();
		fileOutputStream.close();
	}
	
	@Test
	public void read03Excel() throws Exception {
		FileInputStream fileInputStream = new FileInputStream("D:\\itcast\\测试.xls");
		
		//1、读取工作簿
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		//2、读取工作表
		HSSFSheet sheet = workbook.getSheetAt(0);
		//3、读取行；读取第三行
		HSSFRow row = sheet.getRow(2);
		//4、读取单元格；读取第三行第三列
		HSSFCell cell = row.getCell(2);
		System.out.println("第三行第三列的单元格内容为：" + cell.getStringCellValue());
		
		workbook.close();
		fileInputStream.close();
	}
	
	@Test
	public void write07Excel() throws Exception {
		
		//1、创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		//2、创建工作表
		XSSFSheet sheet = workbook.createSheet("Hello World");
		//3、创建行；创建第三行
		XSSFRow row = sheet.createRow(2);
		//4、创建单元格；创建第三行第三列
		XSSFCell cell = row.createCell(2);
		cell.setCellValue("Hello World");
		
		FileOutputStream fileOutputStream = new FileOutputStream("D:\\itcast\\测试.xlsx");
		workbook.write(fileOutputStream);
		workbook.close();
		fileOutputStream.close();
	}
	
	@Test
	public void read07Excel() throws Exception {
		FileInputStream fileInputStream = new FileInputStream("D:\\itcast\\测试.xlsx");
		
		//1、读取工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		//2、读取工作表
		XSSFSheet sheet = workbook.getSheetAt(0);
		//3、读取行；读取第三行
		XSSFRow row = sheet.getRow(2);
		//4、读取单元格；读取第三行第三列
		XSSFCell cell = row.getCell(2);
		System.out.println("第三行第三列的单元格内容为：" + cell.getStringCellValue());
		
		workbook.close();
		fileInputStream.close();
	}
	
	@Test
	public void testExcelStyle() throws Exception {
		
		//1、创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//1.1 创建合并单元格对象（起始行号，结束行号，起始列号，结束列号）；合并第3行第3列到第5列
		CellRangeAddress cellRangeAddress = new CellRangeAddress(2,2,2,4);
		//1.2创建单元格样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//1.3创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗
		font.setFontHeightInPoints((short)16);
		//在样式中加载字体
		cellStyle.setFont(font);
		
		//设置单元格背景
		//设置填充模式
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		cellStyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
		
		//2、创建工作表
		HSSFSheet sheet = workbook.createSheet("Hello World");
		//2.1 加载合并单元格对象
		sheet.addMergedRegion(cellRangeAddress);
		
		//3、创建行；创建第三行
		HSSFRow row = sheet.createRow(2);
		//4、创建单元格；创建第三行第三列
		HSSFCell cell = row.createCell(2);
		//加载单元格样式
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Hello World");
		
		FileOutputStream fileOutputStream = new FileOutputStream("D:\\itcast\\测试.xls");
		workbook.write(fileOutputStream);
		workbook.close();
		fileOutputStream.close();
	}


}
