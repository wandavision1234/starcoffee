package starcoffee;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="makes", path="makes")
public interface MakeRepository extends PagingAndSortingRepository<Make, Long>{


}
