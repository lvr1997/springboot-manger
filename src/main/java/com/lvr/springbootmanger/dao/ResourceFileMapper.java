package com.lvr.springbootmanger.dao;

import com.lvr.springbootmanger.bean.ResourceFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceFileMapper {
    int deleteByPrimaryKey(Integer resourceId);

    int insert(ResourceFile record);

    int insertSelective(ResourceFile record);

    ResourceFile selectByPrimaryKey(Integer resourceId);

    int updateByPrimaryKeySelective(ResourceFile record);

    int updateByPrimaryKey(ResourceFile record);

    List<ResourceFile>  queryResourceFileList(ResourceFile resourceFile);

    int delResourceFile( Integer id);
}