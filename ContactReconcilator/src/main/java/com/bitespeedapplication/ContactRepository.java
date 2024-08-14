package com.bitespeedapplication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, String>{

	@Query(value = "select * from public.contact where phone_number=:phoneNumber",nativeQuery = true) 
	List<ContactEntity> findByPhoneNumber(double phoneNumber);

	@Query(value = "select * from contact where email like :email",nativeQuery = true)
	List<ContactEntity> findByEmail(String email);
}
