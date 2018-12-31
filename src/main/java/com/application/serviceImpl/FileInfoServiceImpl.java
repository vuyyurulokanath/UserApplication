package com.application.serviceImpl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Entity.FileInfo;
import com.application.Enum.Status;
import com.application.Repository.FileInfoRepository;
import com.application.Service.FileInfoService;
@Service
public class FileInfoServiceImpl implements FileInfoService{


	private FileInfoRepository fileimpl;

@Autowired
	public FileInfoServiceImpl(FileInfoRepository fileimpl) {
		super();
		this.fileimpl = fileimpl;
	}


	public List<FileInfo> findAll() {
		// TODO Auto-generated method stub
		return fileimpl.findAll();
	}

	
	public FileInfo findById(long id) {
		// TODO Auto-generated method stub
		return fileimpl.getOne(id);
	}


	public void deleteById(long id) {
		fileimpl.deleteById(id);
		
	}


	public FileInfo save(FileInfo file) {
		
		return fileimpl.save(file);
	}

	
	public List<FileInfo> findByuserid(long userid) {
		
		return fileimpl.findByuserid(userid);
	}

	public FileInfo findByfilename(String filename) {
		
		return fileimpl.findByfilename(filename);
	}


	@Override
	public List<FileInfo> findByuserIdAndStatus(long userid, Status status) {
		
		return fileimpl.findByuserid_idAndStatus(userid, status);
	}


	
}
