package project1.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project1.models.Cate;
@Repository
public interface CateDAO extends CrudRepository<Cate, Integer>
{
	//Log findByEmail(String nm);
	//Log findByUname(String nm);
	//User findById(int id);
	
	//User findByName(String nm);
	
}

































