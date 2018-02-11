package pl.wrryy.amelco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.entity.WalletEvent;
import pl.wrryy.amelco.repository.WalletEventRepository;

import java.util.List;

@Service
public class WalletEventServiceImpl implements WalletEventService {
private WalletEventRepository repository;

    public WalletEventServiceImpl(WalletEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WalletEvent> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    public Page findAllByUser(User user, Pageable pageable) {
        return repository.findAllByUser(user, pageable);
    }
    @Override
    public void saveWalletEvent(WalletEvent event) {
    }

}
