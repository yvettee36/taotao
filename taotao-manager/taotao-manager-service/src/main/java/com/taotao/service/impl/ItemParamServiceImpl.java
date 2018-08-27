package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yvettee on 18-8-24.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public EasyUIDataGridResult getItemParamList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        //执行查询
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
//        List<TbItemParam> list = this.tbItemParamMapper.selectByExample(tbItemParamExample);
        //返回的是大文本数据，所以应该调用查询大文本的方法
        List<TbItemParam> list = this.tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

        //取分页信息
        PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);

        //返回处理结果
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);

        return result;
    }
}
