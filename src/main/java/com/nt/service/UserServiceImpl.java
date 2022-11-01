package com.nt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.nt.model.MyUserDetails;
import com.nt.model.User;
import com.nt.repo.IUserRepo;
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	

	@Override
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPwd(encoder.encode(user.getPwd()));
		return "user saved with id  "+repo.save(user).getUnid()+" ";
	}



	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Optional<User> user = repo.getUserByUsername(username);
        
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Could not find user");
        }
        //UserDetails details=new MyUserDetails(user);
        return new MyUserDetails(user.get());
       // return null;
	}

}
