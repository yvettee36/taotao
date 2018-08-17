package com.taotao.service;

import com.taotao.common.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yvettee on 18-8-17.
 */
public interface PictureService {

    PictureResult uploadPic(MultipartFile picFile);
}
