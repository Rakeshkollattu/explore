package com.dev.explore.service;

import com.dev.explore.dto.GeneralDto;
import com.dev.explore.dto.OfficialsDto;
import com.dev.explore.dto.UserDto;
import com.dev.explore.entity.Member;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MemberService {

    // repos
    Optional<Member> findByEmail(String email);

    Page<Member> getMembersByWard(String ward, int pageNumber, int pageSize);

    Page<Member> getMembersByBooth(String booth, int pageNumber, int pageSize);

    Page<Member> getMemberByBloodGroup(String bloodGroup, int pageNumber, int pageSize);

    Page<Member> getMemberByJob(String job, int pageNumber, int pageSize);

    //search
    Page<Member> findByWardContainingStartsWithIgnoreCase(String keyword, int pageNumber, int pageSize);

    Page<Member> findByBoothContainingStartsWithIgnoreCase(String keyword, int pageNumber, int pageSize);

    Page<Member> findByBloodGroupContainingStartsWithIgnoreCase(String keyword, int pageNumber, int pageSize);

    Page<Member> findByJobContainingStartsWithIgnoreCase(String keyword, int pageNumber, int pageSize);

    //general
    Page<Member> getAllMembers(int pageNumber, int pageSize);

    Optional<Member> getContact(String id);

    void addMember(Member member);

    void updateMember(UserDto member, String id);

    Optional<Member> updateMemberOfficials(OfficialsDto member, String id);

    Optional<Member> updateMemberGenerals(GeneralDto generalDto, String id);

    void deleteContact(String id);

    Set<String> getJobs();

    Set<String> getWards();

    Set<String> getBooths();

    Set<String> getBloodGroups();

//    void updateProfilePicture(String profilePicture, byte[] photoContent,String id) throws IOException, SQLException;
}
