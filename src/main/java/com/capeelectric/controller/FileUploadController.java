package com.capeelectric.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.IOUtils;
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
			throws IOException, SerialException, SQLException {
		logger.debug("File Upload Start");
		storageService.store(file, emcId);
		logger.debug("File Upload 	End");
		return new ResponseEntity<String>("File  Upload Successfully", HttpStatus.OK);
	}

	@GetMapping("/downloadFile/{emcId}")
	public ResponseEntity<String> downloadFile(@PathVariable Integer emcId, HttpServletResponse response)
			throws IOException, SQLException {
		logger.debug("Retrieve File Start emcId : {}", emcId);
		ResponseFile fileDB = storageService.downloadFile(emcId);
		response.setHeader("Content-Disposition", "inline; fileDB.getFileName()=\"" + fileDB.getFileName() + "\"");
		OutputStream out = response.getOutputStream();
		response.setContentType(fileDB.getFileName());
		IOUtils.copy(fileDB.getData().getBinaryStream(), out);
		out.flush();
		out.close();
		return null;

	}

	@DeleteMapping("/removeFile/{emcId}")
	public ResponseEntity<String> removeFile(@PathVariable Integer emcId) throws IOException {
		logger.debug("Remove File Start");
		storageService.removeFile(emcId);
		logger.debug("Remove File End");
		return new ResponseEntity<String>("File  Deleted Successfully", HttpStatus.OK);
	}
}
