package com.capeelectric.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.capeelectric.model.ResponseFile;
import com.capeelectric.repository.FileDBRepository;

@Service
public class FileStorageService {

	@Autowired
	private FileDBRepository fileDBRepository;

	public ResponseFile store(MultipartFile file, Integer emcId) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		ResponseFile FileDB = new ResponseFile();
		FileDB.setEmcId(emcId);
		FileDB.setFileName(fileName);
		FileDB.setData(file.getBytes());
		FileDB.setFileType(file.getContentType());
		return fileDBRepository.save(FileDB);
	}

	public ResponseFile getFile(Integer emcid) {
		return fileDBRepository.findByEmcId(emcid).get();

	}

}
