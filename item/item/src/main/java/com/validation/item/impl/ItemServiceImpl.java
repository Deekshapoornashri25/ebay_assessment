package com.validation.item.impl;

import com.validation.item.dto.Item;
import com.validation.item.dto.ItemSpecific;
import com.validation.item.exception.InvalidDataException;
import com.validation.item.exception.ProcessingFailedException;
import com.validation.item.service.ItemService;
import cool.graph.cuid.Cuid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    NormalizationUtil normalizationUtil;

    @Override
    public Item createItem(Item item) {
        try {
            if (item.getSiteId() >= 0 && item.getSiteId() <= 100) {
                validateTitleLength(item.getTitle());
                Thread.sleep(1000);
                normalizationUtil.normalizedItemSpecific(normalizationUtil.normalizedItemSpecific(item.getItemSpecifies()));
                validateItemSpecifics(item.getItemSpecifies());
                item.setId(Cuid.createCuid());
            }
        }
        catch (InvalidDataException e){
            throw e;
        }
        catch (Exception e){
            log.error("Message: " + ExceptionUtils.getRootCauseMessage(e) + ", StackTrace: " + e.fillInStackTrace());
            throw new ProcessingFailedException(ExceptionUtils.getRootCauseMessage(e));
        }
        return item;
    }

    public static void validateTitleLength(String title) {
        if (StringUtils.isBlank(title) || title.trim().length() > 85) {
            HashMap<String, String> details = new HashMap<>();
            details.put("errorId", "title");
            details.put("errorMessage", "Title length is under 85 characters");
            throwValidationError(details);
        }
    }

    public static void validateItemSpecifics(List<ItemSpecific> itemSpecifics) {
        HashMap<String, String> details = new HashMap<>();
        if (itemSpecifics.size() < 2 || itemSpecifics.size() > 10){
            details.put("errorId", "itemSpecifics");
            details.put("errorMessage", "Number of Item Specifics between 2 - 10");
            throwValidationError(details);
        }
        for (ItemSpecific itemSpecific : itemSpecifics){
            if (StringUtils.isBlank(itemSpecific.getModel())){
                details.put("errorId", "itemSpecifics(model)");
                details.put("errorMessage", "Model must contains a non empty value");
                throwValidationError(details);
            }
        }
    }

    private static void throwValidationError(HashMap<String, String> details) {
        if (!details.isEmpty()) {
            throw new InvalidDataException(details);
        }
    }
}
