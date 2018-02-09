package pl.wrryy.amelco.service;

import pl.wrryy.amelco.entity.Sport;

import java.util.List;

public interface SportService {
    public Sport findOne(long id);
    public Sport findByName(String name);
    public void saveSport(Sport sport);
    public void deleteSport(Sport sport);
    public void deleteSport(long id);
    public List<Sport> findAll();

}
