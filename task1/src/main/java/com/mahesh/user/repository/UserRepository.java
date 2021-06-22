package com.mahesh.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mahesh.user.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "select * from user_tbl where name =:Name and surname =:surname and pincode =:pincode", nativeQuery = true)
	List<User> findByNameAndSurnameAndPincode(@Param("Name") String name, @Param("surname") String surname,
			@Param("pincode") int pincode);

}
