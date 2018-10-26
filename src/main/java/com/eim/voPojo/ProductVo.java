package com.eim.voPojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品包含类目
 * Created by Zy on 2018/10/26.
 */
@Data
public class ProductVo {

    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private String categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
