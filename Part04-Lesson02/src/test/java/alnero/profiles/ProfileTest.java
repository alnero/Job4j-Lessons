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

    /**
     * Test collection of distinct and sorted addresses from list of profiles.
     */
    @Test
    public void whenCollectDistinctAndSortedAddressesFromProfilesThenExpectedListOfAddressesReturned() {
        Address addressOne = new Address("A_City", "StreetOne", 1, 1);
        Address addressOneCopy = new Address("A_City", "StreetOne", 1, 1);
        Address addressTwo = new Address("B_City", "StreetTwo", 2, 2);
        Address addressTwoCopy = new Address("B_City", "StreetTwo", 2, 2);
        Address addressThree = new Address("C_City", "StreetThree", 3, 3);
        Address addressThreeCopy = new Address("C_City", "StreetThree", 3, 3);
        List<Address> expectedDistinctAndSortedListOfAddresses = Arrays.asList(addressOne, addressTwo, addressThree);

        Profile profileOne = new Profile(addressOne);
        Profile profileOneCopy = new Profile(addressOneCopy);
        Profile profileTwo = new Profile(addressTwo);
        Profile profileTwoCopy = new Profile(addressTwoCopy);
        Profile profileThree = new Profile(addressThree);
        Profile profileThreeCopy = new Profile(addressThreeCopy);
        List<Profile> sourceListOfProfiles = Arrays.asList(profileThree, profileThreeCopy, profileOneCopy, profileTwoCopy, profileOne, profileTwo);

        List<Address> resultListOfAddresses = profileOne.collectDistinctAndSorted(sourceListOfProfiles);
        assertEquals(resultListOfAddresses, expectedDistinctAndSortedListOfAddresses);
    }
}
