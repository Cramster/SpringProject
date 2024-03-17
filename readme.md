# Welcome to ProjectHTTP
### A Project by Marc
## For Java Training

#### Overview of functionality:
1. Retrieve data values from an app such as Postman
2. The data will be processed in the app's service classes
3. Processed data will be sent to SQL format
- Create sellers/products
- Delete sellers/products
- Update products

### ! Known Issues !
- When starting program with sellers inserted in Tables.sql and attempting to add a seller with a same name in Postman it will appear to post but upon retrieving the sellers again it keeps the original inserted seller
  - Potential seller long ID in seller service conflicting with seller int ID from DAO
- Struggle bus trying to determine how to properly set JUNIT tests for Service VS DAO as well for model classes.. tried rewatching the 1/23 lecture but not everything translates over to my project, things got very convoluted along the way while introducing new methods from different projects. Admittedly I need more practice w/ Junit.

### To Do:
- Junit 60% coverage
  - JUNIT tests need complete overhaul - they are not working, from the start there was a disconnect w/ service VS DAO checks
- <strike>Add Product DB/DAO functionality</strike>
- Refactor seller ID from long to int to match service to DAO (nice to have)
- ServiceController lambda split (nice to have)