package project1.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project1.models.Regs;
import project1.repository.RegDAO;

@Service
public class AdminService {

	@Autowired
	RegDAO regDao;

	public boolean findByMail(Regs reg) {
		Regs regs = regDao.findByEmail(reg.getEmail());
		if(regs==null)
			return false;
		if (regs.getPwd().equals(reg.getPwd()) && regs.getUser().equals("ADMIN"))
			return true;
		else
			return false;

	}

	@PostConstruct
	public void createAdmin() {
		Regs reg = regDao.findByEmail("admin@gmail.com");
		if (reg == null) {
			reg = new Regs();
			reg.setCmd("admin");
			reg.setEmail("admin@gmail.com");
			reg.setPwd("admin");
			reg.setUname("admin");
			reg.setUser("ADMIN");
			regDao.save(reg);
		}
	}
}
