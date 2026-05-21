package ed.lab.ed1labo04.service;

import ed.lab.ed1labo04.entity.Cart;
import ed.lab.ed1labo04.entity.CartItem;
import ed.lab.ed1labo04.entity.ProductEntity;
import ed.lab.ed1labo04.Model.CartItemRequest;
import ed.lab.ed1labo04.Model.CartRequest;
import ed.lab.ed1labo04.Repository.CartRepository;
import ed.lab.ed1labo04.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart create(CartRequest request) {

        List<CartItem> items = new ArrayList<>();

        double total = 0;

        for (CartItemRequest itemRequest : request.getCartItems()) {

            if (itemRequest.getQuantity() <= 0) {
                return null;
            }

            Optional<ProductEntity> optionalProduct =
                    productRepository.findById(itemRequest.getProductId());

            if (optionalProduct.isEmpty()) {
                return null;
            }

            ProductEntity product = optionalProduct.get();

            if (product.getQuantity() < itemRequest.getQuantity()) {
                return null;
            }

            product.setQuantity(
                    product.getQuantity() - itemRequest.getQuantity()
            );

            productRepository.save(product);

            CartItem item = new CartItem(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    itemRequest.getQuantity()
            );

            items.add(item);

            total += product.getPrice() * itemRequest.getQuantity();
        }

        Cart cart = new Cart();

        cart.setCartItems(items);
        cart.setTotalPrice(total);

        return cartRepository.save(cart);
    }

    public Cart getById(Long id) {

        Optional<Cart> cart = cartRepository.findById(id);

        return cart.orElse(null);
    }
}