package kata.products;

import kata.products.enums.ProductType;

public class ColaProduct implements IProduct
{

	@Override
	public ProductType getProductType()
	{
		return ProductType.COLA;
	}

}
