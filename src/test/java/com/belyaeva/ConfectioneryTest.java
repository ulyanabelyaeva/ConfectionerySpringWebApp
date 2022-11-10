package com.belyaeva;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ConfectioneryTest {
    @Autowired
    private MockMvc mockMvc; //мок на запросы

    //тест на получение страницы пользователя, которая требует авторизации
    //ожидается ошибка при попытке получения страницы и перенаправление на страницу авторизации
    @Test
    public void getUserPage() throws Exception {
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    //тест на аутентификацию пользователя c корректными данными
    //пользователь после успешной аутентификации должен перенаправиться на страницу пользователя
    @Test
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void login() throws Exception {
        this.mockMvc.perform(formLogin().user("89185555555").password("maria"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));
    }

    //тест на аутентификацию пользователя c некорректными данными
    //пользователь после неудавшейся аутентификации должен перенаправиться на страницу аутентификации
    @Test
    public void badLogin() throws Exception {
        this.mockMvc.perform(formLogin().user("89180000000").password("maria"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }

    //тест на авторизацию пользователя с корректными данными
    @Test
    public void registration() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("firstName","Ира");
        params.add("phone","89184444444");
        params.add("password","ira");
        params.add("passwordConfirm","ira");
        this.mockMvc.perform(post("/reg").params(params))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    //тест на авторизацию пользователя с некорректными данными
    @Test
    public void badRegistration() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("firstName","Ира");
        params.add("phone","89184444444");
        params.add("password","ira");
        params.add("passwordConfirm","i");
        this.mockMvc.perform(post("/reg").params(params))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reg"));
    }
}
