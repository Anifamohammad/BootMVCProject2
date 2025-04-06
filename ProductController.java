package com.spring.java.controller;

import java.util.List;  
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.java.entity.ProductEntity;
import com.spring.java.productmodel.ProductModel;
import com.spring.java.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller

public class ProductController {
	@Autowired
	ProductService productService;
	
	
	@ GetMapping("/wish")
	public String greet() {
		return "hello";
	}
	@GetMapping("/thanks")
	public String good() {
		return "hii";
		
	}
	
//	  @GetMapping("/productform") 
//	  public String getForm() { 
//		  return "add-product"; }
//	 
//	
	
	@GetMapping("/productform")
	public String getForm(Model model) 
	{
		ProductModel productModel = new  ProductModel();
		productModel.setPrice(100000);
		productModel.setName("wheet");
		
		model.addAttribute("productModel",productModel);
		return "add-product";
	}
	
	
	
	@PostMapping("/saveproduct")
	public String saveProductData(@Valid @ModelAttribute ProductModel productModel,BindingResult bindingResult) 
	{
		if(bindingResult.hasErrors()) {
			return "add-product";
		}
		productService.saveProduct(productModel);
		return "success";
	}
	
	@GetMapping("/getAllProducts")
	public String getAllProducts(Model model)
	{
		List<ProductEntity> products =productService.getAllProducts();
		model.addAttribute("products", products);
		
		return "product-list";
		
	}
	
//	@GetMapping("/getProduct/{id}")
//	public String getProductById(@PathVariable Long id,Model model)
//			{
//		Optional<ProductEntity> product=productService.getProducts(id);
//		
//		if(product.isPresent())
//		{
//			ProductEntity productEntity=product.get();
//		
//		model.addAttribute("product",productEntity);
//		}
//		else
//		{
//			model.addAttribute("error_message","product with id"+id+"not fount ");
//		}
//		return "id-table";
//		
//			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/getProduct/{id}")
	public String getProductById(@PathVariable Long id, Model model) {
	    ProductEntity product = productService.getProductById(id).orElse(null); 
	    model.addAttribute("product", product); 
	    
	    return "basedonid"; 
	}

	
	

	   


@GetMapping("/deleteProduct/{id}")
public String deleteProduct(@PathVariable("id") Long id) { 
    productService.deleteProduct(id);
    return "redirect:/getAllProducts"; // Redirect to the product list after deletion
}



//@GetMapping("/editProduct/{id}")
//public String editProduct(@PathVariable long id, Model model) { 
//    Optional<ProductEntity> product = productService.getProductById(id);
//    if (product != null) {
//        model.addAttribute("product", product);
//        return "edit";  
//    } else {
//        return "redirect:/getAllProducts";
//    }
//    
//   



@GetMapping("/editproduct/{id}")
public String editProduct(@PathVariable("id") Long id, Model model) {
    ProductModel products = productService.getEditProduct(id);
    model.addAttribute("products", products);
    model.addAttribute("id", id);
    return "editProduct"; 
}


    
    
    
    
    

@PostMapping("/updateProduct/{id}")
public String updateProduct(@PathVariable Long id, @ModelAttribute ProductModel productModel) {
    
   
   productService.updateProduct(id, productModel); 
   return "redirect:/getAllProducts"; 
}
}




