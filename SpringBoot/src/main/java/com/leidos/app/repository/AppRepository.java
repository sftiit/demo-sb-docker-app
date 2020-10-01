package com.leidos.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leidos.app.entity.AppEntity;

@Repository
public interface AppRepository extends JpaRepository<AppEntity, Long>{
	
	List<AppEntity> findByName(String name);
	List<AppEntity> findByBi(boolean bi);

}
