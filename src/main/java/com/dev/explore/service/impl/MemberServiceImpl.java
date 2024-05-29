package com.dev.explore.service.impl;

import com.dev.explore.dto.GeneralDto;
import com.dev.explore.dto.OfficialsDto;
import com.dev.explore.dto.UserDto;
import com.dev.explore.entity.Member;
import com.dev.explore.repo.MemberRepository;
import com.dev.explore.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
//@Cacheable(value = "members")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepo;

    //repo

    @Override
//    @Cacheable(value = "members", key = "#email")
    public Optional<Member> findByEmail(String email) {
        return memberRepo.findByEmail(email);
    }

    @Override
//    @Cacheable(value = "members", key = "#ward")
    public Page<Member> getMembersByWard(String ward, int pageNumber, int pageSize) {
        return memberRepo.findByWard(ward, PageRequest.of(pageNumber, pageSize, Sort.by("name")));
    }

    @Override
//    @Cacheable(value = "members", key = "#booth")
    public Page<Member> getMembersByBooth(String booth, int pageNumber, int pageSize) {
        return memberRepo.findByBooth(booth, PageRequest.of(pageNumber, pageSize, Sort.by("name")));
    }

    @Override
//    @Cacheable(value = "members", key = "#bloodGroup")
    public Page<Member> getMemberByBloodGroup(String bloodGroup, int pageNumber, int pageSize) {
        return memberRepo.findByBloodGroup(bloodGroup, PageRequest.of(pageNumber, pageSize, Sort.by("name")));
    }

    @Override
//    @Cacheable(value = "members", key = "#job")
    public Page<Member> getMemberByJob(String job, int pageNumber, int pageSize) {
        return memberRepo.findByJob(job, PageRequest.of(pageNumber, pageSize, Sort.by("name")));
    }

    @Override
//    @Cacheable(value = "members", key = "#keyword")
    public Page<Member> findByWardContainingStartsWithIgnoreCase(String keyword, int pageNumber, int pageSize) {
        return memberRepo.findByWardOrNameLike(keyword, PageRequest.of(pageNumber, pageSize, Sort.by("name")));
    }

    @Override
//    @Cacheable(value = "members", key = "#keyword")
    public Page<Member> findByBoothContainingStartsWithIgnoreCase(String keyword, int pageNumber, int pageSize) {
        return memberRepo.findByBoothOrNameLike(keyword, PageRequest.of(pageNumber, pageSize, Sort.by("name")));
    }


    @Override
//    @Cacheable(value = "members", key = "#keyword")
    public Page<Member> findByBloodGroupContainingStartsWithIgnoreCase(String keyword, int pageNumber, int pageSize) {
        return memberRepo.findByBloodGroupOrNameLike(keyword, PageRequest.of(pageNumber, pageSize, Sort.by("name")));
    }

    @Override
//    @Cacheable(value = "members", key = "#keyword")
    public Page<Member> findByJobContainingStartsWithIgnoreCase(String keyword, int pageNumber, int pageSize) {
        return memberRepo.findByJobOrNameLike(keyword, PageRequest.of(pageNumber, pageSize, Sort.by("name")));
    }

    //general

    @Override
//    @Cacheable(value = "members")
    public Page<Member> getAllMembers(int pageNumber, int pageSize) {
        return memberRepo.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("name")));
    }

    @Override
//    @Cacheable(value = "members", key = "#id")
    public Optional<Member> getContact(String id) {
        return memberRepo.findById(id);
    }

    @Override
//    @CachePut(value = "members", key = "#member.id")
    public void addMember(Member member) {
        memberRepo.save(member);
    }

    @Override
//    @CachePut(value = "members", key = "#id")
    public void updateMember(UserDto member, String id) {
        Member updatedData = new Member();
        Optional<Member> data = memberRepo.findById(id);
        if (data.isPresent()) {
            updatedData.setId(data.get().getId());
            updatedData.setName(member.getName());
            updatedData.setEmail(member.getEmail());
            updatedData.setPhoneNumber(member.getPhoneNumber());
            updatedData.setJob(member.getJob());
            updatedData.setPosition(member.getRole());
            updatedData.setBloodGroup(member.getBloodGroup());
            updatedData.setDob(member.getDob());
            updatedData.setWard(member.getWard());
            updatedData.setBooth(member.getBooth());
            updatedData.setHouseName(member.getHouseName());
            updatedData.setLandmark(member.getLandmark());
            updatedData.setStreetName(member.getStreetName());
            updatedData.setCity(member.getCity());
            updatedData.setDistrict(member.getDistrict());
            updatedData.setReadyForBloodDonation(member.getReadyForBloodDonation());
            updatedData.setReadyForCommunityWork(member.getReadyForWork());
            memberRepo.save(updatedData);
        } else {
            throw new RuntimeException("Contact not found.");
        }
    }

    @Override
