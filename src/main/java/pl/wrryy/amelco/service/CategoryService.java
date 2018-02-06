package pl.wrryy.amelco.service;

import pl.wrryy.amelco.entity.Sport;

import java.util.List;

public interface CategoryService {
    public Sport findOne(long id);
    public Sport findByName(String name);
    public void saveCategory(Sport sport);
    public void deleteCategory(Sport sport);
    public void deleteCategory(long id);
    public List<Sport> findAll();

}
