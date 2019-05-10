package com.lvr.springbootmanger.controller;

import com.alibaba.fastjson.JSONObject;
import com.lvr.springbootmanger.bean.ResourceFile;
import com.lvr.springbootmanger.bean.User;
import com.lvr.springbootmanger.service.IResourceFileService;
import com.lvr.springbootmanger.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/resourceFile")
public class ResourceFileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceFileController.class);

    @Resource
    private IResourceFileService resourcefileService;

    @RequestMapping("index")
    public String index(ResourceFile resource,Model model){
        List<ResourceFile> resourceFileList=  resourcefileService.queryResourceFileList(resource);
        model.addAttribute("resourceFileList", resourceFileList);
        return "admin/resource/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(){

        return "admin/resource/form";
    }


    @RequestMapping(value = "/upload",method=RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("resourceName") MultipartFile resourceName , HttpServletRequest request) {
        ResourceFile resourceFile = new ResourceFile();
        if (resourceName.isEmpty()) {
            return "文件上传失败，请选择文件";
        }
        String fileName = resourceName.getOriginalFilename();//获取文件名称
        // 存放在这个路径下：该路径是该工程目录下的static文件下：(注：该文件可能需要自己创建)
        // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的

        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/upload/";
        System.out.println(filePath);
        File file = new File(filePath + fileName);
        try {
            resourceName.transferTo(file);
            resourceFile.setResourceName(fileName);
            resourceFile.setResourcePath(filePath);
            User onloadUser = (User) request.getSession().getAttribute("userInfo");
            resourceFile.setOnloadUser(onloadUser.getUserName());
            LOGGER.info("上传成功");
            resourcefileService.insert(resourceFile);
            return "上传成功";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败";
    }

    @ResponseBody
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public String download(String  downloadUrl, String fileName, HttpServletResponse response) throws UnsupportedEncodingException {

        if (fileName != null){
            //设置文件路径
            File file = new File(downloadUrl,fileName);
            if (file.exists()){

                response.setHeader("content-type", "application/octet-stream");//设置强制下载不打开
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" +URLEncoder.encode(fileName,"UTF-8")); //设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream  fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1){
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }

                    LOGGER.info("下载成功");

                    return "下载成功";
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null){
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        }

        return null;
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
     public String delResource(@PathVariable("id")Integer id, String url,String name){
        File file = new File(url, name);
        String msg;
        if (file.exists() && file.isFile()){
            file.delete();
            int delid=  resourcefileService.delResourceFile(id);
            if (delid > 0){
                msg = "删除成功";

            } else {
                msg =  "删除失败";
            }

        } else {
            int delid = resourcefileService.delResourceFile(id);
            if (delid>0)
                msg = "删除成功";
            else
                msg = "删除失败";
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",msg);
        return jsonObject.toJSONString();
     }


}
