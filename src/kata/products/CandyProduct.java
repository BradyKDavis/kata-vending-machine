package kata.products;

import java.math.BigDecimal;

import kata.products.enums.ProductType;

public class CandyProduct implements IProduct
{
	private final BigDecimal price = new BigDecimal(".65");
	@Override
	public ProductType getProductType()
	{
		return ProductType.CANDY;
	}

	@Override
	public BigDecimal getPrice()
	{
		return price;
	}

}
