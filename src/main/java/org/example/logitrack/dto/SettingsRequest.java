package org.example.logitrack.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsRequest {

    private String appName;
    private String language;
    private String timezone;
    private String dateFormat;
    private boolean maintenanceMode;

    private String defaultRole;
    private Integer sessionTimeout;
    private Integer maxUsers;

    private Integer minPwdLength;
    private boolean requireUppercase;
    private boolean requireNumbers;
    private boolean requireSpecial;
    private boolean twoFactor;

    private boolean emailNotif;
    private boolean lowStockAlert;
    private boolean newOrderAlert;
    private boolean userRegAlert;
    private Integer lowStockThreshold;
}