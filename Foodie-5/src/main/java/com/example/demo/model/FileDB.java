package com.example.demo.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files")
public class FileDB {
	
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String name;
  private String type;
  @Lob		
  private byte[] data;
  
  private static String PREFIX = "img";
  private static int COUNT = 0;
  
// @Lob --> LOB es un tipo de datos para almacenar datos de objetos grandes. Hay dos tipos de LOB: BLOB y CLOB:
//	  - BLOB es para almacenar datos binarios
//	  - CLOB es para almacenar datos de texto 


  
  public FileDB() {}
  
  public FileDB(String name, String type, byte[] data) {
    this.name = PREFIX+COUNT;
    this.type = type;
    this.data = data;
    FileDB.COUNT++;
  }
  
  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public byte[] getData() {
    return data;
  }
  public void setData(byte[] data) {
    this.data = data;
  }

@Override
public String toString() {
	return "FileDB [id=" + id + ", name=" + name + ", type=" + type + ", data=" + Arrays.toString(data) + "]";
}
  
  
}