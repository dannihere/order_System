package com.yu.service;

import com.yu.dataobject.ProductInfo;
import com.yu.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    /*查询上架商品*/
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //  加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //  减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);
    //下架
    ProductInfo offSale(String productId);
}
