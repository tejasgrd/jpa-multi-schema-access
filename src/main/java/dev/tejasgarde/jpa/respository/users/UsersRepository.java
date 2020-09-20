package dev.tejasgarde.jpa.respository.users;

import dev.tejasgarde.jpa.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
