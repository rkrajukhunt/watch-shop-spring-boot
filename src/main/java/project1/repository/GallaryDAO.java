package project1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project1.models.Gallary;

@Repository
public interface GallaryDAO extends CrudRepository<Gallary,Integer> {

}
