package com.acruent.mobile.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "mobiles_data")
public class Mobile implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mobile_id")
	private Long id;
	@NotEmpty(message = "Enter A Valid Mobile Name")
	@Size(min = 4, max = 30, message = "Mobile Name Must And Should Be Between 4 To 30")
	@Column(name = "mobile_name")
	private String name;

	@NotNull(message = "Enter Mobile Price Is Mandatory")
	@Min(value = 1000, message = "Mobile Starting Price Is 1000")
	@Max(value = 10000000, message = "Mobile Fainal Price Is 10000000")
	@Column(name = "mobile_price")
	private Double price;

	@NotNull(message = "Enter Mobile Released Year Is Madatory")
	@Column(name = "mobile_lunch_year")
	private Integer year;
	@JsonManagedReference
	@OneToMany(mappedBy = "mobile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Sim> sim;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Sim> getSim() {
		return sim;
	}

	public void setSim(List<Sim> sim) {
		this.sim = sim;
	}

	@Override
	public String toString() {
		return "Mobile [id=" + id + ", name=" + name + ", price=" + price + ", year=" + year + ", sim=" + sim + "]";
	}

}
