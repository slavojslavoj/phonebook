package ru.netology;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {

    private Set<Contact> contactSet;

    public PhoneBook() {
        this.contactSet = new HashSet<>();
    }

    public void addContact(Contact contact) {
        contactSet.remove(contact);
        contactSet.add(contact);
    }

    public void setContactSet(Collection<Contact> contacts) {
        contactSet.addAll(contacts);
    }

    public Collection<Contact> getContactSet() {
        return contactSet;
    }

    public void deleteContact(String phoneNumber) {
        contactSet.removeIf(contact -> contact.getPhoneNumber().equals(phoneNumber));
    }

    public void showAllContacts() {
        System.out.println(contactSet);
    }

    public void searchContact(String firstlOrLastName) {
        List<Contact> result = new ArrayList<>();
        List<Contact> firstNameResult = contactSet.stream().filter(x -> x.getFirstName().equals(firstlOrLastName)).collect(Collectors.toList());
        List<Contact> lastNameResult = contactSet.stream().filter(x -> x.getLastName().equals(firstlOrLastName)).collect(Collectors.toList());
        result.addAll(firstNameResult);
        result.addAll(lastNameResult);
        System.out.println(result);
    }
}
