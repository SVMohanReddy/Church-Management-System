package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Church;
import com.project.repository.ChurchRepository;

import java.util.List;

@Service
public class ChurchService {

    @Autowired
    private ChurchRepository churchRepository;

    public Church createChurch(Church church) {
        return churchRepository.save(church);
    }

    public Church updateChurch(int id, Church church) {
        Church existingChurch = churchRepository.findById(church.getChurch_id()).orElse(null);
        if (existingChurch != null) {
            existingChurch.setChurch_name(church.getChurch_name());
            existingChurch.setChurch_father_name(church.getChurch_father_name());
            existingChurch.setChurch_location(church.getChurch_location());
            existingChurch.setChurch_capacity(church.getChurch_capacity());

            return churchRepository.save(existingChurch);
        }
        return null;
    }

    public Church getChurchById(int id) {
        return churchRepository.findById(id).orElse(null);
    }

    public List<Church> getAllChurches() {
        return churchRepository.findAll();
    }

    public void deleteChurchById(int id) {
        churchRepository.deleteById(id);
    }
}
