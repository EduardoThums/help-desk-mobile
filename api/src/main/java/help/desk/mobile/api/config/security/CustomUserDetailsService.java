package help.desk.mobile.api.config.security;

import help.desk.mobile.api.domain.entity.UserEntity;
import help.desk.mobile.api.repository.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		final UserEntity userEntity = getUser(() -> userRepository.findByUsernameAndDeletedFalse(username));

		return UserPrincipal.create(userEntity);
	}

	UserDetails loadUserById(Long id) {
		final UserEntity userEntity = getUser(() -> userRepository.findByIdAndDeletedFalse(id));

		return UserPrincipal.create(userEntity);
	}

	private UserEntity getUser(Supplier<Optional<UserEntity>> supplier) {
		return supplier.get().orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
	}

	public UserPrincipal getUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			return null;
		}

		return (UserPrincipal) authentication.getPrincipal();
	}
}
