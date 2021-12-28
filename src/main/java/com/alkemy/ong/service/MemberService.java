package com.alkemy.ong.service;

import com.alkemy.ong.model.request.MemberRequest;
import com.alkemy.ong.model.response.MemberResponse;
import com.alkemy.ong.model.response.pagination.CustomPage;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    CustomPage<MemberResponse> getMembersPageable(Pageable pageRequest);

    MemberResponse createMember(MemberRequest request);

    void deleteMember(Long id);

    MemberResponse updateMember(MemberRequest memberRequest, Long id);
}
