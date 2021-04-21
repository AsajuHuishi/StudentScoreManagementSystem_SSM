package indi.huishi.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 建立stu_score表相同的字段
 +-----------+-------------+------+-----+---------+----------------+
 | Field     | Type        | Null | Key | Default | Extra          |
 +-----------+-------------+------+-----+---------+----------------+
 | NAME      | varchar(20) | YES  |     | NULL    |                |
 | score     | float       | YES  |     | NULL    |                |
 | class_name| int(11)     | YES  |     | NULL    |                |
 | no        | varchar(20) | YES  | UNI | NULL    |                |
 | id        | int(11)     | NO   | PRI | NULL    | auto_increment |
 +-----------+-------------+------+-----+---------+----------------+
 * @author AsajuHuishi
 *
 */

public class Student {

	private Integer id;
	private String no;
	private String name;
	private Float score;
	private Integer className;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getClassName() {
		return className;
	}

	public void setClassName(Integer className) {
		this.className = className;
	}

	public Student(Integer id, String no, String name, Float score, Integer className) {
		this.id = id;
		this.no = no;
		this.name = name;
		this.score = score;
		this.className = className;
	}

	public Student() {
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", no='" + no + '\'' +
				", name='" + name + '\'' +
				", score=" + score +
				", className=" + className +
				'}';
	}
}
