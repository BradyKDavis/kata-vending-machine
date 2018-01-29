package kata.products;

import java.math.BigDecimal;

import kata.products.enums.ProductType;

public class ChipProduct implements IProduct
{
	private final BigDecimal price = new BigDecimal(".50");
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

}
