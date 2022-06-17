package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.FileNotFoundException;
import com.example.demo.message.ResponseFile;
import com.example.demo.message.ResponseMessage;
import com.example.demo.model.FileDB;
import com.example.demo.service.FilesStorageService;
/**
 * Esta clase es un controlador REST que intercepta peticiones al servidor, encargándose de las tareas
 * relacionadas con la gestión de ficheros
 * @author estefgar
 *
 */
@RestController
public class FileController {
	
	@Autowired private FilesStorageService storageService;
	
	/**
	 * MÉTODO POST para subir archivos a la bbdd
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		
	    try {
	    	if(this.storageService.findFileByName(file.getOriginalFilename())!= null) {
	    		message = "Ya existe una imagen con el nombre: " + file.getOriginalFilename() + ".";
	    		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    	}
	     FileDB  newFile =this.storageService.store(file);
	      message = "Su archivo -"+file.getOriginalFilename()+"- se ha subido correctamente con el nombre de: "+newFile.getName();
	     
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "No se ha podido subir el archivo: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	}
	
	/**
	 * MÉTODO GET para obtener todos los archivos de la bbdd
	 * @return
	 */
	@GetMapping("/files")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = this.storageService.getAllFiles().map(dbFile -> {
	      String fileDownloadUri = ServletUriComponentsBuilder
	          .fromCurrentContextPath()
	          .path("/files/")
	          .path(dbFile.getId())
	          .toUriString();
	      return new ResponseFile(
	          dbFile.getName(),
	          fileDownloadUri,
	          dbFile.getType(),
	          dbFile.getData().length);
	    }).collect(Collectors.toList());
	    return ResponseEntity.status(HttpStatus.OK).body(files);
	  }
	 

	 
	/**
	 * MÉTODO GET que me devuelve un fichero a través de su nombre de fichero
	 * @param name
	 * @return
	 */
	 @GetMapping("/files/{name}")
	 public ResponseEntity<FileDB> getFileIDByName(@PathVariable String name) {
	    FileDB file = this.storageService.findFileByName(name);
	    if(file== null) {
	    	throw new FileNotFoundException(name);
	    }else {
	    	return ResponseEntity.ok(file);
	    }
	  }
	 
	 
	/**
	 * MÉTODO GET que me devuelve un fichero de una receta
	 * @param id
	 * @return
	 */
	 @GetMapping("/file/{id}")
	 public ResponseEntity<FileDB> getFileByRecipe(@PathVariable Integer id) {
		  FileDB file = this.storageService.getfileByRecipe(id);

		  return ResponseEntity.ok(file);
	 }
	 
	 
	 //GESTIÓN DE EXCEPCIONES
	 
	/**
	 * GESTIÓN DE EXCEPCIÓN FileNotFoundException
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción si el fichero no existe
	 */
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<ApiError> handleFileNotFound(FileNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

}
