package pl.wrryy.amelco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.wrryy.amelco.entity.BetCategory;

import java.util.List;

public interface BetCategoryService {
    public BetCategory findByName(String name);
    public void saveBetCategory(BetCategory betCategory);
    public void deleteBetCategory(long id);
    public List<BetCategory> findAll();
    public Page findAll(Pageable pageable);
}
