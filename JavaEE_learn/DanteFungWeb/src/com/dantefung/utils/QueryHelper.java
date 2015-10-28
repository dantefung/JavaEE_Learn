package com.dantefung.utils;

import java.util.ArrayList;
import java.util.List;


public class QueryHelper {
	//from 子句                        
	private String fromClause = "";			
	//where 子句
	private String whereClause = "";
	
	private List<Object> parameters;
	
	//排序序列常量
	public final static String ORDER_BY_ASC = "ASC";//升序
	public final static String ORDER_BY_DESC = "DESC";//降序
	

	public QueryHelper(String fromClause){
		this.fromClause = fromClause;
	}
	
	
	
	/**
	 * 构造where 子句
	 * @param condition 查询条件；如 i.title like ?
	 * @param params 查询条件中?对应的查询条件值
	 */
	public void addCondition(String condition, Object... params){
		if (whereClause.length() > 0) {
			whereClause += " AND " + condition;
		} else {
			whereClause = " WHERE " + condition;
		}
		
		//添加查询条件值
		if(params != null){
			if(parameters == null){
				parameters = new ArrayList<Object>();
			}
			for(Object param: params){
				parameters.add(param);
			}
		}
	}
	
	
	//返回hql查询语句
	public String getHql(){
		return fromClause + whereClause;
	}
	
	//返回查询总记录数的hql查询语句
	public String getCountHql(){
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}
	
	//查询hql语句中对应的?对应的查询条件值集合
	public List<Object> getParameters(){
		return parameters;
	}

}
