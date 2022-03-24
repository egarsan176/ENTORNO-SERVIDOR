package com.example.demo.message;


/**
 * El controlador usará esta clase para enviar mensajes de respuesta vía HTTP
 * Esta clase se usará para la notificación/información del mensaje
 */

public class ResponseMessage {
	
	  private String message;
	  public ResponseMessage(String message) {
	    this.message = message;
	  }
	  public String getMessage() {
	    return message;
	  }
	  public void setMessage(String message) {
	    this.message = message;
	  }
	}
