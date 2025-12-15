package by.lyofchik.AppSpring.Controller.Web;

import by.lyofchik.AppSpring.Filter.ProductFilter;
import by.lyofchik.AppSpring.Model.DTO.PageResponse;
import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Model.Entities.Role;
import by.lyofchik.AppSpring.Model.Entities.Sale;
import by.lyofchik.AppSpring.Model.Entities.User;
import by.lyofchik.AppSpring.Service.ProductsService.ProductService;
import by.lyofchik.AppSpring.Service.SaleService.SaleService;
import by.lyofchik.AppSpring.Service.UserService.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXB;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public String shop(Model model,
                       Authentication authentication,
                       ProductFilter filter,
                       Pageable pageable) {
        Page<Product> page = productService.findAll(filter, pageable);
        model.addAttribute("products", PageResponse.of(page));

        model.addAttribute("user", authentication);
        model.addAttribute("filter", filter);

        log.info("user logged in {}", authentication);
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
    public String buy(@RequestParam String productName,
                      Model model) {
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.find(authName).orElseThrow();
        Product product = productService.find(productName).orElseThrow();
        List<Product> purchasedProducts = new ArrayList<>();
        purchasedProducts.add(product);
        double totalAmount = product.getPrice();

        saleService.save(Sale.builder()
                .product(product)
                .user(user)
                .saleDate(LocalDate.now())
                .build());

        model.addAttribute("purchasedProducts", purchasedProducts);
        model.addAttribute("user", user);
        model.addAttribute("totalAmount", totalAmount);
        return "shopPages/Checkout";
    }

    @PostMapping("/cart/add/{productName}")
    public String cart(@PathVariable String productName) {
        productsCart.add(productService.find(productName).orElse(null));
        return "redirect:/shop/cart";
    }

    @PostMapping("/cart/buyAll")
    public String cartBuyAll(Model model) {
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.find(authName).orElseThrow();
        List<Product> purchasedProducts = new ArrayList<>(productsCart);
        double totalAmount = purchasedProducts.stream()
                .mapToDouble(Product::getPrice)
                .sum();

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

        model.addAttribute("purchasedProducts", purchasedProducts);
        model.addAttribute("user", user);
        model.addAttribute("totalAmount", totalAmount);
        return "shopPages/Checkout";
    }

    @Transactional
    @DeleteMapping("/delete")
    public void delete(Product product) {
        productService.delete(product);
    }
}
