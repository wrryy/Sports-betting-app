package pl.wrryy.amelco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.entity.WalletEvent;

import java.util.List;

public interface WalletEventService {
    public List<WalletEvent> findAllByUser(User user);
    public Page findAllByUser(User user, Pageable pageable);
    public void saveWalletEvent(WalletEvent event);
}
