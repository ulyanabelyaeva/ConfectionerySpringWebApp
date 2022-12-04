package com.belyaeva.services.impl;

import com.belyaeva.entity.CartEntity;
import com.belyaeva.services.abstractions.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.belyaeva.repository.CartRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    public static final SimpleDateFormat TEXT_FORMATTER = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private final Logger logger = Logger.getLogger(CartServiceImpl.class.getName());

    @Autowired
    private CartRepository cartRepository;

    public CartEntity getCartByUserId(Long id){
        return cartRepository.findAllByUserId(id).stream()
                .filter(c -> !c.isStatus())
                .findFirst()
                .orElse(null);
    }

    public List<CartEntity> getOrderList(Long id){
        return cartRepository.findAllByUserId(id).stream()
                .filter(CartEntity::isStatus)
                .sorted(((Comparator<CartEntity>) (o1, o2) -> {
                    try {
                        return TEXT_FORMATTER.parse(o1.getDate()).compareTo(TEXT_FORMATTER.parse(o2.getDate()));
                    } catch (ParseException e) {
                        logger.info("не удалось распарсить дату");
                        return 0;
                    }
                }).reversed())
                .collect(Collectors.toList());
    }

    public List<CartEntity> getUnreadyOrderList(){
        return cartRepository.findAll().stream()
                .filter(c -> !c.isReady() && c.isStatus())
                .collect(Collectors.toList());
    }

    public void addNewCart(CartEntity cartEntity){
        cartRepository.save(cartEntity);
    }

    public void moveOldCartToOrdersAndCreteNewCart(CartEntity cartEntity){
        cartEntity.setDate(TEXT_FORMATTER.format(new Date()));
        cartEntity.setStatus(true);
        cartRepository.save(cartEntity);
    }

    public void moveOrderToReady(CartEntity cartEntity){
        cartEntity.setReady(true);
        cartRepository.save(cartEntity);
    }

    public CartEntity getCartById(Long id){
        return cartRepository.findById(id).orElse(null);
    }

}
