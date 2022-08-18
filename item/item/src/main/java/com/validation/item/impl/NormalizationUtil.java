package com.validation.item.impl;

import com.validation.item.dto.ItemSpecific;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NormalizationUtil {

    public List<ItemSpecific> normalizedItemSpecific(List<ItemSpecific> itemSpecifics){
        for (ItemSpecific itemSpecific: itemSpecifics){
            if (StringUtils.isNotBlank(itemSpecific.getColor())) {
                itemSpecific.setColor(itemSpecific.getColor().substring(0, 1).toUpperCase() + itemSpecific.getColor().substring(1).toLowerCase());
            }
            if (StringUtils.isNotBlank(itemSpecific.getModel())) {
                itemSpecific.setModel(itemSpecific.getModel().substring(0, 1).toUpperCase() + itemSpecific.getModel().substring(1).toLowerCase());
            }
        }
        return itemSpecifics;
    }
}
