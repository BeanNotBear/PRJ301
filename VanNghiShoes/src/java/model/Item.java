/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dto.ProductSizeDTO;
import java.util.List;

/**
 *
 * @author nghin
 */
public class Item {
    
    private Product product;
    private Category category;
    private List<ProductSizeDTO> productSizeDTOs;

    public Item() {
    }

    public Item(Product product, Category category, List<ProductSizeDTO> productSizeDTOs) {
        this.product = product;
        this.category = category;
        this.productSizeDTOs = productSizeDTOs;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductSizeDTO> getProductSizeDTOs() {
        return productSizeDTOs;
    }

    public void setProductSizeDTOs(List<ProductSizeDTO> productSizeDTOs) {
        this.productSizeDTOs = productSizeDTOs;
    }

}
