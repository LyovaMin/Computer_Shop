package by.lyofchik.AppSpring.Controller.Web;

import by.lyofchik.AppSpring.Filter.ProductFilter;
import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Model.Entities.Role;
import by.lyofchik.AppSpring.Model.Entities.Sale;
import by.lyofchik.AppSpring.Model.Entities.User;
import by.lyofchik.AppSpring.Service.ProductsService.ProductService;
import by.lyofchik.AppSpring.Service.SaleService.SaleService;
import by.lyofchik.AppSpring.Service.UserService.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/shop")
public class ShopController {
    ProductService productService;
    SaleService saleService;
    UserService userService;
    List<Product> productsCart;

    @GetMapping
    public String shop(Model model, Authentication authentication) {
        model.addAttribute("products", productService.findAll());
        User user = userService.find(authentication.getName()).orElseThrow();
        model.addAttribute("user", user);

        log.info("user logged in {}", authentication.getAuthorities());
        return "shopPages/shopMain";
    }

    @GetMapping("/admin")
    public String admin(){
        return "redirect:/admin";
    }

    @GetMapping("/cart")
    public String shopCart(Model model) {
        model.addAttribute("products", productsCart);
        return "shopPages/Cart";
    }

    @PostMapping("/buy")
    public String buy(@RequestParam String productName, Model model) {
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.find(authName).orElseThrow();
        Product product = productService.find(productName).orElseThrow();

        saleService.save(Sale.builder()
                .product(product)
                .user(user)
                .saleDate(LocalDate.now())
                .build());
        model.addAttribute("product", product);
        return "shopPages/Hello";
    }

    @PostMapping("/cart/add/{productName}")
    public String cart(@PathVariable String productName) {
        productsCart.add(productService.find(productName).orElse(null));
        return "redirect:/shop/cart";
    }

    @PostMapping("/cart/buyAll")
    public String cartBuyAll() {
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.find(authName).orElseThrow();

        for (Product product : productsCart) {
            saleService.save(
                    Sale.builder()
                            .product(product)
                            .user(user)
                            .saleDate(LocalDate.now())
                            .build()
            );
        }
        productsCart.clear();
        return "shopPages/Hello";
    }

    @Transactional
    @DeleteMapping("/delete")
    public void delete(Product product) {
        productService.delete(product);
    }
}
