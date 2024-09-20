# springboot_demo
# Spring Boot 多技术栈整合项目

## 项目简介

本项目是一个基于 Spring Boot 的多技术栈整合示例，集成了以下技术栈：

- **MySQL**：关系型数据库，用于持久化存储
- **Redis**：键值存储，用于缓存
- **MongoDB**：NoSQL 文档数据库
- **Elasticsearch**：分布式搜索和分析引擎
- **Swagger UI**：生成 RESTful API 文档
- **Spring Data JPA**：持久化层框架，操作 MySQL
- **Spring Data Redis**：操作 Redis
- **Spring Data MongoDB**：操作 MongoDB
- **Spring Data Elasticsearch**：操作 Elasticsearch

里面包含了各种实用的工具类,各种配置已经相对应完整
例如常见的:
    redis,已经封装为RedisUtils
    mongo,已实现现成的上传下载功能
    等等，这里就不一一列举了
项目旨在为开发者提供一个整合多种数据库和技术栈的脚手架，方便在实际项目中快速上手。

---

## 技术栈

- **Java 17** 或更高版本
- **Spring Boot 3.1.3** 或更高版本
- **MySQL 8.x**
- **Redisson 3.23.4**
- **MongoDB 5.x**
- **Elasticsearch 8.x**
- **Swagger OpenAPI 3**

---

## 环境要求

- **JDK 17** 或更高版本
- **Maven 3.6** 或更高版本
- **MySQL 数据库**
- **Redis 服务**
- **MongoDB 服务**
- **Elasticsearch 服务**
- **IDE**：推荐 IntelliJ IDEA

---

## 项目结构

```bash
springboot-demo
│
common
├──datasource
├──elasticsearch
├──framework
├──mongo
├──redis
├──utils
├──web
│
demo
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.easy.demo
│   │   │       ├── controller      # 控制器层
│   │   │       ├── service         # 服务层
│   │   │       ├── dao             # 数据访问层
│   │   │       ├── bean            # 实体类和模型
│   │   │       ├── config          # 配置类
│   │   │       └── DemoApplication.java # 主启动类
│   │   └── resources
│   │       ├── application.yml     # 主配置文件
│   │       └── application-dev.yml # 开发环境配置
│   └── test
│       └── java
│           └── com.easy.demo
│               └── DemoApplicationTests.java
├── pom.xml
└── README.md
