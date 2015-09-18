package com.adantefung.example;


/**
 * 学生Dao接口（定义了操作的标准）
 *   先接口，再写实现  （ 为了更好的扩展 ）
 * @author APPle
 *
 */
public interface StudentDao {
	/**
	 * 添加学生信息
	 * @param student  需要添加的学生对象
	 * @return 如果添加成功则返回true，否则返回false
	 */
	public boolean addStudent(Student student);
	
	/**
	 * 根据准考证号查询对应学生信息
	 * @param examid  需要查询的准考证号
	 * @return 如果查询到对应的数据，则返回对应的学生对象，否则返回null
	 */
	public Student find(String examid);
	
	/**
	 * 根据姓名删除学生
	 * @param name  需要删除的学生姓名
	 * @return  如果删除成功则返回true，否则返回false
	 */
	public boolean delStudent(String name);
	
	
	
}
