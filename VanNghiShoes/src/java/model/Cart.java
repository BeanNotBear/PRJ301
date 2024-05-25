/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dto.ProductSizeDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nghin
 */
public class Cart {

    private List<Yield> cart;

    public Cart() {
    }

    public Cart(List<Yield> cart) {
        this.cart = cart;
    }

    public List<Yield> getCart() {
        return cart;
    }

    public void setCart(List<Yield> cart) {
        this.cart = cart;
    }

    public int getYieldQuantityByProductIdAndSizeId(int productId, int sizeId) {
        return getYieldByProductIdAndSizeId(productId, sizeId).getQuantity();
    }

    public Yield getYieldByProductIdAndSizeId(int productId, int sizeId) {
        for (Yield y : cart) {
            if (y.getProduct().getId() == productId
                    && y.getSize().getId() == sizeId) {
                return y;
            }
        }
        return null;
    }

    public boolean addYield(Yield yield) {
        if (getYieldByProductIdAndSizeId(yield.getProduct().getId(),
                yield.getSize().getId()) != null) {
            Yield y = getYieldByProductIdAndSizeId(yield.getProduct().getId(),
                    yield.getSize().getId());
            y.setQuantity(y.getQuantity() + yield.getQuantity());
        } else {
            cart.add(yield);
        }
        return true;
    }

    public boolean removeYield(int productId, int sizeId) {
        Yield yield = getYieldByProductIdAndSizeId(productId, sizeId);
        if (yield != null) {
            cart.remove(yield);
            return true;
        }
        return false;
    }

    public long totalPrice() {
        long total = 0;
        for (Yield yield : cart) {
            total += yield.getQuantity() * yield.getProduct().getListedPrice();
        }
        return total;
    }

    public Cart(String text, int userId, List<Item> items) {
        cart = new ArrayList<>();
        if (text != null && text.length() != 0) {
            String[] s = text.split("-");
            for (String i : s) {
                String[] properties = i.split(":");
                if (Integer.parseInt(properties[0]) == userId) {
                    int productId = Integer.parseInt(properties[1]);
                    int sizeId = Integer.parseInt(properties[2]);
                    int quantity = Integer.parseInt(properties[3]);
                    Item item = getItemById(productId, items);
                    ProductSizeDTO size = getSizeById(sizeId, item.getProductSizeDTOs());
                    Yield yield = new Yield(item.getProduct(),
                            quantity,
                            new Size(size.getSizeId(), size.getSize()));
                    addYield(yield);
                }
            }
        }
    }

    private ProductSizeDTO getSizeById(int id, List<ProductSizeDTO> sizes) {
        for (ProductSizeDTO size : sizes) {
            if (size.getSizeId() == id) {
                return size;
            }
        }
        return null;
    }

    private Item getItemById(int id, List<Item> items) {
        for (Item item : items) {
            if (item.getProduct().getId() == id) {
                return item;
            }
        }
        return null;
    }

}
