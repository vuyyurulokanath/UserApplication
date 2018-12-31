package com.application.Service;

import java.util.List;



import com.application.Entity.FileInfo;
import com.application.Enum.Status;

public interface FileInfoService {

	List<FileInfo> findAll();

  FileInfo findById(long id);

	void deleteById(long id);

	FileInfo save(FileInfo file);

	List<FileInfo> findByuserid(long userid);

     FileInfo findByfilename(String filename);

	List<FileInfo> findByuserIdAndStatus(long userid, Status status);
}
