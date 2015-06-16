package com.tracebucket.x1.partner.api.rest.resources;

import com.tracebucket.x1.dictionary.api.domain.Person;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sadath on 26-May-2015.
 */
public class DefaultAffiliateResource extends DefaultPartnerRoleResource {
    private static final String simpleName = "Affiliate";
    private String businessName;
    private String code;
    private Date dateOfIncorporation;
    private String logo;
    private String website;
    private Set<DefaultPersonResource> persons = new HashSet<DefaultPersonResource>(0);
    private String name;
    private Set<DefaultAddressResource> addresses = new HashSet<DefaultAddressResource>(0);

    public static String getSimpleName() {
        return simpleName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateOfIncorporation() {
        return dateOfIncorporation;
    }

    public void setDateOfIncorporation(Date dateOfIncorporation) {
        this.dateOfIncorporation = dateOfIncorporation;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<DefaultPersonResource> getPersons() {
        return persons;
    }

    public void setPersons(Set<DefaultPersonResource> persons) {
        this.persons = persons;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<DefaultAddressResource> getAddresses() {
        return addresses;
    }

    @Override
    public void setAddresses(Set<DefaultAddressResource> addresses) {
        this.addresses = addresses;
    }
}
