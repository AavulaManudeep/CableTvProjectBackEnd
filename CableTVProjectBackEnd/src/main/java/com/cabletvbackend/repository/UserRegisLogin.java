package com.cabletvbackend.repository;

import com.cabletvbackend.dao.Userdetails;

public interface UserRegisLogin {
    public boolean userRegistration(Userdetails userdetails);
    public boolean userAuthentication(Userdetails userdetails);
}
