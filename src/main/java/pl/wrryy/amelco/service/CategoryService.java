package pl.wrryy.amelco.service;

import pl.wrryy.amelco.entity.Category;

import java.util.List;

public interface CategoryService {
    public Category findOne(long id);
    public Category findByName(String name);
    public void saveCategory(Category category);
    public void deleteCategory(Category category);
    public void deleteCategory(long id);
    public List<Category> findAll();

}
