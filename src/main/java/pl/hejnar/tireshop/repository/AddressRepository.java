package pl.hejnar.tireshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hejnar.tireshop.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
