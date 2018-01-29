package kata.products;

import java.math.BigDecimal;

import kata.products.enums.ProductType;

public interface IProduct
{
	ProductType getProductType();
	
	BigDecimal getPrice();
}
