package alnero.profiles;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Customer profile.
 */
public class Profile {
    /**
     * Customer address.
     */
    private Address address;

    /**
     * Create profile with address.
     * @param address customer address
     */
    public Profile(Address address) {
        this.address = address;
    }

    /**
     * Generate list of addresses from list of profiles.
     * @param profiles list of profiles
     * @return list of addresses
     */
    public List<Address> collect(List<Profile> profiles) {
        List<Address> result = profiles.stream().map(profile -> profile.getAddress()).collect(Collectors.toList());
        return result;
    }

    /**
     * Generate distinct and sorted list of addresses from list of profiles.
     * @param profiles list of profiles
     * @return distinct and sorted list of addresses
     */
    public List<Address> collectDistinctAndSorted(List<Profile> profiles) {
        List<Address> result = profiles
                .stream()
                .map(profile -> profile.getAddress())
                .sorted(Comparator.comparing(address -> address.getCity()))
                .distinct()
                .collect(Collectors.toList());
        return result;
    }

    /**
     * Get address from profile.
     * @return customer address
     */
    public Address getAddress() {
        return address;
    }
}
