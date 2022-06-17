package com.example.demo.repository;
/**
 * Encargada de la persistencia de datos de Notification
 * @author estefgar
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {
	/**
	 * CONSULTA para eliminar al usuario de una notificación cuyo id se pasa por parámetro
	 * @param id
	 */
	@Query(value="delete from user_notifications where notifications_id = ?1", nativeQuery = true)
	void deleteNotificationFK(Integer id);

}
