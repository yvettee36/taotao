package com.taotao.service.impl;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;
import com.taotao.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传
 * Created by yvettee on 18-8-17.
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;

    @Override
    public PictureResult uploadPic(MultipartFile picFile) {
        PictureResult result = new PictureResult();
        //判断图片是否为空
        if (picFile.isEmpty()) {
            result.setError(1);
            result.setMessage("图片为空");
            return result;
        }

        //上传图片到服务器
        try {
            //取出图片的扩展名，不要.
            String originalFileName = picFile.getOriginalFilename();
            String extName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

            FastDFSClient client = new FastDFSClient("classpath:properties/client.conf");
            String url = client.uploadFile(picFile.getBytes(), extName);

            //拼接图片服务器的IP地址
            url = IMAGE_SERVER_BASE_URL + url;
            //再把URL相应给客户端
            result.setError(0);
            result.setUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(1);
            result.setMessage("图片上传失败");
        }


        return result;
    }
}
