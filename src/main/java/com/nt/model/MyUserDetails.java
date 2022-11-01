package com.nt.model;

import java.util.Collection;

import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MyUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
    
    public MyUserDetails(User user) {
        this.user = user;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*SimpleGrantedAuthority authority = new SimpleGrantedAuthority(getPassword())
		return Arrays.asList(authority);*/
		/*List<SimpleGrantedAuthority> authority=new ArrayList<SimpleGrantedAuthority>();
		for(String role:user.getRoles())
		{
			authority.add(new SimpleGrantedAuthority(role));
		}
		return authority;*/
		return user.getRoles().stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toSet());
		
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPwd();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUname();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
