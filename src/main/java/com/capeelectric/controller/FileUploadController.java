package com.capeelectric.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.IOUtils;
import com.capeelectric.model.ResponseFile;
import com.capeelectric.service.FileStorageService;

@RestController
@RequestMapping("/api/emc/v1")
public class FileUploadController<V> {
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
		logger.debug("DownloadFile File Start emcId : {}", emcId);
		ResponseFile fileDB = storageService.downloadFile(emcId);
		response.setHeader("Content-Disposition", "inline; fileDB.getfileId()=\"" + fileDB.getFileId() + "\"");
		OutputStream out = response.getOutputStream();
		response.setContentType(fileDB.getFileName());
		IOUtils.copy(fileDB.getData().getBinaryStream(), out);
		out.flush();
		out.close();
		return null;

	}

	@GetMapping("/retrieveFileName/{emcId}")
	public ResponseEntity<Map> retrieveFileNameByEmcId(@PathVariable Integer emcId) throws IOException, SQLException {
		logger.debug("Retrieve File Start emcId : {}", emcId);
		ResponseFile fileDB = storageService.retrieveFileNameByEmcId(emcId);
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("fileId", fileDB.getFileId().toString());
		hashMap.put("fileType", fileDB.getFileType());
		hashMap.put("fileEmcId", fileDB.getEmcId().toString());
		hashMap.put("fileName", fileDB.getFileName());
		return new ResponseEntity<Map>(hashMap, HttpStatus.OK);
	}

	@PutMapping("/updateFile/{fileId}")
	public ResponseEntity<String> updateFile(@RequestParam("file") MultipartFile file, @PathVariable Integer fileId)
			throws IOException, SerialException, SQLException {
		logger.debug("UpdateFile File Start");
		storageService.updateFile(file, fileId);
		logger.debug("UpdateFile File End");
		return new ResponseEntity<String>("File Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/removeFile/{emcId}")
	public ResponseEntity<String> removeFile(@PathVariable Integer emcId) throws IOException {
		logger.debug("Remove File Start");
		storageService.removeFile(emcId);
		logger.debug("Remove File End");
		return new ResponseEntity<String>("File  Deleted Successfully", HttpStatus.OK);
	}
}
