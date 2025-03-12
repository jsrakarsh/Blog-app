  package com.akarsh.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.akarsh.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
	    // Validate path (ensure it's not null)
	    if (path == null || path.trim().isEmpty()) {
	        throw new IOException("Upload path is not specified");
	    }

	    // Generate a unique file name
	    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	    
	    // Ensure the directory exists
	    File uploadDir = new File(path);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdirs(); // Create the directory if it doesn’t exist
	    }

	    // Create a file instance at the specified path
	    File destinationFile = new File(uploadDir, fileName);

	    // Save the file to disk
	    file.transferTo(destinationFile);

	    return fileName;
	}



	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
	    
	    // Construct full file path
	    String fullPath = path + File.separator + fileName;
	    
	    // Create InputStream for the file
	    InputStream is = new FileInputStream(fullPath);
	    
	    return is;
	}



}
