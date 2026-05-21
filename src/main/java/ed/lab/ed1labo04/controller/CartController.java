package ed.lab.ed1labo04.controller;

import ed.lab.ed1labo04.entity.Cart;
import ed.lab.ed1labo04.Model.CartRequest;
import ed.lab.ed1labo04.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @PostMapping
    public ResponseEntity<Cart> create(
            @RequestBody CartRequest request
    ) {

        Cart cart = service.create(request);

        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getById(@PathVariable Long id) {

        Cart cart = service.getById(id);

        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}