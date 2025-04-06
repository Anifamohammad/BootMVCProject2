package com.spring.java.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.java.entity.ProductEntity;
import com.spring.java.productmodel.ProductModel;
import com.spring.java.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	// Save product logic with price * quantity calculation
	public void saveProduct(ProductModel productModel) {
		ProductEntity productEntity = new ProductEntity();
		double price = productModel.getPrice();
		int quantity = productModel.getQuantity();

		// Calculate tax (10% of price)
		double taxAmount = price * 10 / 100;

		// Calculate total price (price * quantity)
		double totalPrice = price * quantity+taxAmount;

		// Set values for the product entity
		productEntity.setName(productModel.getName());
		productEntity.setPrice(price);
		productEntity.setQuantity(quantity);
		productEntity.setBrand(productModel.getBrand());
		productEntity.setMadein(productModel.getMadein());
		productEntity.setTaxAmount(taxAmount);
		productEntity.setTotalAmount(totalPrice);
		productEntity.setCreatedAt(LocalDateTime.now());
		productEntity.setCreatedBy(System.getProperty("user.name"));

		productRepository.save(productEntity);
	}

	public List<ProductEntity> getAllProducts() {
		return productRepository.findAll();
	}

	// getproduct by id

//    public Optional<ProductEntity> getProducts(Long id) {
//		// TODO Auto-generated method stub
//	Optional<ProductEntity> product=productRepository.findById(id);
//		return product;
//	}
//
//    
	public Optional<ProductEntity> getProductById(Long id) {
		return productRepository.findById(id);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

//  //recalculate the price 
//  double totalAmount=productModel.getPrice()* productModel.getQuantity();
//  double tax=totalAmount *10/100;
//  productModel.setTotalAmount(totalAmount); 
//  productModel.setTaxAmount(tax);
//  product.setCreatedAt(LocalDateTime.now());
//  product.setCreatedBy(System.getProperty("user.name")); 

	public ProductModel getEditProduct(Long id) {
        
        ProductEntity productEntity = productRepository.findById(id).get();
//               change the productModel
        
        ProductModel productModel=new ProductModel();
        
                

        // Update the fields of the existing product
        productModel.setName(productEntity.getName());
        productModel.setBrand(productEntity.getBrand());
        productModel.setPrice(productEntity.getPrice());
        productModel.setQuantity(productEntity.getQuantity());
        productModel.setMadein(productEntity.getMadein());
        
        
       

        // Save the updated product
        return productModel;
        }

//    
//  public ProductEntity updateProduct(ProductEntity productEntity) {
//        return productRepository.save(productEntity);
//  }

// Method to update product
//    public void updateProduct(Long id, ProductModel productModel) {
//        ProductEntity productEntity = productRepository.findById(id)
//            .orElseThrow();
//
//        // Update the product entity with the values from productModel
//        productEntity.setName(productModel.getName());
//        productEntity.setBrand(productModel.getBrand());
//        productEntity.setPrice(productModel.getPrice());
//        productEntity.setQuantity(productModel.getQuantity());
//        productEntity.setMadein(productModel.getMadein());
//        
//        // Save the updated product entity
//        productRepository.save(productEntity);
//    }
//}


	    public void updateProduct(Long id, ProductModel productModel) {
	            
	            ProductEntity productEntity = productRepository.findById(id).orElse(null);
	            if(productEntity!=null) {
	                    

	            // Update the fields of the existing product
	            productEntity.setName(productModel.getName());
	            productEntity.setBrand(productModel.getBrand());
	            productEntity.setPrice(productModel.getPrice());
	            productEntity.setQuantity(productModel.getQuantity());
	            productEntity.setMadein(productModel.getMadein());
	            //recalculate the price 
	            double totalAmount=productEntity.getPrice()* productEntity.getQuantity();
	            double tax=totalAmount *10/100;
	            productEntity.setTotalAmount(totalAmount); 
	            productEntity.setTaxAmount(tax);
	            productEntity.setCreatedAt(LocalDateTime.now());
	            productEntity.setCreatedBy(System.getProperty("user.name")); 


	            // Save the updated product
	            productRepository.save(productEntity);
	            }
	        }
	    }

