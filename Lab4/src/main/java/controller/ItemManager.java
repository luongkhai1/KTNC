package controller;

import model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void validateItem(Item item){
        if (item == null){
            throw new NullPointerException("Item is Null");//
        }
        if (item.getId() <= 0) {
            throw new IllegalArgumentException("Id phải là số dương");
        }
    }

    public void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên không được bỏ trống");
        }
        if (!name.matches("^[\\p{L}]+([\\p{L}\\s]*[\\p{L}]+)*$")) {
            throw new IllegalArgumentException("Tên chỉ chứa kí tự chữ");
        }
        if (name.length() >= 50) {
            throw new IllegalArgumentException("Tên phải chứa dưới 50 kí tự");
        }
    }

    public void addItem(Item item){
        validateItem(item);
        validateName(item.getName());
        items.add(item);
    }

    public void updateItem(int id, String newName){
        if (id <= 0) {
            throw new IllegalArgumentException("Id phải là số dương");
        }
        validateName(newName);
        boolean found = false;
        for (Item item: items) {
            if (item.getId() == id){
                item.setName(newName);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Item k có ");
        }
    }

    public void deleteItem(int id){
        if (id <= 0) {
            throw new IllegalArgumentException("Id phải là số dương");
        }
        boolean removed = items.removeIf(item -> item.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("Item k có");
        }
    }
}