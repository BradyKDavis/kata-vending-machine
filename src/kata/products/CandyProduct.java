package kata.products;

import java.math.BigDecimal;

import kata.products.enums.ProductType;

public class CandyProduct implements IProduct
{
	private static final String NAME = "Kata Candy";
	private static final BigDecimal price = new BigDecimal(".65");
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
	
	@Override
	public String toString()
	{
		return NAME;
	}

}
