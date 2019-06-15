package teilar.cs4414202.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name="users")
public class User implements UserDetails {
	@Id
	@Column(length=45)
	private String username;
	
	@Column(length=45)
	@NotNull
	private String password;
	
	@Column(length=255,unique=true)
	@NotNull
	@Email
	private String email;
	
	@OneToMany(mappedBy="user")
	private List<GasStation> gastation;
	
	@OneToMany(mappedBy="user")
	private List<Order> orders;
	
	@Transient
	private List<String> roles = new ArrayList<>();
	
	public User() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<GasStation> getGastation() {
		return gastation;
	}

	public void setGastation(List<GasStation> gastation) {
		this.gastation = gastation;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		  return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}


    @Override
    public String getPassword() {
        return "{noop}"+this.password;//Το χρησιμοποιηούμε για να ξεγελάσουμε το spring security επειδή θέλει να είναι hash ο κωδικός
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	
	
	

}
