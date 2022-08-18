package com.validation.item.dto;

import lombok.*;

import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String id;

    private Integer siteId;

    private Integer categoryId;

    private String title;

    private String condition;

    private Float price;

    private Integer quantity;

    private List<String> imageUrl;

    private List<ItemSpecific> itemSpecifies;

    private String description;
}
