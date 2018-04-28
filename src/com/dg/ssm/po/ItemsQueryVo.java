package com.dg.ssm.po;

import java.util.List;

public class ItemsQueryVo {

    private ItemCustom itemCustom;

    private List<ItemCustom> itemsList;

    public List<ItemCustom> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ItemCustom> itemsList) {
        this.itemsList = itemsList;
    }

    public ItemCustom getItemCustom() {
        return itemCustom;
    }

    public void setItemCustom(ItemCustom itemCustom) {
        this.itemCustom = itemCustom;
    }

    @Override
    public String toString() {
        return "ItemsQueryVo{" +
                "itemCustom=" + itemCustom +
                '}';
    }
}
