package com.example.demo.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileDB;
import com.example.demo.repository.FileRepo;
/**
 * Servicio que se encarga de mediar entre el controller y el repositorio de FileDB
 * @author estefgar
 *
 */
@Service
public class FilesStorageService {

	@Autowired private FileRepo fileREPO;
	
	/**
	 * MÉTODO que recibe MultipartFile objeto, lo transformar en FileDBobjeto y lo guarda en la base de datos 
	 * @param file
	 * @return objeto File
	 * @throws IOException
	 */
	public FileDB store(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
	    return this.fileREPO.save(FileDB);
	}
	
	/**
	 * MÉTODO para recuperar un objeto FileDB a través de su id
	 * @param id
	 * @return objeto FileDB
	 */
	public FileDB getFile(String id) {
	  return this.fileREPO.findById(id).get();
	}
	
	/**
	 * MÉTODO que devuelve todos los archivos almacenados en la bbdd
	 * @return lista de archivos
	 */
	public Stream<FileDB> getAllFiles() {
	  return this.fileREPO.findAll().stream();
	}
	
	//te devuelve el fichero a través del nombre de la imagen
	public FileDB findFileByName(String name) {
		return this.fileREPO.findFileByName(name);
		
	}
	
	/**
	 * MÉTODO que te devuelve el fichero asociado a una receta a través de una consulta
	 * @param id de la receta
	 * @return fichero
	 */
	public FileDB getfileByRecipe(Integer id) {
		return this.fileREPO.findFileFromRecipe(id);
	}
	
	
	
	
}

