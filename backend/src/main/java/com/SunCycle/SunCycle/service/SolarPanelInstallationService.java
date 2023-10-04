package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.model.SolarPanelInstallationDTO;
import com.SunCycle.SunCycle.model.Status;
import com.SunCycle.SunCycle.model.User;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
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
    private UserRepository userRepository;

    public SolarPanelInstallation createSolarPanelInstallation(int userId, String geoLocation, String address, String state, int postcode, String type){
        System.out.println("creating solar panel installation");
        System.out.println(userId);
        System.out.println(geoLocation);
        System.out.println(address);
        System.out.println(state);
        System.out.println(postcode);
        System.out.println(type);

        SolarPanelInstallation newInstallation = new SolarPanelInstallation(userId, geoLocation, address, state, postcode, type);

        return solarPanelInstallationRepository.save(newInstallation);
    }

    public SolarPanelInstallationDTO updateSolarPanelInstallation(Authentication authentication, int panelId, SolarPanelInstallation newInstallation) {
        Optional<SolarPanelInstallation> installationOptional = solarPanelInstallationRepository.findById(panelId);
        if (!installationOptional.isPresent()) {
            return new SolarPanelInstallationDTO("no installation found", Status.NOT_FOUND);
        }

        SolarPanelInstallation installation = installationOptional.get();

        if (installation.getUserId() != installation.getUserId()) {
            return new SolarPanelInstallationDTO("invalid user id", Status.ERROR);
        }

        Optional<User> userOptional = userRepository.findByEmail(authentication.getName());
        if (!userOptional.isPresent()) {
            return new SolarPanelInstallationDTO("invalid user id", Status.NOT_FOUND);
        }
        User user = userOptional.get();
        if (user.getUserId() != installation.getUserId()){
            return new SolarPanelInstallationDTO("invalid user id", Status.ERROR);
        }

        installation.update(newInstallation);
        solarPanelInstallationRepository.save(installation);

        return new SolarPanelInstallationDTO(installation, Status.SUCCESS);
    }

    public SolarPanelInstallationDTO deleteInstallation(Authentication authentication, int panelId) {
        Optional<SolarPanelInstallation> installationOptional = solarPanelInstallationRepository.findById(panelId);
        if (!installationOptional.isPresent()) {
            return new SolarPanelInstallationDTO("invalid panel id", Status.NOT_FOUND);
        }

        SolarPanelInstallation found = installationOptional.get();
        Optional<User> userOptional = userRepository.findByEmail(authentication.getName());
        if (!userOptional.isPresent()) {
            return new SolarPanelInstallationDTO("invalid user id", Status.NOT_FOUND);
        }
        User user = userOptional.get();
        if (user.getUserId() != found.getUserId()){
            return new SolarPanelInstallationDTO("invalid user id", Status.ERROR);
        }

        solarPanelInstallationRepository.delete(found);
        return new SolarPanelInstallationDTO(found, Status.SUCCESS);
    }

    public List<SolarPanelInstallation> getInstallationsByEmail(String email) {
        System.out.println("getting all installations for " + email);

        User user = userRepository.findByEmail(email).get();
        int userId = user.getUserId();
        List<SolarPanelInstallation> installations = solarPanelInstallationRepository.findSolarPanelInstallationsByUserId(userId);

        return installations;
    }
}
