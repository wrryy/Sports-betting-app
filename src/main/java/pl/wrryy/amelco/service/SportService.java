package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Sport;
import pl.wrryy.amelco.repository.SportRepository;

import java.util.List;

@Service
public class SportService  {
    private SportRepository sportRepository;

    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public Sport findOne(long id) {
        return sportRepository.findOne(id);
    }

    public Sport findByName(String name) {
        return sportRepository.findByName(name); }

    public void saveSport(Sport sport) { sportRepository.save(sport); }

    public void deleteSport(Sport sport) { sportRepository.delete(sport); }

    public void deleteSport(long id) {
        sportRepository.delete(id);
    }

    public List<Sport> findAll() { return sportRepository.findAll(); }

}
