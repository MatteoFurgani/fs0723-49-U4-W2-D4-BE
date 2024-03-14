import entities.Customer;
import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1L, "televisore", "elettronica", 700.0);
        Product product2 = new Product(2L, "cellulare samsung", "elettronica", 800.0);
        Product product3 = new Product(3L, "impianto stereo", "elettronica", 250.0);
        Product product4 = new Product(4L, "scrivania", "arredo", 125.0);
        Product product5 = new Product(5L, "letto", "arredo", 300.0);
        Product product6 = new Product(6L, "armadio", "arredo", 1200.0);
        Product product7 = new Product(7L, "bicicletta", "sport", 300.0);
        Product product8 = new Product(8L, "bilanceri", "sport", 75.0);
        Product product9 = new Product(9L, "palloni", "sport", 35.0);
        Product product10 = new Product(10L, "sci", "sport", 280.0);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);


        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer(1L, "Mario", 2);
        Customer customer2 = new Customer(2L, "Luigi", 1);
        Customer customer3 = new Customer(3L, "Rachele", 3);
        Customer customer4 = new Customer(4L, "Simona", 1);
        Customer customer5 = new Customer(5L, "Marco", 2);


        List<Order> orders = new ArrayList<>();
        Order order1 = new Order(1L, "In attesa", LocalDate.of(2024,3,13),
                LocalDate.of(2024,5,12), List.of(product1, product2), customer1);
        Order order2 = new Order(2L, "In lavorazione", LocalDate.of(2024,3,14),
                LocalDate.of(2024,4,2), List.of(product3, product4), customer2);
        Order order3 = new Order(3L, "Spedito", LocalDate.of(2024,3,10),
                LocalDate.of(2024,3,15), List.of(product5, product6), customer3);
        Order order4 = new Order(4L, "Consegnato", LocalDate.of(2024,2,5),
                LocalDate.of(2024,2,10), List.of(product7, product8), customer4);
        Order order5 = new Order(5L, "Consegnato", LocalDate.of(2024,2,29),
                LocalDate.of(2024,3,6), List.of(product9, product10), customer5);

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);




        //-------------------------------------ESERCIZIO1-------------------------------------

        System.out.println("--------------ESERCIZIO1--------------------");


        Map<Customer, List<Order>> ordersPerCustomer = orders.stream().collect(Collectors.groupingBy(Order::getCustomer));

        ordersPerCustomer.forEach((customer, customerOrders) -> {
            System.out.println("Cliente: " + customer.getName());
            System.out.println("Ordini: ");
            customerOrders.forEach(order -> System.out.println(order.getId()));
        });

        //-------------------------------------ESERCIZIO2-------------------------------------

        System.out.println("--------------ESERCIZIO2--------------------");

        Map<Customer, Double> saldoTotalePerCliente = orders.stream().collect(Collectors.groupingBy
                (Order::getCustomer, Collectors.summingDouble(order -> order.getProducts().stream()
                        .mapToDouble(Product::getPrice).sum())));

        saldoTotalePerCliente.forEach(((customer, saldoTotale) -> {
            System.out.println("Cliente: " + customer.getName() + " - Saldo totale: " + saldoTotale);
        } ));

        //-------------------------------------ESERCIZIO3-------------------------------------

        System.out.println("--------------ESERCIZIO3--------------------");

        Optional<Product> prodottoPiuCostoso = products.stream().max(Comparator.comparing(Product::getPrice));

        prodottoPiuCostoso.ifPresent(product ->
                System.out.println("Il prodotto piu costoso e': " + product.getName() + " a " + product.getPrice()));


        //-------------------------------------ESERCIZIO4-------------------------------------

        System.out.println("--------------ESERCIZIO4--------------------");

        OptionalDouble mediaPrezziOrdini = orders.stream().mapToDouble(order -> order.getProducts().stream()
                .mapToDouble(Product::getPrice).sum()).average();

        mediaPrezziOrdini.ifPresent(media -> System.out.println("La media dei prezzi degli ordini e': " + media));

        //-------------------------------------ESERCIZIO5-------------------------------------

        System.out.println("--------------ESERCIZIO5--------------------");

        Map<String, Double> totalePerCategoria = products.stream().collect(Collectors
                .groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));

        totalePerCategoria.forEach(((categoria, totale) -> System.out.println("La categoria " + categoria + " ha un totale di: " + totale)));



    }
}
