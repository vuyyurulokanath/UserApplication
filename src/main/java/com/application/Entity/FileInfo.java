package com.application.Entity;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.application.Enum.Status;

@Entity
@Table(name = "file_info")
public class FileInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "filename")
	private String filename;
	@ManyToOne
	private UserDetailsInfo userid;
	@Enumerated(EnumType.ORDINAL)
    private Status status;
	@Column(name="created_time")
	private Date createddate;
	@Column(name="update_time")
	private Date updatedate;
	@PrePersist
	protected void onCreate() {
		createddate = new Date();
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public UserDetailsInfo getUserid() {
		return userid;
	}

	public void setUserid(UserDetailsInfo userid) {
		this.userid = userid;
	}

	
	
	

}
