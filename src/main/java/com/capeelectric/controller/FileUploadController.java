package com.capeelectric.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capeelectric.model.ResponseFile;
import com.capeelectric.service.FileStorageService;

@RestController
@RequestMapping("/api/emc/v1")
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	@Autowired
	private FileStorageService storageService;

	@PostMapping("/upload/{emcId}")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer emcId)
			throws IOException {
		logger.debug("File Upload Start");
		storageService.store(file, emcId);
		logger.debug("File Upload 	End");
		return new ResponseEntity<String>("File  Upload Successfully", HttpStatus.OK);
	}

	@GetMapping("/files/{emcid}")
	public ResponseEntity<byte[]> getFile(@PathVariable Integer emcid) {
		logger.debug("Retrieve File Start emcId : {}", emcid);
		ResponseFile fileDB = storageService.getFile(emcid);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"")
				.body(fileDB.getData());
	}
}
