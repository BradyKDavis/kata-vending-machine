package kata.vendingMachine.productManager;

import kata.products.enums.ProductType;

public interface IProductManager
{
	Double getItemPrice(ProductType productType);
}
