package com.hackathon.sprout.domain.file.service;

import com.hackathon.sprout.domain.file.domain.File;
import com.hackathon.sprout.domain.file.repository.FileRepository;
import com.hackathon.sprout.domain.image.domain.ImageMessage;
import com.hackathon.sprout.domain.image.service.ImageMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {
    private final ImageMessageRepository imageMessageRepository;
    private final FileRepository fileRepository;
    private final S3Service uploader;

    @Transactional
    public void saveFiles(List<MultipartFile> requestFiles) throws IOException {
        List<File> fileList = new ArrayList<>();
        for (MultipartFile requestFile : requestFiles) {
            String fileUrl = uploader.saveFile(requestFile);
            File file = File.createFileBuilder()
                    .imageMessage(null)
                    .uploadFileName(requestFile.getOriginalFilename())
                    .url(fileUrl)
                    .fileSize(requestFile.getSize())
                    .build();
            fileList.add(file);
        }

        fileRepository.saveAll(fileList);
    }

    @Transactional
    public void saveFiles(Long imageMessageId, List<MultipartFile> requestFiles) throws IOException {
        ImageMessage imageMessage = imageMessageRepository.findById(imageMessageId).orElseThrow();

        List<File> fileList = new ArrayList<>();
        for (MultipartFile requestFile : requestFiles) {
            String fileUrl = uploader.saveFile(requestFile);
            File file = File.createFileBuilder()
                    .imageMessage(imageMessage)
                    .uploadFileName(requestFile.getOriginalFilename())
                    .url(fileUrl)
                    .fileSize(requestFile.getSize())
                    .build();
            fileList.add(file);
        }

        fileRepository.saveAll(fileList);
    }

    @Transactional
    public void delete(Long imageMessageId) {
        ImageMessage imageMessage = imageMessageRepository.findById(imageMessageId).orElseThrow();

        // SecurityUtil.checkAuthorizedUser(post.getUser().getUserId());

        if (imageMessage.getFiles() != null) {
            for (File file : imageMessage.getFiles()) {
                fileRepository.delete(file);
            }
        }
    }

    @Transactional
    public void update(Long imageMessageId, List<MultipartFile> requestFiles) throws IOException {
        delete(imageMessageId);
        saveFiles(imageMessageId, requestFiles);
    }

}