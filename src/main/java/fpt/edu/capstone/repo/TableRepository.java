package fpt.edu.capstone.repo;

import fpt.edu.capstone.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Tables, Long> {
    @Query("select c from Tables c where c.tableId = ?1")
    Tables findTableById(Long id);

    @Query("select c.qrCode.QRCodeId from Tables c where c.tableId = ?1")
    Long findQRCodeIdByTableId(Long tableId);

    @Query("select c from  Tables c where c.qrCode.QRCodeId = ?1")
    Tables findTableByQRCodeId(Long qrCodeId);
}