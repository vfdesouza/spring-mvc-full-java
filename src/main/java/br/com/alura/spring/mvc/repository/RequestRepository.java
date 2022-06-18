package br.com.alura.spring.mvc.repository;

import br.com.alura.spring.mvc.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByProductNameIgnoreCaseContaining(String productName);
}
