package com.dev.explore.repo;

import com.dev.explore.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByEmail(String email);

    Page<Member> findByWard(String ward, Pageable pageable);

    Page<Member> findByBooth(String ward, Pageable pageable);

    Page<Member> findByBloodGroup(String ward, Pageable pageable);

    Page<Member> findByJob(String ward, Pageable pageable);

    @Query(value = "select * from contacts m where m.name like %:keyword% or m.ward like %:keyword%", nativeQuery = true)
    Page<Member> findByWardOrNameLike(@Param("keyword")String keyword, Pageable pageable);

    @Query(value = "select * from contacts m where m.name like %:keyword% or m.booth like %:keyword%", nativeQuery = true)
    Page<Member> findByBoothOrNameLike(@Param("keyword")String keyword, Pageable pageable);

    @Query(value = "select * from contacts m where m.name like %:keyword% or m.blood_group like %:keyword%", nativeQuery = true)
    Page<Member> findByBloodGroupOrNameLike(@Param("keyword")String keyword, Pageable pageable);

    @Query(value = "select * from contacts m where m.name like %:keyword% or m.job like %:keyword%", nativeQuery = true)
    Page<Member> findByJobOrNameLike(@Param("keyword")String keyword, Pageable pageable);


}
