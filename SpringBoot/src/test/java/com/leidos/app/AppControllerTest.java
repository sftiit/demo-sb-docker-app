package com.leidos.app;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.leidos.app.entity.AppEntity;
import com.leidos.app.repository.AppRepository;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AppControllerTest {

	@Autowired
	AppRepository appRepository;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Test
	@Rollback(false)
	public void createRow() {
		AppEntity appEntity = appRepository.save(new AppEntity(id, "cloud4", "sekar4", "sekar4@gmail.com", true));
		assertThat(appEntity.getId());
	}
	
	
	@Test
	public void getAValue() {
		AppEntity appEntity = appRepository.getOne(new Long(100));
		assertThat(appEntity.getMember().equalsIgnoreCase("sekar1"));
	}
	

}
