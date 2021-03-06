package com.tracebucket.x1.partner.api.test.service;

import com.tracebucket.x1.dictionary.api.domain.jpa.impl.DefaultAddress;
import com.tracebucket.x1.partner.api.DefaultPartnerStarter;
import com.tracebucket.x1.partner.api.dictionary.PartnerCategory;
import com.tracebucket.x1.partner.api.domain.impl.jpa.DefaultOwner;
import com.tracebucket.x1.partner.api.domain.impl.jpa.DefaultPartner;
import com.tracebucket.x1.partner.api.domain.impl.jpa.DefaultPartnerRole;
import com.tracebucket.x1.partner.api.service.DefaultPartnerService;
import com.tracebucket.x1.partner.api.test.fixture.DefaultAddressFixture;
import com.tracebucket.x1.partner.api.test.fixture.DefaultAffiliateFixture;
import com.tracebucket.x1.partner.api.test.fixture.DefaultOwnerFixture;
import com.tracebucket.x1.partner.api.test.fixture.DefaultPartnerFixture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Set;
import java.util.UUID;

/**
 * Created by Vishwajit on 26-05-2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes  = {DefaultPartnerStarter.class})
public class PartnerServiceTest {

    @Autowired
    private DefaultPartnerService partnerService;

    private DefaultPartner partner = null;

    @Before
    public void setUp() throws Exception{

    }

    private void createPartner() throws Exception{
        partner = DefaultPartnerFixture.standardPartner();
        partner.setOwner(new DefaultOwner(UUID.randomUUID().toString()));
        partner = partnerService.save(partner.getOwner().getOrganizationUID(), partner);
    }

    @Test
    public void testCreate() throws Exception {
        createPartner();
        Assert.assertNotNull(partner.getAggregateId());
    }

    @Test
    public void testFindOne() throws Exception{
        createPartner();
        partner = partnerService.findOne(partner.getOwner().getOrganizationUID(), partner.getAggregateId());
        Assert.assertNotNull(partner);
    }

    @Test
    public void setPartnerCategory() throws Exception{
        createPartner();
        partner = partnerService.setPartnerCategory(partner.getOwner().getOrganizationUID(), PartnerCategory.ORGANIZATION, partner.getAggregateId());
        Assert.assertNotNull(partner);
        Assert.assertEquals(partner.getPartnerCategory(), PartnerCategory.ORGANIZATION);
    }

     @Test
     public void movePartnerToCategory() throws Exception{
         createPartner();
         partner = partnerService.movePartnerToCategory(partner.getOwner().getOrganizationUID(), PartnerCategory.INDIVIDUAL, partner.getAggregateId());
         Assert.assertNotNull(partner);
         Assert.assertEquals(partner.getPartnerCategory(), PartnerCategory.INDIVIDUAL);
     }

    @Test
     public void addPartnerRole() throws Exception{
        createPartner();
        partner.getAllAssignedRoles().add(DefaultAffiliateFixture.standardAffiliate());
        partner = partnerService.addPartnerRole(partner.getOwner().getOrganizationUID(), partner);
        Assert.assertNotNull(partner);
        Assert.assertEquals(2, partner.getAllAssignedRoles().size());
    }

    @Test
    public void addAddressToRole() throws Exception{

        createPartner();
        DefaultAddress address = DefaultAddressFixture.standardAddress();
        Set<DefaultPartnerRole> partnerRoles = partner.getAllAssignedRoles();

        Assert.assertNotNull(partner);
        Assert.assertNotNull(partnerRoles);

        for(DefaultPartnerRole partnerRole: partnerRoles){
            partner = partnerService.addAddressToRole(partner.getOwner().getOrganizationUID(), partnerRole.getEntityId(),address, partner.getAggregateId());
            break;
        }

        Set<DefaultPartnerRole> partnerRoles1 = partner.getAllAssignedRoles();
        for(DefaultPartnerRole partnerRole: partnerRoles1){
            Assert.assertEquals(1,partnerRole.getAddresses().size());
            break;
        }

    }

    @Test
    public void moveRoleAddressTo() throws Exception{

        createPartner();
        Set<DefaultPartnerRole> partnerRoles = partner.getAllAssignedRoles();
        Assert.assertNotNull(partnerRoles);

        DefaultAddress address = DefaultAddressFixture.standardAddress();

        for(DefaultPartnerRole partnerRole: partnerRoles){
            partner = partnerService.moveRoleAddressTo(partner.getOwner().getOrganizationUID(), partnerRole.getEntityId(),address,partner.getAggregateId());
            break;
        }

        Set<DefaultPartnerRole> partnerRoles1 = partner.getAllAssignedRoles();
        Assert.assertNotNull(partnerRoles1);

        for(DefaultPartnerRole partnerRole: partnerRoles1){
            Assert.assertEquals(1,partnerRole.getAddresses().size());
            break;
        }

        Assert.assertNotNull(partner);
       Assert.assertNotNull(partner.getAllAssignedRoles());
    }

    @Test
    public void changeOwner() throws Exception{
        createPartner();
        String organizationUid = UUID.randomUUID().toString();
        DefaultOwner owner = DefaultOwnerFixture.standardOwner(organizationUid);
        partner = partnerService.changeOwner(partner.getOwner().getOrganizationUID(), owner, partner.getAggregateId());
        Assert.assertNotNull(partner);
        Assert.assertEquals(organizationUid, partner.getOwner().getOrganizationUID());
    }

    @Test
    public void hasPartnerRole() throws Exception{
        createPartner();
        Set<DefaultPartnerRole> partnerRoles = partner.getAllAssignedRoles();
        Assert.assertNotNull(partnerRoles);
        for(DefaultPartnerRole partnerRole : partnerRoles){
            Assert.assertTrue(partnerService.hasPartnerRole(partner.getOwner().getOrganizationUID(), partner.getAggregateId(), partnerRole.getEntityId()));
            break;
        }
    }

    @After
    public void tearDown(){
        if(partner != null && partner.getAggregateId() != null) {
            partnerService.delete(partner.getOwner().getOrganizationUID(), partner.getAggregateId());
            partner = partnerService.findOne(partner.getOwner().getOrganizationUID(), partner.getAggregateId());
            Assert.assertNull(partner);
        }
    }
}
