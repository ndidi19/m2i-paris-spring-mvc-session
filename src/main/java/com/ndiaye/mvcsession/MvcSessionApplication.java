package com.ndiaye.mvcsession;

import com.ndiaye.mvcsession.entity.Product;
import com.ndiaye.mvcsession.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class MvcSessionApplication implements CommandLineRunner {


	private final ProductRepository productRepository;

	public MvcSessionApplication(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MvcSessionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product p1 = new Product("Jordan 5", "Brand bew Jordan 5", new BigDecimal("99.00"));
		Product p2 = new Product("Supra Skytop", "Supra Skytop 2022", new BigDecimal("89.00"));
		Product p3 = new Product("Levi's 501", "Jeans Levi's 501", new BigDecimal("129.00"));

		productRepository.saveAll(List.of(p1, p2, p3));
	}
}
