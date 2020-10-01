package com.leidos.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leidos.app.entity.AppEntity;
import com.leidos.app.repository.AppRepository;

@RestController
@RequestMapping("/api")
public class AppController {
	
	@Autowired
	AppRepository appRepository;

	
	@PostMapping("/teams")
	public ResponseEntity<AppEntity> createTeam(@RequestBody AppEntity team) {
		try {
			AppEntity teamRec = appRepository
					.save(new AppEntity(team.getId(), team.getName(), team.getMember(), team.getEmail(), false));
			return new ResponseEntity<>(teamRec, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/teams")
	public ResponseEntity<List<AppEntity>> getAllTeam(@RequestParam(required = false) String name) {
		try {
			List<AppEntity> allTeam = new ArrayList<AppEntity>();

			if (name == null)
				appRepository.findAll().forEach(allTeam::add);
			else
				appRepository.findByName(name).forEach(allTeam::add);

			return new ResponseEntity<>(allTeam, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/team/{id}")
	public ResponseEntity<AppEntity> getTeamById(@PathVariable("id") long id) {
		Optional<AppEntity> teamRec = appRepository.findById(id);
		

		if (teamRec.isPresent()) {
			return new ResponseEntity<>(teamRec.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
}
