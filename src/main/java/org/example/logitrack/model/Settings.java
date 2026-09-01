package org.example.logitrack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
