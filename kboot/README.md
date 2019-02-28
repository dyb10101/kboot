# kboot

#### 介绍
基于springboot以用户应用场景为约定的后端快速开发模板
 
核心观念:写得更简单 开发效率更高 代码更容易理解 站在全局做思考 创建具体约定

#### 软件架构
nginx + springboot2.1.3(undertow、jpa--特殊需求用NamedParameterJdbcTemplate、websocket) + mysql5.7.24

项目编码:java(jdk1.8.0_152+所有编码utf-8) mysql(字符集utf8+排序规则utf8_general_ci+innodb+不使用外键关联)

#### 项目地址
项目文档:https://www.eolinker.com/#/share/index?shareCode=ZFPEYF

请求时请将 eolinker-api 的REST参数转换为 body-raw-json 字符串参数,Authorization参数每次login后都会变化注意获取后保存

github地址:https://github.com/kauuze/kboot.git

### 项目部署
连接线上数据库，服务器仅开放80 8888 8899三个端口，用nginx做转发和ssl加密功能