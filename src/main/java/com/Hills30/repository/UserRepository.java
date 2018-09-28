/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hills30.repository;

import com.Hills30.domain.People;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nenad
 */
@Repository
public interface UserRepository extends CrudRepository<People, Long> {

    People findById(long id);

    @Query(value = "SELECT friends FROM PEOPLE_FRIENDS where people_id= ?1",
            nativeQuery = true)
    public List<Integer> findFriendsById(long id);
}
