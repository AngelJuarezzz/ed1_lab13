package ed.lab.ed1labo04.service;

import ed.lab.ed1labo04.entity.ProductEntity;
import ed.lab.ed1labo04.Model.CreateProductRequest;
import ed.lab.ed1labo04.Model.UpdateProductRequest;
import ed.lab.ed1labo04.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductEntity create(CreateProductRequest request) {

        if (request.getPrice() <= 0) {
            return null;
        }

        ProductEntity product = new ProductEntity(
                request.getName(),
                request.getPrice(),
                0
        );

        return repository.save(product);
    }

    public List<ProductEntity> getAll() {
        return repository.findAll();
    }

    public ProductEntity getById(Long id) {

        Optional<ProductEntity> product = repository.findById(id);

        return product.orElse(null);
    }

    public ProductEntity update(Long id, UpdateProductRequest request) {

        Optional<ProductEntity> optionalProduct = repository.findById(id);

        if (optionalProduct.isEmpty()) {
            return null;
        }

        if (request.getPrice() <= 0 || request.getQuantity() < 0) {
            return null;
        }

        ProductEntity product = optionalProduct.get();

        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return repository.save(product);
    }
}