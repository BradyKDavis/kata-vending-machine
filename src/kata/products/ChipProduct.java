package kata.products;

import java.math.BigDecimal;

import kata.products.enums.ProductType;

public class ChipProduct implements IProduct
{
	private static final String NAME = "Kata Chips";
	private static final BigDecimal price = new BigDecimal(".50");
	@Override
	public ProductType getProductType()
	{
		return ProductType.CHIPS;
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
