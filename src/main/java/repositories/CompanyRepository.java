package repositories;

import avro.Companies;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Companies, Long>{
    Companies findBySymbol(String symbol);
}
