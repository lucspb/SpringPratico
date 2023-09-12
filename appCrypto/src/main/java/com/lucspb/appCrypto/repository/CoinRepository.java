package com.lucspb.appCrypto.repository;

import com.lucspb.appCrypto.dto.CoinDTO;
import com.lucspb.appCrypto.entity.Coin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@EnableAutoConfiguration
public class CoinRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Coin insert(Coin coin) {
        entityManager.persist(coin);
        return coin;
    }

    public List<CoinDTO> getAll() {
        String jpql = "select new com.lucspb.appCrypto.dto.CoinDTO(c.name, sum(c.qtd)) from Coin c group by c.name";
        TypedQuery<CoinDTO> query = entityManager.createQuery(jpql, CoinDTO.class);
        return query.getResultList();
    }


    public List<Coin> getByName(String name){
        String jpql = "select c from Coin c where c.name like :name";
        entityManager.createQuery(jpql, Coin.class);
        TypedQuery<Coin> query = entityManager.createQuery(jpql, Coin.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }


    public List<Coin> getById(int id){
        String jpql = "select c from Coin c where c.id like :id";
        entityManager.createQuery(jpql, Coin.class);
        TypedQuery<Coin> query = entityManager.createQuery(jpql, Coin.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Transactional
    public boolean remove(int id){
        Coin coin = entityManager.find(Coin.class, id);
        if(coin == null){
            throw new RuntimeException();
        }
        entityManager.remove(coin);
        return true;
    }




    @Transactional
    public Coin update (Coin coin){
        entityManager.merge(coin);
        return coin;
    }
}


