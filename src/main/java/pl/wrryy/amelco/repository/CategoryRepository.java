package pl.wrryy.amelco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrryy.amelco.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    public Category findByName(String name);
}
