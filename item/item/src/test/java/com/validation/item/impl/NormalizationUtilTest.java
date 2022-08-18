package com.validation.item.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.validation.item.dto.ItemSpecific;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NormalizationUtil.class})
@ExtendWith(SpringExtension.class)
class NormalizationUtilTest {

    @Autowired
    private NormalizationUtil normalizationUtil;


    @Test
    void testNormalizedItemSpecific() {

        //Set Up
        ItemSpecific itemSpecific = mock(ItemSpecific.class);
        when(itemSpecific.getColor()).thenReturn("Color");
        when(itemSpecific.getModel()).thenReturn("Model");
        doNothing().when(itemSpecific).setColor((String) any());
        doNothing().when(itemSpecific).setModel((String) any());
        itemSpecific.setColor("Color");
        itemSpecific.setModel("Model");

        //Run
        ArrayList<ItemSpecific> itemSpecificList = new ArrayList<>();
        itemSpecificList.add(itemSpecific);
        List<ItemSpecific> result = normalizationUtil.normalizedItemSpecific(itemSpecificList);

        //Verify
        assertSame(itemSpecificList, result);
        assertEquals(1, result.size());
        verify(itemSpecific, atLeast(1)).getColor();
        verify(itemSpecific, atLeast(1)).getModel();
        verify(itemSpecific, atLeast(1)).setColor((String) any());
        verify(itemSpecific, atLeast(1)).setModel((String) any());
    }
}

