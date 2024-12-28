package com.springbootproject.date_night_arcade.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springbootproject.date_night_arcade.model.Card;

@Repository
public interface CardRepo extends JpaRepository<Card, Integer> {

}
