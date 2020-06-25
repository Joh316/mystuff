package com.example.demo.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
   Challenge: Minimales Backend mit Java Spring Boot für eine REST-basierte CRUD-Anwendung

		Aufgabe:
		Spring Boot-basiertes REST-Backend für "Item"-Resourcen erstellen
		Persistenz: Spring Data JPA / MySQL
		Bereitstellung des Codes in eigenem GitHub-Repo
		Nachweis der Funktion durch Beispiel-Requests (Swagger)
		Einfügen von 3 Beispieldatensätzen per DevBootstrap-Klasse (ApplicationListener)
		Zunächst keine Security notwendig
*/

@Getter
@Setter
@Builder
@NoArgsConstructor // ohne funktioniert JPA nicht
@AllArgsConstructor
@Entity
public class Item {

	// Attribute
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private int amount;

	private String location;

	private String description;

	private Date lastUsed;
	
	
}
