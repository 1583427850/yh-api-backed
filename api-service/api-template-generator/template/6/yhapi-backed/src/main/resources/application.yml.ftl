spring:
  application:
    name: ${artifactName}
  # DataSource Config
  <#if needMysql>
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/my_db
    username: root
    password: 123456
  </#if>
  mvc:
    pathmatch:
      matching-strategy: ANT_PATH_MATCHER
  # session 失效时间（分钟）
  session:
    timeout: 86400
    store-type: redis
  # redis 配置
<#if needRedis>
  redis:
    port: 6379
    host: localhost
    database: 0
</#if>
server:
  port: 7529
  servlet:
    context-path: /api
<#if needMybatisPlus>
mybatis-plus:
  configuration:
    map-underscore-to-camel-case: false
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      logic-delete-field: isDelete # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
</#if>