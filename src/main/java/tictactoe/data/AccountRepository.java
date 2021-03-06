package tictactoe.data;

import tictactoe.Account;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query(value = "SELECT * FROM `account` WHERE username = ?1 and password = ?2", nativeQuery = true)
    ArrayList<Account> findAccount(String un, String pw);

    @Query(value = "SELECT * FROM `account` WHERE username = ?1", nativeQuery = true)
    ArrayList<Account> findAccountByName(String un);

    @Query(value = "SELECT * FROM `account` WHERE id = ?1", nativeQuery = true)
    Account findOneAccount(int i);

    @Query(value = "SELECT * FROM `account` WHERE status > 0", nativeQuery = true)
    ArrayList<Account> findActiveAccount();

    @Query(value = "SELECT * FROM `account` ORDER BY point DESC", nativeQuery = true)
    ArrayList<Account> ldbBasic();

    @Transactional
    @Modifying
    @Query(value = "UPDATE `account` SET status = 1 WHERE id = ?1", nativeQuery = true)
    void online(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `account` SET status = 2 WHERE id = ?1", nativeQuery = true)
    void busy(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `account` SET status = 0 WHERE id = ?1", nativeQuery = true)
    void offline(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `account` SET point = point + 1 WHERE id = ?1", nativeQuery = true)
    void win(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `account` SET point = point + 0.5 WHERE id = ?1", nativeQuery = true)
    void draw(int id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `account`(`username`, `name`,`password`, `point`) VALUES(?1, ?2, ?3, 0.0)", nativeQuery = true)
    void createAccount(String un, String name, String pw);
}