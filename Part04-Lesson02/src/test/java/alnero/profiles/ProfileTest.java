package alnero.profiles;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Profile class testing.
 */
public class ProfileTest {
    /**
     * Test collection of addresses from list of profiles.
     */
    @Test
    public void whenCollectAddressesFromProfilesThenExpectedListOfAddressesReturned() {
        Address addressOne = new Address("CityOne", "StreetOne", 1, 1);
        Address addressTwo = new Address("CityTwo", "StreetTwo", 2, 2);
        Address addressThree = new Address("CityThree", "StreetThree", 3, 3);
        List<Address> expectedListOfAddresses = Arrays.asList(addressOne, addressTwo, addressThree);

        Profile profileOne = new Profile(addressOne);
        Profile profileTwo = new Profile(addressTwo);
        Profile profileThree = new Profile(addressThree);
        List<Profile> sourceListOfProfiles = Arrays.asList(profileOne, profileTwo, profileThree);

        List<Address> resultListOfAddresses = profileOne.collect(sourceListOfProfiles);
        assertEquals(resultListOfAddresses, expectedListOfAddresses);
    }
}
