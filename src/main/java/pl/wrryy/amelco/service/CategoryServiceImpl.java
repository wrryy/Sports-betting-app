package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Sport;
import pl.wrryy.amelco.repository.SportRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private SportRepository sportRepository;

    public CategoryServiceImpl(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    @Override
    public Sport findOne(long id) {
        return sportRepository.findOne(id);
    }

    @Override
    public Sport findByName(String name) { return sportRepository.findByName(name); }

    @Override
    public void saveCategory(Sport sport) { sportRepository.save(sport); }

    @Override
    public void deleteCategory(Sport sport) { sportRepository.delete(sport); }

    @Override
    public void deleteCategory(long id) {
        sportRepository.delete(id);
    }

    @Override
    public List<Sport> findAll() { return sportRepository.findAll(); }

}
