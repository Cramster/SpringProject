# Welcome to ProjectSpring
### A Project by Steven & Marc
## For Java Training

#### Overview of functionality:
	-Model - do together - use Lombok - Need to determine how to do the foreign key
		-Product )
			    private int productID;
			    private String productName;
			    private double price;
			    private int sellerID 
		-Seller
            private int sellerId ;
            private String sellerName;
			
			
	Service - split work 
		-ProductService - pretty much the same however remove references to the DAO and reference repository instead
            - Get All products
            - Get product by ID
            - Post product
            - Delete Product
            - Update Product
		-SellerService -  pretty much the same however remove references to the DAO and reference repository instead
            - GET to receive all Seller
            - Post to add a new seller
                    - Refer back to project 2 requirement on additional exceptions
		
		
	Exceptions ---  do together some additional may be needed depending on whose project we build off of
		-copy from old project
		
	Controller - split work 
		- Create two controllers
			§ ProductController - change to use Spring annotations
			§ SellerController - change to use Spring annotations
			
	Repository - do together 
		SellerRepository
		ProductRepository
		
	Update to use spring
		Main  - do together 
		Pom.xml  - do together 
		
	Resources
		Application.properties  - do together 

