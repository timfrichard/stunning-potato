package com.pubsub.demo.publisher.activemq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pubsub.demo.publisher.activemq.models.Company;
import com.pubsub.demo.publisher.activemq.models.Department;
import com.pubsub.demo.publisher.activemq.models.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringActiveMqTopicProducerApplication /*implements CommandLineRunner*/ {

//    @Autowired
//    JmsPublisher publisher;

    public static void main(final String[] args) {
        SpringApplication.run(SpringActiveMqTopicProducerApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) throws JsonProcessingException {

        final ObjectMapper objectMapper = new ObjectMapper();
        final Company honda = new Company();
        honda.setName("Honda");
        final List<Product> products = new ArrayList<>();
        honda.setProducts(products);
        final Product crv = new Product();
        crv.setCompany(honda);
        crv.setName("CRV");
        products.add(crv);

        final Product accord = new Product();
        accord.setCompany(honda);
        accord.setName("Accord");
        products.add(accord);

        final Product civic = new Product();
        civic.setCompany(honda);
        civic.setName("CIVIC");
        products.add(civic);

        final List<Department> departments = new ArrayList<>();
        honda.setDepartments(departments);
        departments.add(Department.builder().company(honda).name("Pre Owned Sales").build());
        departments.add(Department.builder().company(honda).name("New Sales").build());
        departments.add(Department.builder().company(honda).name("Service").build());

        System.out.println(objectMapper.writeValueAsString(honda));
        return null;
//                args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            final String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (final String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
    }
//	@Override
//	public void run(String... args) throws Exception {
//		/*
//		 * Apple company & products
//		 */
//		// initial company and products
//		Product iphone7 = new Product("Iphone 7");
//		Product iPadPro = new Product("IPadPro");
//
//		List<Product> appleProducts = new ArrayList<Product>(Arrays.asList(iphone7, iPadPro));
//
//		Company apple = new Company("Apple", appleProducts);
//
//		iphone7.setCompany(apple);
//		iPadPro.setCompany(apple);
//
//		// send message to ActiveMQ
//		publisher.send(apple);
//
//        /*
//         * Samsung company and products
//         */
//		Product galaxyS8 = new Product("Galaxy S8");
//		Product gearS3 = new Product("Gear S3");
//
//		List<Product> samsungProducts = new ArrayList<Product>(Arrays.asList(galaxyS8, gearS3));
//
//		Company samsung = new Company("Samsung", samsungProducts);
//
//		galaxyS8.setCompany(samsung);
//		gearS3.setCompany(samsung);
//
//        /*
//         * send message to ActiveMQ
//         */
//		publisher.send(samsung);
//	}
}
