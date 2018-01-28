package kata.products;

import kata.products.enums.ProductType;

public class ChipProduct implements IProduct
{
	private final Double price = new Double(.50);
	@Override
	public ProductType getProductType()
	{
		return ProductType.CHIPS;
	}
	@Override
	public Double getPrice()
	{
		return price;
	}

}
