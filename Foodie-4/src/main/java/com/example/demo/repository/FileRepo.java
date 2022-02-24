package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.FileDB;

@Repository
public interface FileRepo extends JpaRepository<FileDB, String>{
	
	@Query(value="select id from files where name = ?1", nativeQuery = true) 
	public String findFileIDByName(String name);

}
