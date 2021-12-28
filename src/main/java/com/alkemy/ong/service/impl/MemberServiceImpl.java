package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.request.MemberRequest;
import com.alkemy.ong.model.response.MemberResponse;
import com.alkemy.ong.model.response.pagination.CustomPage;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public CustomPage<MemberResponse> getMembersPageable(Pageable pageRequest) {
        return new CustomPage<>(memberRepository.findAll(pageRequest).map(MemberResponse::toDTO));
    }

    @Override
    public MemberResponse createMember(MemberRequest request) {
        return MemberResponse.toDTO(memberRepository.save(MemberRequest.toEntity(request)));
    }

    @Override
    public MemberResponse updateMember(MemberRequest memberRequest, Long id) {
        var foundMember = memberRepository.findById(id).orElseThrow();
        return MemberResponse.toDTO(memberRepository
                .save(MemberRequest.refreshData(foundMember, memberRequest)));
    }

    @Override
    public void deleteMember(Long id) {
        var foundMember = memberRepository.findById(id).orElseThrow();
        memberRepository.delete(foundMember);
    }


}
