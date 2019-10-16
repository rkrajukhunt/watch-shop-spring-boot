package project1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project1.models.Regs;
@Repository
public interface RegDAO extends JpaRepository<Regs, Integer>
{
	Regs findByEmail(String nm);
	Regs findByUname(String nm);
	//User findById(int id);
	
	//User findByName(String nm);
	
}

































