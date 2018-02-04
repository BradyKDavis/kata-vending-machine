package kata.products;

import java.math.BigDecimal;

import kata.products.enums.ProductType;

public class ColaProduct implements IProduct
{
	private static final String NAME = "Kata Cola";
	private static final BigDecimal price = new BigDecimal("1.00");
	@Override
	public ProductType getProductType()
	{
		return ProductType.COLA;
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
