package com.easy.mongo.controller;

import com.easy.mongo.pojo.FileMetaData;
import com.easy.mongo.service.FileStorageService;
import com.easy.web.annotation.Wrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author muchi
 */
@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {

    private final FileStorageService fileStorageService;

    @Autowired
    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    /**
     * 上传文件接口
     * @param file 要上传的文件
     * @return 文件的元数据
     */
    @PostMapping("/upload")
    public ResponseEntity<FileMetaData> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileMetaData metaData = fileStorageService.uploadFile(file);
            return ResponseEntity.ok(metaData);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * 下载文件接口
     * @param id 文件的ID
     * @return 文件的二进制数据
     */
    @Wrap(disabled=true)
    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String id) {
        try {
            // 获取文件元数据
            FileMetaData metaData = fileStorageService.getFileMetaData(id);
            if (metaData == null) {
                return ResponseEntity.notFound().build();
            }

            InputStream inputStream = fileStorageService.downloadFile(id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metaData.getFilename() + "\"")
                    .contentType(MediaType.parseMediaType(metaData.getContentType()))
                    .contentLength(metaData.getSize())
                    .body(new InputStreamResource(inputStream));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * 列出所有上传的文件
     * @return 文件元数据列表
     */
    @GetMapping("/list")
    public ResponseEntity<List<FileMetaData>> listAllFiles() {
        List<FileMetaData> files = fileStorageService.getAllFileMetaData();
        return ResponseEntity.ok(files);
    }
}
