package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URISyntaxException;
import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entity.Item;
import com.example.demo.repo.ItemRepository;

@ActiveProfiles("test") // schaltet auf H2 Database um, config siehe application.properties
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MainControllerTest {

	
	private static final String BASE_PATH = "/api/v1/items";

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ItemRepository repo;

	@BeforeEach
	void setupRepo() {
		repo.deleteAll();
	}

	@Test
	void shouldBeAbleToUploadAnItem() {
		
		System.out.println("********** port ********" + port);

		// Given | Arrange
		// When | Act
		ResponseEntity<Item> response = givenAnInsertedItem();
		// Then | Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody().getId()).isNotNull();
	}

	@Test
	void shouldReadAllItems() {
		fail();
	}

	@Test
	void shouldFindOneItem() {
		// Given | Arrange
		Item lawnMower = givenAnInsertedItem().getBody();
		// When | Act
		ResponseEntity<Item> response = restTemplate.getForEntity(BASE_PATH + "/" + lawnMower.getId(), Item.class);
		// Then | Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualToComparingFieldByField(lawnMower);
	}

	
	@Test
	void shouldFindNoItemForUnknownId() throws URISyntaxException {
		fail();
	}

	@Test
	void shouldBeAbleToDeleteAnItem() throws URISyntaxException {
		fail();
	}

	@Test
	void shouldNotBeAbleToDeleteAnItemWithUnknownId() throws URISyntaxException {
		fail();
	}

	@Test
	void shouldBeAbleToReplaceAnItem() throws URISyntaxException {
		fail();
	}

	@Test
	void shouldNotBeAbleToReplaceAnItemWithUnknownId() throws URISyntaxException {
		fail();
	}

	private ResponseEntity<Item> givenAnInsertedItem() {
		Item item = buildLawnMower();
		return restTemplate.postForEntity(BASE_PATH, item, Item.class);
	}

	private Item buildLawnMower() {
		Item item = Item.builder().name("Lawn mower").amount(1).lastUsed(Date.valueOf("2019-05-01"))
				.location("Basement").build();
		return item;
	}

	private Item buildLawnTrimmer() {
		Item item = Item.builder().name("Lawn trimmer").amount(1).lastUsed(Date.valueOf("2018-05-01"))
				.location("Basement").build();
		return item;
	}
}
