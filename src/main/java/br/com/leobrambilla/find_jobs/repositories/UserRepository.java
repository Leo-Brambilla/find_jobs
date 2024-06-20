package br.com.leobrambilla.find_jobs.repositories;

import br.com.leobrambilla.find_jobs.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}