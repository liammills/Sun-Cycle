package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.SolarPanelInstallationRequestDTO;
import com.SunCycle.SunCycle.dto.SolarPanelInstallationResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.SolarPanel;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.model.User;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
import com.SunCycle.SunCycle.repository.SolarPanelRepository;
import com.SunCycle.SunCycle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolarPanelInstallationService {

    @Autowired
    private SolarPanelInstallationRepository solarPanelInstallationRepository;

    @Autowired
    private SolarPanelRepository solarPanelRepository;

    @Autowired
    private UserRepository userRepository;

    public SolarPanelInstallationResponseDTO createSolarPanelInstallation(SolarPanelInstallationRequestDTO dto) {
        // convert dto to installation instance
        SolarPanelInstallation installation = new SolarPanelInstallation();

        // check if user exists
        Optional<User> userOpt = userRepository.findById(dto.getUserId());
        if (userOpt.isPresent())
            installation.setUser(userOpt.get());
        else
            return new SolarPanelInstallationResponseDTO("User not found", Status.NOT_FOUND);

        // set other fields
        installation.setGeoLocation(dto.getGeoLocation());
        installation.setAddress(dto.getAddress());
        installation.setPostcode(dto.getPostcode());
        installation.setState(dto.getState());
        installation.setType(dto.getType());

        return new SolarPanelInstallationResponseDTO(solarPanelInstallationRepository.save(installation), Status.SUCCESS);
    }

    public SolarPanelInstallationResponseDTO updateSolarPanelInstallation(Authentication authentication, int panelId, SolarPanelInstallationRequestDTO dto) {
        // try to find installation
        Optional<SolarPanelInstallation> installationOptional = solarPanelInstallationRepository.findById(panelId);
        if (installationOptional.isEmpty()) {
            return new SolarPanelInstallationResponseDTO("Installation not found", Status.NOT_FOUND);
        }
        SolarPanelInstallation oldInstallation = installationOptional.get();

        // check if user id matches
        if (oldInstallation.getUser().getUserId() != dto.getUserId()) {
            return new SolarPanelInstallationResponseDTO("Invalid user id", Status.ERROR);
        }

        // try to find current user
        Optional<User> userOptional = userRepository.findByEmail(authentication.getName());
        if (userOptional.isEmpty()) {
            return new SolarPanelInstallationResponseDTO("Invalid user id", Status.NOT_FOUND);
        }
        User user = userOptional.get();

        // check if user id matches
        if (oldInstallation.getUser().getUserId() != user.getUserId()){
            return new SolarPanelInstallationResponseDTO("Invalid user id", Status.ERROR);
        }

        // convert dto to installation instance
        SolarPanelInstallation installation = new SolarPanelInstallation();
        installation.setUser(user);
        installation.setGeoLocation(dto.getGeoLocation());
        installation.setAddress(dto.getAddress());
        installation.setPostcode(dto.getPostcode());
        installation.setState(dto.getState());
        installation.setType(dto.getType());

        // update old installation
        oldInstallation.update(installation);
        solarPanelInstallationRepository.save(oldInstallation);

        return new SolarPanelInstallationResponseDTO(oldInstallation, Status.SUCCESS);
    }

    public SolarPanelInstallationResponseDTO deleteInstallation(Authentication authentication, int panelId) {
        // try to find installation by id
        Optional<SolarPanelInstallation> installationOptional = solarPanelInstallationRepository.findById(panelId);
        if (installationOptional.isEmpty()) {
            return new SolarPanelInstallationResponseDTO("Invalid panel id", Status.NOT_FOUND);
        }

        SolarPanelInstallation found = installationOptional.get();

        // try to find user
        Optional<User> userOptional = userRepository.findByEmail(authentication.getName());
        if (userOptional.isEmpty()) {
            return new SolarPanelInstallationResponseDTO("Invalid user id", Status.NOT_FOUND);
        }
        User user = userOptional.get();

        // check if user id matches
        if (user.getUserId() != found.getUser().getUserId()){
            return new SolarPanelInstallationResponseDTO("Invalid user id", Status.ERROR);
        }

        // fetch all panels related to this installation
        List<SolarPanel> relatedPanels = solarPanelRepository.findSolarPanelsBySolarPanelInstallation(found);

        // here, you can either delete the related panel
        solarPanelRepository.deleteAll(relatedPanels);

        // or, if you want to keep the panel but just remove its reference to the installation
//        for (SolarPanel panel : relatedPanels) {
//             panel.setInstallation(null);
//             solarPanelRepository.save(panel);
//        }

        // delete installation
        solarPanelInstallationRepository.delete(found);
        return new SolarPanelInstallationResponseDTO(found, Status.SUCCESS);
    }

    public List<SolarPanelInstallation> getInstallationsByEmail(String email) {
        System.out.println("Getting all installations for " + email);

        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.map(user -> solarPanelInstallationRepository.findSolarPanelInstallationsByUser(user)).orElse(null);
    }
}
