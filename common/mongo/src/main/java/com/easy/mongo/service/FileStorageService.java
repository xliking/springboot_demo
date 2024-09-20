package com.easy.mongo.service;

import com.easy.mongo.pojo.FileMetaData;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author muchi
 */
@Service
public class FileStorageService {

    private final MongoTemplate mongoTemplate;
    private final GridFSBucket gridFSBucket;

    @Autowired
    public FileStorageService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.gridFSBucket = GridFSBuckets.create(mongoTemplate.getDb());
    }

    /**
     * 上传文件到MongoDB GridFS
     * @param file 要上传的文件
     * @return 文件的元数据
     * @throws IOException 如果文件读取失败
     */
    public FileMetaData uploadFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String contentType = file.getContentType();
        long size = file.getSize();

        ObjectId fileId = null;
        try (InputStream inputStream = file.getInputStream()) {
            if (filename != null) {
                fileId = gridFSBucket.uploadFromStream(filename, inputStream);
            }
        }
        FileMetaData metaData = new FileMetaData();
        if (fileId != null) {
            metaData.setId(fileId.toHexString());
        }
        metaData.setFilename(filename);
        metaData.setContentType(contentType);
        metaData.setSize(size);

        // 保存文件元数据到自定义集合
        mongoTemplate.save(metaData);

        return metaData;
    }

    /**
     * 下载文件
     * @param id 文件的ID
     * @return 文件的InputStream
     */
    public InputStream downloadFile(String id) {
        ObjectId objectId = new ObjectId(id);
        return gridFSBucket.openDownloadStream(objectId);
    }

    /**
     * 根据文件ID获取文件元数据
     * @param id 文件的ID
     * @return 文件元数据，如果不存在则返回null
     */
    public FileMetaData getFileMetaData(String id) {
        return mongoTemplate.findById(id, FileMetaData.class);
    }

    /**
     * 获取所有文件的元数据
     * @return 文件元数据列表
     */
    public List<FileMetaData> getAllFileMetaData() {
        return mongoTemplate.findAll(FileMetaData.class);
    }
}
