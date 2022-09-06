package br.com.jeferson.userdpt.repositories;

import br.com.jeferson.userdpt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
