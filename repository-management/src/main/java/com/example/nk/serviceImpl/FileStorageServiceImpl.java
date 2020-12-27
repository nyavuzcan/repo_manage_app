package com.example.nk.serviceImpl;

import com.example.nk.Exception.FileStorageException;
import com.example.nk.Config.FileStorageProperties;
import com.example.nk.Exception.MyFileNotFoundException;
import com.example.nk.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {

  private final Path fileStorageLocation;

  @Autowired
  public FileStorageServiceImpl(FileStorageProperties fileStorageProperties ) {
    this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath();
    try {
      Files.createDirectories(this.fileStorageLocation);

    }catch (Exception ex){
      throw new FileStorageException("Could not create the directory to upload");
    }
  }

  public String storeFile(MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    try {
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }catch (Exception e){
      throw new FileStorageException("Could not store file"+ fileName+". Please try again!");
    }
  }

  public Resource loadFileAsResource(String fileName) {
    try {
      Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
      Resource resource = new UrlResource(filePath.toUri());
      if (resource.exists()) {
        return resource;
      } else {
        throw new MyFileNotFoundException("File not found"+ fileName);
      }
    }catch (MalformedURLException ex){
      throw new MyFileNotFoundException ("File not found "+ fileName);
    }
  }
}
