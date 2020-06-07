package com.cabletvbackend.repository;

import com.cabletvbackend.dao.Userdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailService extends JpaRepository<Userdetails,String> {



}
