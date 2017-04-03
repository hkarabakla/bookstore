package com.hkarabakla.repository;


import com.hkarabakla.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {

}
