package com.acruent.mobile.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sims_names")
public class Sim implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sim_id")
	private Long id;

	@NotNull(message = "Sim Name Must And Should Be Enter")
	@Size(min = 1, max = 20, message = "Sim Name Must And Should Be Between 1 to 20")
	@Column(name = "sim_name")
	private String name;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "mobile_id")
	private Mobile mobile;

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

	public Mobile getMobile() {
		return mobile;
	}

	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Sim [id=" + id + ", name=" + name + ", mobile=" + mobile + "]";
	}

}
