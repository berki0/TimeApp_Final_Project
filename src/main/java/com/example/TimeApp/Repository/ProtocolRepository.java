package com.example.TimeApp.Repository;

import com.example.TimeApp.Entities.DailyProtocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;
@Repository
public interface ProtocolRepository extends JpaRepository<DailyProtocol, Long> {
}
