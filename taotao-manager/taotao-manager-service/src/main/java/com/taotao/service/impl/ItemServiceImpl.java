package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品信息管理
 * Created by yvettee on 2018/7/24.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

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
}
