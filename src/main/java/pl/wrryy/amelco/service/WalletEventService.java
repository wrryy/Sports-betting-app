package pl.wrryy.amelco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.entity.WalletEvent;
import pl.wrryy.amelco.repository.WalletEventRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    public void saveWalletEvent(WalletEvent walletEvent){ repository.save(walletEvent);}

    public WalletEvent createWalletEvent(String event, User user, BigDecimal value){
        WalletEvent walletEvent = new WalletEvent();
        walletEvent.setType(event);
        walletEvent.setUser(user);
        walletEvent.setValue(value);
        walletEvent.setCreated(LocalDateTime.now());
        return walletEvent;
    }
}