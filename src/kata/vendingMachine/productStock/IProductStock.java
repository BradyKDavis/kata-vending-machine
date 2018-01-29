package kata.vendingMachine.productStock;

import kata.products.IProduct;
import kata.products.enums.ProductType;

public interface IProductStock
{
	boolean hasProductInStock(ProductType productType);
	
	IProduct getProduct(ProductType productType);
}
