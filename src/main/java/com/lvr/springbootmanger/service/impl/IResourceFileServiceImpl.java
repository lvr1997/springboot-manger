package com.lvr.springbootmanger.service.impl;

import com.lvr.springbootmanger.bean.ResourceFile;
import com.lvr.springbootmanger.dao.ResourceFileMapper;
import com.lvr.springbootmanger.service.IResourceFileService;
import com.lvr.springbootmanger.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IResourceFileServiceImpl implements IResourceFileService {

    @Resource
    private ResourceFileMapper resourceFileMapper;


    @Override
    @Transactional
    public int insert(ResourceFile record) {
        int  res=  resourceFileMapper.insert(record);
        return res;
    }

    @Override
    public List<ResourceFile> queryResourceFileList(ResourceFile resourceFile) {
        List<ResourceFile>  resourceFiles=    resourceFileMapper.queryResourceFileList(resourceFile);
        return resourceFiles;
    }

    @Override
    public int delResourceFile(Integer id) {

        return resourceFileMapper.delResourceFile(id);
    }
}
