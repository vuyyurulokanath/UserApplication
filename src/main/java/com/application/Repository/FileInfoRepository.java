package com.application.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;



import com.application.Entity.FileInfo;
import com.application.Enum.Status;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

	List<FileInfo> findByuserid(long userid);

	List<FileInfo> findByuserid_idAndStatus(long userid, Status status);

	FileInfo findByfilename(String filename);
}
