package pl.hejnar.tireshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.hejnar.tireshop.entity.Address;
import pl.hejnar.tireshop.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.address=?1 WHERE u.id=?2")
    void updateAddressToUser(Address address, Long id);
}
