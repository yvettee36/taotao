package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

/**
 * 规格参数
 * Created by yvettee on 18-8-24.
 */
public interface ItemParamService {

    EasyUIDataGridResult getItemParamList(int page, int rows);

    TaotaoResult getItemParamByCid(Long cid);

    TaotaoResult insertItemparParam(Long cid, String paramData);
}
