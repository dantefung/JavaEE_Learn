package com.dantefung.tools.excel;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * 打印表格
 * 
 * @author Dante Fung
 * 
 * 
 */
public class JxlExcel {
	/**
	 * 打印表格功能
	 * 
	 * @param path
	 *            储存表格的路径+名字
	 * @param title
	 *            表格头
	 * @param body
	 *            表格体
	 */
	public static void print(String path, Object[] title, Object[][] body) {
		// 查询表格头的sql语句：select column_name from information_schema.COLUMNS where
		// table_name='表名'
		// 创建文件夹
		File file = new File(path);
		try {
			file.createNewFile();
			// 创建工作簿
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 创建sheet
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			Label label = null;
			// 添加表格头
			for (int i = 0; i < title.length; i++) {
				label = new Label(i, 0,  (String) title[i]);// 往第n列第一行添加数据，形成表格头
				sheet.addCell(label);// 添加到sheet中去
			}
			// 追加数据
			for (int i = 1; i < body.length; i++) {
				for (int j = 0; j < body[i].length; j++) {

					label = new Label(j, i, (String) body[i][j]);// 第i列第j行
					sheet.addCell(label);
				}
			}
			// 写进表格中
			workbook.write();
			// 关闭流
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
