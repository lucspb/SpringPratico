package com.lucspb.appCrypto.repository;

import com.lucspb.appCrypto.dto.CoinDTO;
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

    private static String SELECT_BY_NAME = "select * from coin where name = ?";

    private static String SELECT_BY_ID = "select * from coin where id = ?";

    private static String DELETE = "delete from coin where id = ?";

    private static String UPDATE = "update coin SET name = ?, price = ?, qtd = ? where id = ?";

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

    public List<CoinDTO> getAll(){
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CoinDTO>() {
            @Override
            public CoinDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CoinDTO coin = new CoinDTO();
                coin.setName(rs.getString("name"));
                coin.setQtd(rs.getBigDecimal("qtd"));
                return coin;
            }
        });
    }

    public List<Coin> getByName(String name){
        Object[] attr = new Object[] { name };
        return jdbcTemplate.query(SELECT_BY_NAME, new RowMapper<Coin>() {
            @Override
            public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
                Coin coin = new Coin();
                coin.setId(rs.getInt("id"));
                coin.setName(rs.getString("name"));
                coin.setPrice(rs.getBigDecimal("price"));
                coin.setQtd(rs.getBigDecimal("qtd"));
                coin.setDateTime(rs.getTimestamp("datetime"));

                return coin;
            }
        }, attr);
    }

    public List<Coin> getById(int id){
        Object[] attr = new Object[] { id };
        return jdbcTemplate.query(SELECT_BY_ID, new RowMapper<Coin>() {
            @Override
            public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
                Coin coin = new Coin();
                coin.setId(rs.getInt("id"));
                coin.setName(rs.getString("name"));
                coin.setPrice(rs.getBigDecimal("price"));
                coin.setQtd(rs.getBigDecimal("qtd"));
                coin.setDateTime(rs.getTimestamp("datetime"));
                return coin;
            }
        }, attr);
    }

    public int remove(int id){
        return jdbcTemplate.update(DELETE, id);
    }

    public Coin update (Coin coin){
        Object[] attr = new Object[] {
                coin.getName(),
                coin.getPrice(),
                coin.getQtd(),
                coin.getId()
        };
        jdbcTemplate.update(UPDATE, attr);
        return coin;
    }
}
