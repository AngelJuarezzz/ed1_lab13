package ed.lab.ed1labo04.Repository;

import ed.lab.ed1labo04.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}