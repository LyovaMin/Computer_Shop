package by.lyofchik.AppSpring.Controller.Web;

import by.lyofchik.AppSpring.Model.DTO.EmailRequest;
import by.lyofchik.AppSpring.Model.Entities.Category;
import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Service.CategoryService.CategoryService;
import by.lyofchik.AppSpring.Service.MailService.MailService;
import by.lyofchik.AppSpring.Service.ProductsService.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    MailService mailService;
    ProductService productService;
    CategoryService categoryService;

    @GetMapping("/mail")
    public String admin(Model model,
                        @Nullable @ModelAttribute EmailRequest emailRequest) {
        model.addAttribute("emailRequest", emailRequest);
        return "admin/adminMainPage";
    }

    @PostMapping("/send")
    public String adminPost(@Validated @ModelAttribute EmailRequest emailRequest,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> log.info(error.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("emailRequest", emailRequest);
            return "redirect:/admin/mail";
        }
        mailService.send(emailRequest);
        return "redirect:/admin/mail";
    }

    @GetMapping
    public String productManagementPage(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("newProduct", new Product());
        return "admin/product";
    }

    @PostMapping("products/add")
    public String addProduct(@ModelAttribute Product product, @RequestParam int categoryId) {
        Category category = categoryService.find(categoryId).orElse(null);
        product.setCategory(category);
        productService.save(product);
        return "redirect:/admin";
    }

    @PostMapping("products/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        Product product = productService.findById(id);
        if (product != null) {
            productService.delete(product);
        }
        return "redirect:/admin";
    }
}
