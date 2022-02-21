package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.FileDB;

@Repository
public interface FileRepo extends JpaRepository<FileDB, String>{

}
