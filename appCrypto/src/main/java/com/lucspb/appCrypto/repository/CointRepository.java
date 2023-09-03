package com.lucspb.appCrypto.repository;

import com.lucspb.appCrypto.entity.Coin;
import org.springframework.jdbc.core.JdbcTemplate;

public class CointRepository {

    private static String INSERT = "insert into coin (name, price, quantity, datetime)"
            + "values (?,?,?,?)";
    private JdbcTemplate jdbcTemplate;

    public CointRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Coin insert(Coin coin){
        Object[] atributes = new Object[] {
                coin.getName(),
                coin.getPrice(),
                coin.getQuantity(),
                coin.getDateTime()
        };
        jdbcTemplate.update(INSERT, atributes);
        return coin;
    }
}
