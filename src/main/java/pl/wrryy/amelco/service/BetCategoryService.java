package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.BetCategory;
import pl.wrryy.amelco.repository.BetCategoryRepository;

import java.util.List;
@Service
public class BetCategoryService  {
    BetCategoryRepository betCategoryRepository;

    public BetCategoryService(BetCategoryRepository betCategoryRepository) {
        this.betCategoryRepository = betCategoryRepository;
    }

    public BetCategory findByName(String name) { return betCategoryRepository.findByName(name); }

    public BetCategory findOne(long id){return betCategoryRepository.findOne(id);}

    public void saveBetCategory(BetCategory betCategory) { betCategoryRepository.save(betCategory);}

    public void deleteBetCategory(long id) { betCategoryRepository.delete(id); }

    public List<BetCategory> findAll() { return betCategoryRepository.findAll(); }
}
