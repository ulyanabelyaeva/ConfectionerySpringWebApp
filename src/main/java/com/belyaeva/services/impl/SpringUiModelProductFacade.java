package com.belyaeva.services.abstractions;

import org.springframework.ui.Model;

@Service
public class SpringUiModelProductFacade implements ProductFacade<Model, Model> {

    @Autowired
    private ProductTypeServiceImpl productTypeServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;


    public Model getProducts(Model model) {
        List<ProductType> productTypeList = productTypeServiceImpl.getProductTypeList();
        List<Product> productList = productServiceImpl.getAllProducts();

        User user = userServiceImpl.getTempUser();
        model.addAttribute("tempUser", user);
        model.addAttribute("productTypes", productTypeList);
        model.addAttribute("products", productList);

        return model
    }
}
