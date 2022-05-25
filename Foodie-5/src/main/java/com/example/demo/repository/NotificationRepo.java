package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {
	
	@Query(value="delete from user_notifications where notifications_id = ?1", nativeQuery = true)
	void deleteNotificationFK(Integer id);

}
