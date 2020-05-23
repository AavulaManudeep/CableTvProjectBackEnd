package com.cabletvbackend.repository;

import com.cabletvbackend.dao.UserPlanInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlanInfoService extends JpaRepository<UserPlanInfo,String> {
}
