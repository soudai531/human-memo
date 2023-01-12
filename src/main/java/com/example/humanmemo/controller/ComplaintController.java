package com.example.humanmemo.controller;

import com.example.humanapi.controller.ComplaintsApi;
import com.example.humanapi.model.ComplaintDTO;
import com.example.humanapi.model.ComplaintForm;
import com.example.humanapi.model.ComplaintListDTO;
import com.example.humanapi.model.PageDTO;
import com.example.humanmemo.service.ComplaintEntity;
import com.example.humanmemo.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ComplaintController implements ComplaintsApi {

    private final ComplaintService complaintService;

    @Override
    public ResponseEntity<ComplaintDTO> showComplaint(Long complaintId) {
        var entity = complaintService.find(complaintId);
        return ResponseEntity.ok(entity);
    }

    @Override
    public ResponseEntity<ComplaintDTO> createComplaint(ComplaintForm form) {
        var dto = complaintService.create(form.getTitle());
        return ResponseEntity
                .created(URI.create("/complaints/" + dto.getId()))
                .body(dto);
    }

    @Override
    public ResponseEntity<ComplaintListDTO> listComplaint(Integer limit, Long offset) {
        var dtoList = complaintService.find(limit,offset);
        var dto = new ComplaintListDTO();;
        var pageDTO = new PageDTO();
        pageDTO.setLimit(limit);
        pageDTO.setOffset(offset);
        pageDTO.setSize(dtoList.size());

        dto.setPage(pageDTO);
        dto.setResults(dtoList);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ComplaintDTO> editComplaint(Long complaintId, ComplaintForm form) {
        var dto = complaintService.update(complaintId, form.getTitle());
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> deleteComplaint(Long complaintId) {
        complaintService.delete(complaintId);
        return ResponseEntity.noContent().build();
    }
}
