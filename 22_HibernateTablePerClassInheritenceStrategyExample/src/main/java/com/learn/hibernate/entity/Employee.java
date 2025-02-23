package com.learn.hibernate.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.omg.CORBA.INV_FLAG;

@Entity
@Table(name="employee_table")
public class Employee extends Person{

	@Override
	public String toString() {
		return "Employee [salary=" + salary + ", doj=" + doj + ", deptName=" + deptName + ", bonus=" + bonus
				+ ", email=" + email + super.toString()+"]";
	}

	@Column(name="salary",columnDefinition = "DECIMAL(7,2)")
	private Double salary;
	
	@Column(name="date_of_joing")
	private Date doj;
	
	@Column(name="dept_name",length = 30)
	private String deptName;
	
	@Column(name="bonus",precision = 6,scale = 3)
	private BigDecimal bonus;
	
	@Column(name="email",length = 30,unique = true)
	private String email;
	
	public Employee() {
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}