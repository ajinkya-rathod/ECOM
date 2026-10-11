package Product_Service.Product_Service.service;

import Product_Service.Product_Service.dto.ProductDto;
import Product_Service.Product_Service.entity.Category;
import Product_Service.Product_Service.entity.Product;
import Product_Service.Product_Service.mapper.ProductMapper;
import Product_Service.Product_Service.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final String uploadDir = System.getProperty("user.dir")+"/uploads/products/";

    @Override
    public ProductDto createProduct(ProductDto dto) {
        Category category = null;

        if(dto.getCategoryId() !=null){
            category = new Category();
            category.setId(dto.getCategoryId());
        }

       Product product = productMapper.toEntity(dto,category);
      Product savedProduct = productRepository.save(product);

        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto dto) {

      Product product =  productRepository.findById(id)
              .orElseThrow(()-> new RuntimeException("product is not found with this Id "+ id));

      product.setName(dto.getName());
      product.setDescription(dto.getDescription());
      product.setPrice(dto.getPrice());
      product.setDiscountPrice(dto.getDiscountPrice());
      product.setQuantity(dto.getQuantity());
      product.setBrand(dto.getBrand());
      product.setImageUrl(dto.getImageUrl());
      if(dto.getCategoryId() != null){
          Category category = new Category();
          category.setId(dto.getCategoryId());
          product.setCategory(category);
      }
      return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public String deleteProduct(Long id) {

        productRepository.deleteById(id);

        return "product deleted succsesfully";

    }

    @Override
    public ProductDto getByProductId(Long id) {

      Product product = productRepository.findById(id)
              .orElseThrow(()-> new RuntimeException("product not found with this id "+ id));

        return productMapper.toDto(product);
    }


    // this method is for sorting logic for product
    @Override
    public Page<ProductDto> getAllProduct(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);

       Page<Product> allProducts = productRepository.findAll(pageable);

        return allProducts.map(productMapper::toDto);

    }

    @Override
    public Page<ProductDto> searchProduct(String keyword, int page, int size) {

        Pageable pageable = PageRequest.of(page,size);

        Page<Product> productDtos = productRepository.searchProducts(keyword,pageable);

        return productDtos.map(productMapper::toDto);
    }

    @Override
    public Page<ProductDto> filterProducts(Long categoryId, double minPrice, double maxPrice, int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage = productRepository.advancedFilter(null,categoryId,minPrice,minPrice,pageable);

        return productPage.map(productMapper::toDto);
    }

    @Override
    public Page<ProductDto> advancedFilter(String Keyword, Long categoryId, double minPrice, double maxPrice, int page, int size, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public ProductDto uploadImage(Long productId, MultipartFile file) throws IOException {

       Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found with this ID : "+productId));

       // below code checking multipart file empty or not //

        if (file.isEmpty()){
            throw new RuntimeException("Image file is Empty");
        }

        long mazSize = 2*1024*1024;

        if (file.getSize() > mazSize){
            throw new RuntimeException("file size must be less than 2MB");
        }

        List<String> allowedType = List.of("image/jpeg","image/png","image/jpg");

        if (!allowedType.contains(file.getContentType())){
            throw new RuntimeException("only jpeg png and jpg are allowed");
        }

        String origanalFileName = file.getOriginalFilename();

        if (origanalFileName == null || !origanalFileName.contains(".")){
            throw new RuntimeException("Invalid File Name");
        }

        String ext = origanalFileName.substring(origanalFileName.lastIndexOf(".")+1).toLowerCase();

        List<String> allowedExt = List.of("jpg","png","jpeg");

        if (!allowedExt.contains(ext)){
            throw new RuntimeException("invalid Image Extension");
        }

        File folder = new File(uploadDir);

        if (!folder.exists()){
            folder.mkdirs();
        }

        String fileName = UUID.randomUUID().toString()+"."+ext;

         Path filePath = Paths.get(uploadDir+fileName);

        Files.write(filePath,file.getBytes());

        String imageUrl = "/products/images/"+fileName;

        product.setImageUrl(imageUrl);
       Product product1 = productRepository.save(product);
        return productMapper.toDto(product1);
    }
}
