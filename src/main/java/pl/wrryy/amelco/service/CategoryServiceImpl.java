package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Category;
import pl.wrryy.amelco.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findOne(long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public Category findByName(String name) { return categoryRepository.findByName(name); }

    @Override
    public void saveCategory(Category category) { categoryRepository.save(category); }

    @Override
    public void deleteCategory(Category category) { categoryRepository.delete(category); }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.delete(id);
    }

    @Override
    public List<Category> findAll() { return categoryRepository.findAll(); }

}
