package com.gitlab.lhqezio.util;

import java.util.List;

import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.user.UserData;

public interface DataLoader {
    public List<UserData> getUsersData();
    public List<Product> getProductsData();

    public void updateRowsAndSave(List<Product> updatedRows);
    public void updateRowsAndSave(List<Product> updatedRows, List<Product> productListToEdit);
}
