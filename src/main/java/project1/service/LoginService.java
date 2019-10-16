package project1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project1.models.Regs;
import project1.repository.RegDAO;

@Service
public class LoginService {

	@Autowired
	RegDAO regDAO;

	public void registerUser(Regs regs) {
		regs.setUser("USER");
		regDAO.save(regs);
	}

	public boolean loginUser(Regs regs) {

		Regs regsEntity =regDAO.findByEmail(regs.getEmail());
		if(regsEntity == null)
			return false;
		if(regsEntity.getPwd().equals(regs.getPwd()))
			return true;
		return false;
	}
}
