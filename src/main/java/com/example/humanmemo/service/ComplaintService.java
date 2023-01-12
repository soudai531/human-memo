package com.example.humanmemo.service;

import com.example.humanapi.model.ComplaintDTO;
import com.example.humanmemo.repository.ComplaintRepository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintDTO find(Long complaintId) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        return complaintRepository.findById(complaintId)
                .map(entity -> toComplaintDTO(entity))
                .orElseThrow(() -> new ComplaintEntityNotFoundException(complaintId));
    }

    public List<ComplaintDTO> find(int limit, long offset) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        return complaintRepository.selectList(limit,offset)
                .stream()
                .map(entity -> toComplaintDTO(entity))
                .collect(Collectors.toList());
    }

    public ComplaintDTO create(String title) {
        var entity = complaintRepository.save(new ComplaintEntity(null, title,new Date()));
        var dto = toComplaintDTO(entity);
        return dto;

    }

    public ComplaintDTO update(Long complaintId, String title) {
        complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ComplaintEntityNotFoundException(complaintId));
        var beforeEntity = complaintRepository.findById(complaintId);
        var afterEntity = complaintRepository.save(new ComplaintEntity(complaintId, title, beforeEntity.get().getRegDateOn()));
        return toComplaintDTO(afterEntity);
    }

    /**
     * ComplaintEntityをComplaintDTOに詰め替える
     */
    private static ComplaintDTO toComplaintDTO(ComplaintEntity entity) {
        var dto =  new ComplaintDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        dto.setDate(sf.format(entity.getRegDateOn()));
        return dto;
    }

    public void delete(Long complaintId) {
        var entity = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ComplaintEntityNotFoundException(complaintId));
        complaintRepository.delete(entity);
    }
}
