package repositories;

import avro.Price;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PriceRepository extends CrudRepository<Price, Long> {
    List<Price> findAllByTicker(String ticker);
}
