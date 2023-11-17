package com.kioskback.repository;

import com.kioskback.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByLoginId(String loginId);

    // loginId로 member 조회
    Optional<MemberEntity> findByLoginId(String loginId);

    // member 전체 조회
    List<MemberEntity> findAll();

}
