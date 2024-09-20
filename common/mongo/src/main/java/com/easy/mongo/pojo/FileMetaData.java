package com.easy.mongo.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author muchi
 */
@Document(collection = "files")
@Data
public class FileMetaData {
    @Id
    private String id;
    private String filename;
    private String contentType;
    private long size;
}
