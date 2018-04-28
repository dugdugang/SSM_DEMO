package com.dg.ssm.service;

import com.dg.ssm.mapper.ItemsMapper;
import com.dg.ssm.mapper.ItemsMapperCustom;
import com.dg.ssm.po.ItemCustom;
import com.dg.ssm.po.Items;
import com.dg.ssm.po.ItemsQueryVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    //注入
    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<ItemCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {

        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    @Override
    public Items findItemCustom(int id) throws Exception {
        Items itemCustom = itemsMapper.selectByPrimaryKey(id);
        return itemCustom;
    }

    @Override
    public void updateItemCustom(int id, ItemCustom itemCustom) throws Exception {
        if (id <= 0) {
            return;
        }
        int i = itemsMapper.updateByPrimaryKeyWithBLOBs(itemCustom);
        if (i > 0) {
            System.out.println("更新成功!!!");
        }
    }
}
