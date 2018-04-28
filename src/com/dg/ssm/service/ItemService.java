package com.dg.ssm.service;

import com.dg.ssm.po.ItemCustom;
import com.dg.ssm.po.Items;
import com.dg.ssm.po.ItemsQueryVo;

import java.util.List;

public interface ItemService {

    List<ItemCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

    /**
     * 根据id查询商品
     *
     * @param id
     * @return
     * @throws Exception
     */
    Items findItemCustom(int id) throws Exception;

    /**
     * 根据id更新商品
     *
     * @param id
     * @param itemCustom
     * @throws Exception
     */
    void updateItemCustom(int id, ItemCustom itemCustom) throws Exception;

}
