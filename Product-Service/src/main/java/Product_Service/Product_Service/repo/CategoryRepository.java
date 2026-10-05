package Product_Service.Product_Service.repo;

import Product_Service.Product_Service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findByParentId(Long parentId);

    Boolean existsByName(String name);


}
