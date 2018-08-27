package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;

/**
 * 规格参数
 * Created by yvettee on 18-8-24.
 */
public interface ItemParamService {

    EasyUIDataGridResult getItemParamList(int page, int rows);
}
