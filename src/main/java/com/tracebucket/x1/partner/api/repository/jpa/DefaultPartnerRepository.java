package com.tracebucket.x1.partner.api.repository.jpa;

import com.tracebucket.tron.ddd.domain.AggregateId;
import com.tracebucket.tron.ddd.jpa.BaseAggregateRepository;
import com.tracebucket.x1.partner.api.domain.impl.jpa.DefaultPartner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sadath on 26-May-2015.
 */
public interface DefaultPartnerRepository extends BaseAggregateRepository<DefaultPartner, AggregateId> {
    @Query(value = "select p from com.tracebucket.x1.partner.api.domain.impl.jpa.DefaultPartner p where p.owner.organizationUID = :organizationUid")
    public List<DefaultPartner> findPartnersByOrganization(@Param("organizationUid") String organizationUid);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :organizationUid and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND (pe.ORGANIZATION_UNIT__ID = :organizationUnitUid AND pe.POSITION__ID = :positionUid) OR (pe.ORGANIZATION_UNIT__ID IS NULL AND pe.POSITION__ID IS NULL)", nativeQuery = true)
    public List<DefaultPartner> getEmployeesAssignedAndNotToOrganizationAndPosition(@Param("organizationUid") String organizationUid, @Param("organizationUnitUid") String organizationUnitUid, @Param("positionUid") String positionUid);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :organizationUid and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND (pe.ORGANIZATION_UNIT__ID = :organizationUnitUid AND pe.POSITION__ID = :positionUid)", nativeQuery = true)
    public List<DefaultPartner> getEmployeesAssignedToOrganizationAndPosition(@Param("organizationUid") String organizationUid, @Param("organizationUnitUid") String organizationUnitUid, @Param("positionUid") String positionUid);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :organizationUid and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND (pe.ORGANIZATION_UNIT__ID = :organizationUnitUid)", nativeQuery = true)
    public List<DefaultPartner> getEmployeesAssignedToOrganizationUnit(@Param("organizationUid") String organizationUid, @Param("organizationUnitUid") String organizationUnitUid);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :organizationUid and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND ((pe.ORGANIZATION_UNIT__ID IS NULL OR pe.ORGANIZATION_UNIT__ID = :organizationUnitUid) AND pe.POSITION__ID IS NULL)", nativeQuery = true)
    public List<DefaultPartner> getEmployeesNotAssignedToOrganizationAndPosition(@Param("organizationUid") String organizationUid, @Param("organizationUnitUid") String organizationUnitUid);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :organizationUid and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND (pe.ORGANIZATION_UNIT__ID IS NOT NULL AND pe.POSITION__ID IS NOT NULL)", nativeQuery = true)
    public List<DefaultPartner> getEmployeesAssignedToOrganizationAndPosition(@Param("organizationUid") String organizationUid);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :organizationUid and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND (pe.ORGANIZATION_UNIT__ID = :organizationUnitUid AND pe.DEPARTMENT__ID = :departmentUid)", nativeQuery = true)
    public List<DefaultPartner> getEmployeesAssignedToOrganizationUnitAndDepartment(@Param("organizationUid") String organizationUid, @Param("organizationUnitUid") String organizationUnitUid, @Param("departmentUid") String departmentUid);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :organizationUid and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND (pe.ORGANIZATION_UNIT__ID = :organizationUnitUid AND pe.DEPARTMENT__ID = :departmentUid AND pe.IS_USER = true AND pe.USERNAME IS NOT NULL)", nativeQuery = true)
    public List<DefaultPartner> getEmployeeUsersAssignedToOrganizationUnitAndDepartment(@Param("organizationUid") String organizationUid, @Param("organizationUnitUid") String organizationUnitUid, @Param("departmentUid") String departmentUid);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :organizationUid and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND (pe.ORGANIZATION_UNIT__ID = :organizationUnitUid AND pe.IS_USER = true AND pe.USERNAME IS NOT NULL)", nativeQuery = true)
    public List<DefaultPartner> getEmployeeUsersAssignedToOrganizationUnit(@Param("organizationUid") String organizationUid, @Param("organizationUnitUid") String organizationUnitUid);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :organizationUid and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND (pe.IS_USER = true AND pe.USERNAME IS NOT NULL)", nativeQuery = true)
    public List<DefaultPartner> getEmployeeUsers(@Param("organizationUid") String organizationUid);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :organizationUid and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND (pe.ORGANIZATION_UNIT__ID = :organizationUnitUid AND pe.POSITION__ID = :positionUid AND pe.DEPARTMENT__ID = :departmentUid)", nativeQuery = true)
    public List<DefaultPartner> getEmployeesAssignedToOrganizationUnitAndPositionAndDepartment(@Param("organizationUid") String organizationUid, @Param("organizationUnitUid") String organizationUnitUid, @Param("positionUid") String positionUid, @Param("departmentUid") String departmentUid);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :tenantId and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND pe.USERNAME = :username", nativeQuery = true)
    public DefaultPartner getLoggedInEmployeeDetails(@Param("tenantId") String tenantId, @Param("username") String username);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :tenantId and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND pe.USERNAME IS NULL and pe.IS_USER = false", nativeQuery = true)
    public List<DefaultPartner> getEmployeesWhoAreNotUsers(@Param("tenantId") String tenantId);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :tenantId and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND pe.USERNAME = :username and pe.IS_USER = true", nativeQuery = true)
    public DefaultPartner getEmployeeByUsername(@Param("tenantId") String tenantId, @Param("username") String username);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :tenantId and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND p.ID = :partnerUid and pe.PARTNER_ROLE__ID = :roleUid", nativeQuery = true)
    public DefaultPartner getEmployee(@Param("tenantId") String tenantId, @Param("partnerUid") String partnerUid, @Param("roleUid") String roleUid);


    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and p.ORGANIZATION__ID = :tenantId and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND pe.USERNAME IN(:userNames) and pe.IS_USER = true", nativeQuery = true)
    public List<DefaultPartner> getEmployeesByLoginNames(@Param("tenantId") String tenantId, @Param("userNames") List<String> userNames);

    @Query(value = "SELECT p.* FROM PARTNER p INNER JOIN PARTNER_ROLE pr ON p.ID = pr.PARTNER__ID \n" +
            "INNER JOIN PARTNER_EMPLOYEE pe ON pe.PARTNER_ROLE__ID = pr.ID\n" +
            "WHERE p.PASSIVE = false and pr.PARTNER_ROLE = 'PARTNER_EMPLOYEE' AND pe.USERNAME IN(:userNames) and pe.IS_USER = true", nativeQuery = true)
    public List<DefaultPartner> getEmployeesByLoginNames(@Param("userNames") List<String> userNames);
}