//    @CachePut(value = "members", key = "#id")
    public Optional<Member> updateMemberOfficials(OfficialsDto member, String id) {

        Member updatedData = new Member();
        Optional<Member> data = memberRepo.findById(id);
        if (data.isPresent()) {
            updatedData.setId(data.get().getId());
            updatedData.setName(data.get().getName());
            updatedData.setEmail(data.get().getEmail());

            if (member.getPhoneNumber() == null || member.getPhoneNumber().isEmpty()) {
                updatedData.setPhoneNumber(data.get().getPhoneNumber());
            } else {
                updatedData.setPhoneNumber(member.getPhoneNumber());
            }

            if (member.getJob() == null || member.getJob().isEmpty()) {
                updatedData.setJob(data.get().getJob());
            } else {
                updatedData.setJob(member.getJob());
            }

            if (member.getPosition() == null || member.getPosition().isEmpty()) {
                updatedData.setPosition(data.get().getPosition());
            } else {
                updatedData.setPosition(member.getPosition());
            }

            updatedData.setBloodGroup(data.get().getBloodGroup());
            updatedData.setDob(data.get().getDob());
            updatedData.setWard(data.get().getWard());
            updatedData.setBooth(data.get().getBooth());
            updatedData.setHouseName(data.get().getHouseName());
            updatedData.setLandmark(data.get().getLandmark());
            updatedData.setStreetName(data.get().getStreetName());
            updatedData.setCity(data.get().getCity());
            updatedData.setDistrict(data.get().getDistrict());
            updatedData.setReadyForBloodDonation(data.get().getReadyForBloodDonation());
            updatedData.setReadyForCommunityWork(data.get().getReadyForCommunityWork());
            memberRepo.save(updatedData);
        } else {
            throw new RuntimeException("Member not found.");
        }
        return memberRepo.findById(id);
    }

    @Override
//    @CachePut(value = "members", key = "#id")
    public Optional<Member> updateMemberGenerals(GeneralDto member, String id) {

        Member updatedData = new Member();
        Optional<Member> data = memberRepo.findById(id);
        if (data.isPresent()) {
            updatedData.setId(data.get().getId());
            updatedData.setName(data.get().getName());
            updatedData.setEmail(data.get().getEmail());
            updatedData.setPhoneNumber(data.get().getPhoneNumber());
            updatedData.setJob(data.get().getJob());
            updatedData.setPosition(data.get().getPosition());
            if (member.getBloodGroup() == null || member.getBloodGroup().isEmpty()) {
                updatedData.setBloodGroup(data.get().getBloodGroup());
            } else {
                updatedData.setBloodGroup(member.getBloodGroup());
            }
            if (member.getDob() == null || member.getDob().isEmpty()) {
                updatedData.setDob(data.get().getDob());
            } else {
                updatedData.setDob(member.getDob());
            }
            if (member.getWard() == null || member.getWard().isEmpty()) {
                updatedData.setWard(data.get().getWard());
            } else {
                updatedData.setWard(member.getWard());
            }
            if (member.getBooth() == null || member.getBooth().isEmpty()) {
                updatedData.setBooth(data.get().getBooth());
            } else {
                updatedData.setBooth(member.getBooth());
            }
            if (member.getHouseName() == null || member.getHouseName().isEmpty()) {
                updatedData.setHouseName(data.get().getHouseName());
            } else {
                updatedData.setHouseName(member.getHouseName());
            }
            if (member.getLandmark() == null || member.getLandmark().isEmpty()) {
                updatedData.setLandmark(data.get().getLandmark());
            } else {
                updatedData.setLandmark(member.getLandmark());
            }
            if (member.getStreetName() == null || member.getStreetName().isEmpty()) {
                updatedData.setStreetName(data.get().getStreetName());
            } else {
                updatedData.setStreetName(member.getStreetName());
            }
            if (member.getCity() == null || member.getCity().isEmpty()) {
                updatedData.setCity(data.get().getCity());
            } else {
                updatedData.setCity(member.getCity());
            }
            if (member.getDistrict() == null || member.getDistrict().isEmpty()) {
                updatedData.setDistrict(data.get().getDistrict());
            } else {
                updatedData.setDistrict(member.getDistrict());
            }
            updatedData.setReadyForBloodDonation(data.get().getReadyForBloodDonation());
            updatedData.setReadyForCommunityWork(data.get().getReadyForCommunityWork());
            memberRepo.save(updatedData);
        } else {
            throw new RuntimeException("Member not found.");
        }

        return memberRepo.findById(id);
    }

    @Override
//    @CacheEvict(value = "directory", key = "#id")
    public void deleteContact(String id) {
        Optional<Member> contact = memberRepo.findById(id);
        contact.ifPresent(value -> memberRepo.delete(value));
    }

    @Override
    public Set<String> getJobs() {
        return memberRepo.findAll().stream().map(Member::getJob).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWards() {
        return memberRepo.findAll().stream().map(Member::getWard).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getBooths() {
        return memberRepo.findAll().stream().map(Member::getBooth).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getBloodGroups() {
        return memberRepo.findAll().stream().map(Member::getBloodGroup).collect(Collectors.toSet());
    }

//    @Override
//    public void updateProfilePicture(String profilePicture, byte[] photoContent, String id) throws IOException, SQLException {
//
//        Optional<Contact> contact = contactRepo.findById(id);
//        if (contact.isPresent()) {
//            //add here
//        }
//    }
}
