package com.rootable.backend.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id; // 파일 ID

    @Column(name = "originalFileName", nullable = false)
    private String originalFileName; // 원본 파일명

    @Column(name = "fileName", nullable = false)
    private String fileName; // 서버에 저장된 파일명

    @Column(name = "filePath", nullable = false)
    private String filePath; // 서버에 저장된 파일 경로

    @Column(name = "content_type", nullable = false, length = 100)
    private String contentType; // MIME 타입

    @Column(name = "file_size", nullable = false)
    private Long fileSize; // 파일 크기 (바이트 단위)

    @Builder
    public File(String originalFileName, String fileName, String filePath, String contentType, Long fileSize) {
        this.originalFileName = originalFileName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.contentType = contentType;
        this.fileSize = fileSize;
    }

}
