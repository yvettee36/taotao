package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * Created by yvettee on 2018/7/27.
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}
