<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${groupName}.${artifactName}.mapper.UserMapper">

    <resultMap id="BaseResultMap" type="${groupName}.${artifactName}.model.entity.User">
        <id property="id" column="id" jdbcType="BIGINT"/>
        <result property="userName" column="userName" jdbcType="VARCHAR"/>
        <result property="userAccount" column="userAccount" jdbcType="VARCHAR"/>
        <result property="userAvatar" column="userAvatar" jdbcType="VARCHAR"/>
        <result property="gender" column="gender" jdbcType="TINYINT"/>
        <result property="userRole" column="userRole" jdbcType="VARCHAR"/>
        <result property="userPassword" column="userPassword" jdbcType="VARCHAR"/>
        <result property="createTime" column="createTime" jdbcType="TIMESTAMP"/>
        <result property="updateTime" column="updateTime" jdbcType="TIMESTAMP"/>
        <result property="isDelete" column="isDelete" jdbcType="TINYINT"/>
    </resultMap>

    <sql id="Base_Column_List">
        id,userName,userAccount,
        userAvatar,gender,userRole,
        userPassword,createTime,updateTime,
        isDelete
    </sql>
</mapper>
