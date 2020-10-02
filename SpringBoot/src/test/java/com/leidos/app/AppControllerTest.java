package com.leidos.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.leidos.app.entity.AppEntity;
import com.leidos.app.repository.AppRepository;

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
		String row = readTestFile();
		if (row != null) 
		{
			String[] items = row.split(",");
			AppEntity appEntity = appRepository.save(new AppEntity(id, items[0].toString(), items[1].toString(), 
					items[2].toString(), Integer.parseInt(items[3].toString()) == 1 ? true : false));
			assertThat(appEntity.getId());
		}
	}
	
	
	@Test
	public void getAValue() {
		AppEntity appEntity = appRepository.getOne(new Long(1000));
		assertThat(appEntity.getMember().equalsIgnoreCase("sekar"));
	}
	
	public String readTestFile() {
		String fileName = "testdata.txt";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream("/" + fileName)));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
