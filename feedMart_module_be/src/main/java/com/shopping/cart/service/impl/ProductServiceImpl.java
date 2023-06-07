package com.shopping.cart.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shopping.cart.entity.Product;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> searchProducts(String query) {
        List<Product> products = productRepository.searchProductsSQL(query);
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

	@Override
	public List<Product> createAllProduct(List<Product> products) {
		return productRepository.saveAll(products);
	}
	
	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}
	

	@Override
	public Product getProductById(Long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.orElse(null);
	}

	@Override
	public Product findById(Long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.orElse(null);
	}

//	@Override
//	public List<Product> createAllProduct(List<Product> products) {
//		return productRepository.saveAll(products);
//	}
}
