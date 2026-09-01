package org.example.logitrack.service;

import lombok.RequiredArgsConstructor;
import org.example.logitrack.dto.SettingsRequest;
import org.example.logitrack.model.Settings;
import org.example.logitrack.repository.SettingsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingsService {

    private final SettingsRepository settingsRepository;

    public Settings getSettings() {
        return settingsRepository.findById(1L)
                .orElseGet(() -> {
                    Settings settings = new Settings();
                    settings.setId(1L);
                    settings.setAppName("LogiTrack");
                    settings.setLanguage("fr");
                    settings.setTimezone("Africa/Casablanca");
                    settings.setDateFormat("DD/MM/YYYY");
                    settings.setDefaultRole("AGENT");
                    settings.setSessionTimeout(60);
                    settings.setMaxUsers(100);
                    settings.setMinPwdLength(8);
                    settings.setLowStockThreshold(10);

                    return settingsRepository.save(settings);
                });
    }

    public Settings updateSettings(SettingsRequest request) {
        Settings settings = getSettings();
        settings.setAppName(request.getAppName());
        settings.setLanguage(request.getLanguage());
        settings.setTimezone(request.getTimezone());
        settings.setDateFormat(request.getDateFormat());

        settings.setMaintenanceMode(request.isMaintenanceMode());

        settings.setDefaultRole(request.getDefaultRole());
        settings.setSessionTimeout(request.getSessionTimeout());
        settings.setMaxUsers(request.getMaxUsers());

        settings.setMinPwdLength(request.getMinPwdLength());
        settings.setRequireUppercase(request.isRequireUppercase());
        settings.setRequireNumbers(request.isRequireNumbers());
        settings.setRequireSpecial(request.isRequireSpecial());
        settings.setTwoFactor(request.isTwoFactor());

        settings.setEmailNotif(request.isEmailNotif());
        settings.setLowStockAlert(request.isLowStockAlert());
        settings.setNewOrderAlert(request.isNewOrderAlert());
        settings.setUserRegAlert(request.isUserRegAlert());

        settings.setLowStockThreshold(request.getLowStockThreshold());

        return settingsRepository.save(settings);
    }
}