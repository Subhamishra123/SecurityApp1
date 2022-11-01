package com.nt.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;
@Data
@Table(name = "security_users")
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer unid;
	@Column(length = 20,nullable = false)
	private String uname;
	@Column(length = 150,nullable = false)
	private String pwd;
	@Column(length = 30,nullable = false)
	private String email;
	
	private Boolean enable=true;
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "role")
	@CollectionTable(name = "security_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "unid"))
	private Set<String> roles;

}
