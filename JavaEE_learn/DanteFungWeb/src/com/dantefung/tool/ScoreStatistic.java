package com.dantefung.tool;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoreStatistic {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) {

		try 
		{
			BufferedReader br_own_ans = new BufferedReader(new FileReader("F:\\传智播客\\Fung.txt"));
			BufferedReader br_st_ans = new BufferedReader(new FileReader("F:\\传智播客\\JavaEE就业班入学测试答案.txt"));
			
			String st_ans = null;
			String own_ans = null;
			int count = 0;
			int num = 0;
			List<Integer> wrong_subjects = new ArrayList<Integer>();
		
			while((st_ans = br_st_ans.readLine()) != null && (own_ans = br_own_ans.readLine()) != null)
			{
				++ num;
				
				System.out.println(num + "、 STANDARD ANSWER IS: " + st_ans.trim() +" ---- "+ " MY ANSWER IS: " + own_ans.trim());
				if(st_ans.trim().equalsIgnoreCase(own_ans.trim()))
				{
					count ++;
				}
				else
				{
				    	wrong_subjects.add(num);
				}
			
			}
			
			System.out.println("YOU HAVE " + count + " SUBJECTS CORRECT!!");
			
			System.out.println();
			for(int temp : wrong_subjects)
			{
				System.out.print( temp + " 、 ");
			}
		} 
		catch (IOException e) 
		{
			throw new RuntimeException(e);
		}
	}

}
