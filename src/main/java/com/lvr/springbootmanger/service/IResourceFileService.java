package com.lvr.springbootmanger.service;

import com.lvr.springbootmanger.bean.ResourceFile;

import java.util.List;

public interface IResourceFileService {

    int insert(ResourceFile record);

    List<ResourceFile> queryResourceFileList(ResourceFile resourceFile);

    int delResourceFile( Integer id);
}
