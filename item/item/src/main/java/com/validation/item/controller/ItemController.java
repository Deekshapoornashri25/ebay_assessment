package com.validation.item.controller;

import com.validation.item.dto.Item;
import com.validation.item.exception.ProcessingFailedException;
import com.validation.item.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        log.info("Request to create item");
        Item itemResponse;
        try {
            itemResponse = itemService.createItem(item);
            log.info("Item created successfully");
        }
        catch (Exception e) {
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("Exception in createItem: ").append(e.getMessage()).append(", error: ").append(ExceptionUtils.getRootCauseMessage(e))
                    .append(", stackTrace: ").append(ExceptionUtils.getStackTrace(e));
            log.error(messageBuilder.toString());
            throw e;
        }
        return new ResponseEntity<>(itemResponse, HttpStatus.CREATED);
    }
}
