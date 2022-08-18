package com.validation.item.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.validation.item.dto.Item;
import com.validation.item.dto.ItemSpecific;
import com.validation.item.exception.InvalidDataException;
import com.validation.item.exception.ProcessingFailedException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ItemServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ItemServiceImplTest {
    @Autowired
    private ItemServiceImpl itemServiceImpl;

    @MockBean
    private NormalizationUtil normalizationUtil;



    @Test
    void testCreateItem() {
        //Set Up
        when(normalizationUtil.normalizedItemSpecific((List<ItemSpecific>) any())).thenReturn(new ArrayList<>());
        Item item = mock(Item.class);
        when(item.getItemSpecifies()).thenThrow(new ProcessingFailedException("An error occurred"));
        when(item.getSiteId()).thenReturn(4);
        when(item.getTitle()).thenReturn("title");

        //Verify
        assertThrows(ProcessingFailedException.class, () -> itemServiceImpl.createItem(item));
        verify(item, atLeast(1)).getSiteId();
        verify(item).getTitle();
        verify(item).getItemSpecifies();
    }



    @Test
    void testCreateItem1() {
        //Set  Up
        when(normalizationUtil.normalizedItemSpecific((List<ItemSpecific>) any())).thenReturn(new ArrayList<>());
        Item item = mock(Item.class);
        when(item.getItemSpecifies()).thenReturn(new ArrayList<>());
        when(item.getSiteId()).thenReturn(4);
        when(item.getTitle()).thenReturn("");
        assertThrows(InvalidDataException.class, () -> itemServiceImpl.createItem(item));
        verify(item, atLeast(1)).getSiteId();
        verify(item).getTitle();
    }



    @Test
    void testValidateTitleLength() {

        //Assert
        assertThrows(InvalidDataException.class, () -> ItemServiceImpl.validateTitleLength(""));
    }


    @Test
    void testValidateItemSpecifics() {

        //Set Up
        ItemSpecific itemSpecific = new ItemSpecific();
        itemSpecific.setColor("errorId");
        itemSpecific.setModel("errorId");

        ItemSpecific itemSpecific1 = new ItemSpecific();
        itemSpecific1.setColor("Color");
        itemSpecific1.setModel("");

        ArrayList<ItemSpecific> itemSpecificList = new ArrayList<>();
        itemSpecificList.add(itemSpecific1);
        itemSpecificList.add(itemSpecific);

        //Verify
        assertThrows(InvalidDataException.class, () -> ItemServiceImpl.validateItemSpecifics(itemSpecificList));
    }

}

