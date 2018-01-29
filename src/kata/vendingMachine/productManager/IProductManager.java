package kata.vendingMachine.productManager;

import java.math.BigDecimal;

import kata.products.enums.ProductType;

public interface IProductManager
{
	BigDecimal getItemPrice(ProductType productType);
}
