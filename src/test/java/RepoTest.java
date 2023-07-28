import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepoTest {

    Product product1 = new Product(7, "зубная паста", 150);
    Product product2 = new Product(37, "мыло", 100);
    Product product3 = new Product(77, "шампунь", 250);


    @Test
    public void removeExistingElement() {

        ShopRepository shopRepository = new ShopRepository();

        shopRepository.add(product1);
        shopRepository.add(product2);
        shopRepository.add(product3);

        shopRepository.removeById(77);

        Product[] expected = {product1, product2};
        Product[] actual = shopRepository.findAll();

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void notFoundExceptionTest() {

        ShopRepository shopRepository = new ShopRepository();

        shopRepository.add(product1);
        shopRepository.add(product2);
        shopRepository.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            shopRepository.removeById(400);
        });
    }

    @Test
    public void addProduct() {

        ShopRepository shopRepository = new ShopRepository();

        shopRepository.add(product1);
        shopRepository.add(product2);
        shopRepository.add(product3);

        Product[] expected = {product1, product2, product3};
        Product[] actual = shopRepository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addProductWithDuplicateId() {

        ShopRepository shopRepository = new ShopRepository();

        Product product4 = new Product(77, "шампунь", 250);

        shopRepository.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            shopRepository.add(product4);
        });
    }

}