/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author nghin
 */
public class CategoryDTO {
    private int id;
    private String name;
    private int numberOfProduct;

    public CategoryDTO() {
    }

    public CategoryDTO(int id, String name, int numberOfProduct) {
        this.id = id;
        this.name = name;
        this.numberOfProduct = numberOfProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }
    
}
