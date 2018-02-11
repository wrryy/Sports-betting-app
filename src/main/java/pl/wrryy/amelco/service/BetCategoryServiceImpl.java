package pl.wrryy.amelco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.BetCategory;
import pl.wrryy.amelco.repository.BetCategoryRepository;

import java.util.List;
@Service
public class BetCategoryServiceImpl implements BetCategoryService {
    BetCategoryRepository betCategoryRepository;

    public BetCategoryServiceImpl(BetCategoryRepository betCategoryRepository) {
        this.betCategoryRepository = betCategoryRepository;
    }

    @Override
    public BetCategory findByName(String name) { return betCategoryRepository.findByName(name); }

    @Override
    public void saveBetCategory(BetCategory betCategory) { betCategoryRepository.save(betCategory);}

    @Override
    public void deleteBetCategory(long id) { betCategoryRepository.delete(id); }

    @Override
    public List<BetCategory> findAll() { return betCategoryRepository.findAll(); }
    @Override
    public Page findAll(Pageable pageable) { return betCategoryRepository.findAll(pageable); }
}
