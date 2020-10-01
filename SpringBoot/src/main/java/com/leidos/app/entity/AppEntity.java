package com.leidos.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "team")
public class AppEntity {
	public AppEntity() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "member")
	private String member;
	
	@Column(name = "email")
	private String email;

	@Column(name = "bi")
	private boolean bi;

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", member=" + member + ", email=" + email + ", bi=" + bi + "]";
	}

	public AppEntity(long id, String name, String member, String email, boolean bi) {
		this.id = id;
		this.name = name;
		this.member = member;
		this.email = email;
		this.bi = bi;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isBi() {
		return bi;
	}

	public void setBi(boolean bi) {
		this.bi = bi;
	}
}
