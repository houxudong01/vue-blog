package com.simpleblog.vueblog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:hxd
 * @date:2020/5/31
 */
@ConfigurationProperties("swagger")
public class SwaggerProperties {
    private Boolean enabled;
    private String title;
    private String description;
    private String termsOfServiceUrl;
    private Contact contact;
    private String license;
    private String licenseUrl;
    private String version;
    private List<VendorExtension> vendorExtensions = new ArrayList<>();
    private String excluded = "/error.html";

    public String getExcluded() {
        return excluded;
    }

    public void setExcluded(String excluded) {
        this.excluded = excluded;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<VendorExtension> getVendorExtensions() {
        return vendorExtensions;
    }

    public void setVendorExtensions(List<VendorExtension> vendorExtensions) {
        this.vendorExtensions = vendorExtensions;
    }

    @Override
    public String toString() {
        return "SwaggerProperties{" +
                "enabled=" + enabled +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", termsOfServiceUrl='" + termsOfServiceUrl + '\'' +
                ", contact=" + contact +
                ", license='" + license + '\'' +
                ", licenseUrl='" + licenseUrl + '\'' +
                ", version='" + version + '\'' +
                ", vendorExtensions=" + vendorExtensions +
                ", excluded='" + excluded + '\'' +
                '}';
    }
}
