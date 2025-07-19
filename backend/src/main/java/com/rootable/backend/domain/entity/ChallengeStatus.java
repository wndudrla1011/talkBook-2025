package com.rootable.backend.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChallengeStatus {

    PENDING("대기 중"),
    APPROVED("승인됨"),
    REJECTED("거절됨");

    private final String label;

}
