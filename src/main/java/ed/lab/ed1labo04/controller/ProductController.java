package ed.lab.ed1labo04.controller;

import ed.lab.ed1labo04.entity.ProductEntity;
import ed.lab.ed1labo04.Model.CreateProductRequest;
import ed.lab.ed1labo04.Model.UpdateProductRequest;
import ed.lab.ed1labo04.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<ProductEntity> create(
            @RequestBody CreateProductRequest request
    ) {

        ProductEntity product = service.create(request);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getById(@PathVariable Long id) {

        ProductEntity product = service.getById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> update(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest request
    ) {

        ProductEntity product = service.update(id, request);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}