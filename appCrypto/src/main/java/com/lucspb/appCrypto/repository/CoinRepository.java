package com.lucspb.appCrypto.repository;

import com.lucspb.appCrypto.entity.Coin;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@EnableAutoConfiguration
public class CoinRepository {

    private static String INSERT = "insert into coin (name, price, qtd, datetime) values (?,?,?,?)";

    private static String SELECT_ALL = "select name, sum(qtd) as qtd from coin group by name";

    private JdbcTemplate jdbcTemplate;

    public CoinRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Coin insert(Coin coin){
        Object[] attr = new Object[] {
                coin.getName(),
                coin.getPrice(),
                coin.getQtd(),
                coin.getDateTime()
        };
        jdbcTemplate.update(INSERT, attr);
        return coin;
    }

    public List<Coin> getAll(){
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Coin>() {
            @Override
            public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
                Coin coin = new Coin();
                coin.setName(rs.getString("name"));
                coin.setQtd(rs.getBigDecimal("qtd"));
                return coin;
            }
        });
    }
}
