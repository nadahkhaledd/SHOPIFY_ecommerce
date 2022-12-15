import helpers.HelperMethods;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.utility.ProductsUtils;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class ProductUtilsTest {
    private ProductsUtils productsUtils;
    private HelperMethods helperMethods;

    public ProductUtilsTest() {
        productsUtils=new ProductsUtils();
        helperMethods=new HelperMethods();
    }

    private List<Product> initFirstListOfProducts(){
            List<Product> list = new ArrayList<>();
            Category womenDresses=new Category();
            womenDresses.setName("women's dresses");
            womenDresses.setImagePath("dummy pic");
            womenDresses.setId(1);
            Product product1=new Product("tshirt","dummy pic",120.0,womenDresses,12);
            Product product2=new Product("red tshirt","dummy pic",120.0,womenDresses,1);
            list.add(product1);
            list.add(product2);
            womenDresses.setProducts(list);
            return list;
        
    }
    private List<Product> initSecondListOfProducts(){//return different list of products than the above method
        List<Product> list = new ArrayList<>();
        Category kidsDresses=new Category();
        kidsDresses.setName("kid's dresses");
        kidsDresses.setImagePath("dummy pic");
        kidsDresses.setId(1);
        Product product1=new Product("kids tshirt","dummy pic",170.0,kidsDresses,12);
        Product product2=new Product("kids short","dummy pic",60.0,kidsDresses,1);
        list.add(product1);
        list.add(product2);
        kidsDresses.setProducts(list);
        return list;

    }
    private Set<Product> initMergedSetOfProducts(){//return different list of products than the above method
        Set<Product> productsSet = new HashSet<>();
        Category womenDresses=new Category();
        womenDresses.setName("women's dresses");
        womenDresses.setImagePath("dummy pic");
        womenDresses.setId(1);
        Product product1=new Product("tshirt","dummy pic",120.0,womenDresses,12);
        Product product2=new Product("red tshirt","dummy pic",120.0,womenDresses,1);
        Category kidsDresses=new Category();
        kidsDresses.setName("kid's dresses");
        kidsDresses.setImagePath("dummy pic");
        kidsDresses.setId(1);
        Product product3=new Product("kids tshirt","dummy pic",170.0,kidsDresses,12);
        Product product4=new Product("kids short","dummy pic",60.0,kidsDresses,1);
        productsSet.add(product1);
        productsSet.add(product2);
        productsSet.add(product3);
        productsSet.add(product4);
        return productsSet;

    }
    private Set<Product> initMergedSetOfProductsAndCategoryProducts(){
        Set<Product> productsSet = new HashSet<>();
        Category kidsDresses=new Category();
        kidsDresses.setName("kid's dresses");
        kidsDresses.setImagePath("dummy pic");
        Category womenDresses=new Category();
        womenDresses.setName("women's dresses");
        womenDresses.setImagePath("dummy pic");
        Product product1=new Product("kids tshirt","dummy pic",170.0,kidsDresses,12);
        Product product2=new Product("kids short","dummy pic",60.0,kidsDresses,1);
        Product product3=new Product("tshirt","dummy pic",120.0,womenDresses,12);
        Product product4=new Product("red tshirt","dummy pic",120.0,womenDresses,1);
        kidsDresses.setProducts(Arrays.asList(product1,product2));
        womenDresses.setProducts(Arrays.asList(product2,product3));
        productsSet.addAll(Arrays.asList(product1,product2,product3,product4));
        return productsSet;

    }
    @Test
    public void  mergingListsOfProductsTest_sendTwoDifferentListsOfProducts_returnSetOfMergedProducts(){
        //arrange
        List<Product> womenProducts=initFirstListOfProducts();
        List<Product> kidsProducts=initSecondListOfProducts();
        Set<Product> mergedProducts=initMergedSetOfProducts();
        //act
        Set<Product> result=productsUtils.mergingListsOfProducts(womenProducts,kidsProducts);
        //assert
        assertTrue(result.equals(mergedProducts));

    }
    @Test
    public void  mergingListsOfProductsTest_sendTwoIdenticalListsOfProducts_returnSetOfMergedProducts(){
        //arrange
        List<Product> womenProducts=initFirstListOfProducts();
        List<Product> womenProducts2=initFirstListOfProducts();
        Set<Product> mergedProducts=new HashSet<>(initFirstListOfProducts());
        //act
        Set<Product> result=productsUtils.mergingListsOfProducts(womenProducts,womenProducts2);
        //assert
        assertTrue(result.equals(mergedProducts));

    }
    @Test
    public void  mergingListsOfProductsTest_sendTwoEmptyListsOfProducts_returnEmptySet(){
        //arrange
        List<Product> firstList=new ArrayList<>();
        List<Product> secondList=new ArrayList<>();
        Set<Product> mergedProducts=new HashSet<>();
        //act
        Set<Product> result=productsUtils.mergingListsOfProducts(firstList,secondList);
        //assert
        assertTrue(result.equals(mergedProducts));

    }
    @Test
    public void  mergingProductsAndCategoryProductsTest_sendCategoryList_sendProductList_returnSetOfMergedProducts(){
        //arrange
        List<Category> categories=helperMethods.initCategoryList();
        List<Product> products=initFirstListOfProducts();
        Set<Product>expectedResult=initMergedSetOfProductsAndCategoryProducts();
        //act
        Set<Product> result=productsUtils.mergingProductsAndCategoryProducts(categories,products);
        //assert
        assertTrue(expectedResult.equals(result));
    }
    @Test
    public void  mergingProductsAndCategoryProductsTest_sendEmptyCategoryList_sendEmptyProductList_returnEmptySetOfMergedProducts(){
        //arrange
        List<Category> categories=new ArrayList<>();
        List<Product> products=new ArrayList<>();
        Set<Product>expectedResult=new HashSet<>();
        //act
        Set<Product> result=productsUtils.mergingProductsAndCategoryProducts(categories,products);
        //assert
        assertTrue(expectedResult.equals(result));
    }
}
