package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品规格参数管理Controller
 * Created by yvettee on 18-8-24.
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;


    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemParamList(Integer page,Integer rows) {
        EasyUIDataGridResult result = itemParamService.getItemParamList(page,rows);

        return result;
    }

    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable Long cid) {
        TaotaoResult result = itemParamService.getItemParamByCid(cid);

        return result;
    }
}
