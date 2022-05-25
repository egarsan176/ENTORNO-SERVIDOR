package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.FileDB;

@Repository
public interface FileRepo extends JpaRepository<FileDB, String>{
	
	/**
	 * CONSULTA para encontrar un fichero por su nombre
	 * @param name
	 * @return fichero que coincide con ese nombre
	 */
	@Query(value="select * from files where name = ?1", nativeQuery = true) 
	public FileDB findFileByName(String name);
	
	/**
	 * CONSULTA para encontrar el fichero de una receta en concreto
	 */
	@Query(value="select * from files f where f.id =(select r.file_id from recipe r, files f2 where f2.id = r.file_id and r.id= ?1);", nativeQuery = true) 
	public FileDB findFileFromRecipe(Integer id);

}
