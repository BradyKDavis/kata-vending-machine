package kata.products;

import kata.products.enums.ProductType;

public class ChipProduct implements IProduct
{

	@Override
	public ProductType getProductType()
	{
		return ProductType.CHIPS;
	}

}
