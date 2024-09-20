package com.easy.mongo.config;

import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * @author muchi
 */
@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    /**
     * 配置 MongoDB 数据库工厂
     * @return MongoDatabaseFactory 实例
     */
    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(MongoClients.create(mongoUri), "filedb");
    }

    /**
     * 配置 MongoTemplate
     * @return MongoTemplate 实例
     */
    @Bean
    public MongoTemplate mongoTemplate() {
        MappingMongoConverter converter = new MappingMongoConverter(mongoDatabaseFactory(), new MongoMappingContext());
        // 移除 _class 字段
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        converter.afterPropertiesSet();
        return new MongoTemplate(mongoDatabaseFactory(), converter);
    }

    /**
     * 配置 GridFsTemplate
     * @return GridFsTemplate 实例
     */
    @Bean
    public GridFsTemplate gridFsTemplate() {
        return new GridFsTemplate(mongoDatabaseFactory(), mongoTemplate().getConverter());
    }
}
