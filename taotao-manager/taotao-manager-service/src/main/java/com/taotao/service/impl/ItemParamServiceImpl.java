package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamExtendMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExtend;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品规格参数模板管理Service
 * Created by yvettee on 18-8-24.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Autowired
    private TbItemParamExtendMapper tbItemParamExtendMapper;

    @Override
    public EasyUIDataGridResult getItemParamList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);


       /* //执行查询
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        //返回的是大文本数据，所以应该调用查询大文本的方法
        List<TbItemParam> list = this.tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
       */

        //使用自定义查询，增加商品类目的查询结果
        List<TbItemParamExtend> list = this.tbItemParamExtendMapper.selectItemParamExtendList();

       /* //取分页信息
        PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
       */

        //取分页信息
        System.out.println(list.size());
        PageInfo<TbItemParamExtend> pageInfo = new PageInfo<TbItemParamExtend>(list);

        //返回处理结果
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);

        return result;
    }

    @Override
    public TaotaoResult getItemParamByCid(Long cid) {
        //根据cid查询规格参数模板
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = tbItemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(cid);

        //执行查询
        List<TbItemParam> list = tbItemParamMapper.selectByExample(tbItemParamExample);

        if (list != null && list.size() > 0) {
            TbItemParam tbItemParam = list.get(0);
            return TaotaoResult.ok(tbItemParam);
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemparParam(Long cid, String paramData) {
        //创建一个pojo
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());

        tbItemParamMapper.insert(tbItemParam);
        return TaotaoResult.ok();
    }
}
