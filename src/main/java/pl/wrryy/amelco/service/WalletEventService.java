package pl.wrryy.amelco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.entity.WalletEvent;
import pl.wrryy.amelco.repository.WalletEventRepository;

import java.util.List;

@Service
public class WalletEventService {
    private WalletEventRepository repository;

    public WalletEventService(WalletEventRepository repository) {
        this.repository = repository;
    }

    public List<WalletEvent> findAllByUser(User user) {
        return repository.findAllByUserOrderByCreatedDesc(user);
    }

    public Page findAllByUser(User user, Pageable pageable) {
        return repository.findAllByUserOrderByCreatedDesc(user, pageable);
    }
    public void saveWalletEvent(WalletEvent event) {
    }

}