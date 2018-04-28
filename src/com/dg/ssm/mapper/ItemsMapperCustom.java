package com.dg.ssm.mapper;

import com.dg.ssm.po.ItemCustom;
import com.dg.ssm.po.ItemsQueryVo;

import java.util.List;

public interface ItemsMapperCustom {

    List<ItemCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}
