package pl.wrryy.amelco.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.entity.WalletEvent;

import java.util.List;

@Repository
public interface WalletEventRepository extends JpaRepository<WalletEvent, Long>{
    public List<WalletEvent> findAllByUser(User user);
    public Page<WalletEvent> findAllByUser(User user, Pageable pageable);

}
