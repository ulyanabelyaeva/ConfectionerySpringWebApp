package com.belyaeva;

import com.belyaeva.entity.CartItem;
import com.belyaeva.entity.Product;
import com.belyaeva.services.CartItemService;
import com.belyaeva.services.CartService;
import com.belyaeva.services.ProductService;
import com.belyaeva.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("89185555555")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/create-product-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql", "/create-product-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class FunctionTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private MockMvc mockMvc;

    //фильтр по типу десерта
    @Test
    public void filterByType() throws Exception {
        this.mockMvc.perform(get("/catalog/2"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='cards']/div").nodeCount(2));
    }

    //добавление товара в корзину
    @Test
    public void addToCart(){
        int before = cartService.getCartByUserId(userService.getTempUser().getId()).getItems().size();
        Product product = productService.getProductById(1L);
        CartItem cartItem = new CartItem(product, cartService.getCartByUserId(userService.getTempUser().getId()));
        cartItemService.addNewItem(cartItem);
        int after = cartService.getCartByUserId(userService.getTempUser().getId()).getItems().size();

        Assertions.assertSame(before +  1, after);
    }

    //оплата заказа
    @Test
    public void payOrder() throws Exception {
        Product product = productService.getProductById(1L);
        CartItem cartItem = new CartItem(product, cartService.getCartByUserId(userService.getTempUser().getId()));
        cartItemService.addNewItem(cartItem);

        this.mockMvc.perform(post("/user/cart").param("btn", "pay").with(csrf()))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/cart"));

        this.mockMvc.perform(get("/user/cart"))
                .andDo(print())
                .andExpect(xpath("//*[@id='item_order']").nodeCount(0));
    }

}
