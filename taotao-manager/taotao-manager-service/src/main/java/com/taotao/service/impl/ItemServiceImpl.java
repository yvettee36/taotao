package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import com.taotao.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品信息管理
 * Created by yvettee on 2018/7/24.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;


    @Override
    public TbItem getItemById(Long itemId) {
        TbItem tbItem = this.tbItemMapper.selectByPrimaryKey(itemId);
        return tbItem;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        //执行查询
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> list = this.tbItemMapper.selectByExample(tbItemExample);
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);

        //返回处理结果
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem tbItem, String desc) {

        //生成商品ID
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        //商品状态：1--正常  2--下架  3--删除
        tbItem.setStatus((byte) 1);
        //创建时间和更新时间
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        //插入商品表
        tbItemMapper.insert(tbItem);
        //商品描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        //插入商品描述数据
        tbItemDescMapper.insert(tbItemDesc);
        return TaotaoResult.ok();
    }
}
