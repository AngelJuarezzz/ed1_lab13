package ed.lab.ed1labo04.Repository;

import ed.lab.ed1labo04.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}