package com.dango.reggie.controller;

import com.dango.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传和下载
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        //file是一个临时文件， 需要转存到指定位置， 否则本次请求结束后文件将自动清除
        log.info(file.toString());

        String originalFilename = file.getOriginalFilename();  //abc.jpg
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID重新生成文件名， 防止文件名称重复造成文件覆盖
        String Filename = UUID.randomUUID().toString() + suffix;

        //创建一个目录对象
        File dir = new File(basePath);
        //判断当前目录是否存在
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            //将临时文件转存到指定路径
            file.transferTo(new File(basePath + Filename));
        } catch (IOException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return R.success(Filename);
    }
}
