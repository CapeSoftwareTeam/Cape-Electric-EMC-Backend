package com.capeelectric.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.capeelectric.model.ResponseFile;
import com.capeelectric.repository.FileDBRepository;
import com.capeelectric.service.impl.FacilityDataServiceImpl;

@Service
public class FileStorageService {
	private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

	@Autowired
	private FileDBRepository fileDBRepository;

	public ResponseFile store(MultipartFile file, Integer emcId) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		ResponseFile FileDB = new ResponseFile();
		FileDB.setEmcId(emcId);
		FileDB.setFileName(fileName);
		FileDB.setData(file.getBytes());
		FileDB.setFileType(file.getContentType());
		logger.debug("File Saved In DB");
		return fileDBRepository.save(FileDB);
	}

	public ResponseFile getFile(Integer emcid) {
		return fileDBRepository.findByEmcId(emcid).get();

	}

}
