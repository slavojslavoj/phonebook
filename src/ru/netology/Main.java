package ru.netology;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PhoneBook phoneBook = loadPhoneBook();
        boolean exit = false;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nВыберите действие:\n" +
                    "ADD    Добавить контакт\n" +
                    "DEL    Удалить контакт\n" +
                    "ALL    Показать все контакты\n" +
                    "FIND   Поиск по имени или фамилии\n" +
                    "EXIT   Выйти из программы\n");

            switch (scanner.nextLine()) {
                case "ADD":
                    Contact contact = new Contact();
                    System.out.println("Введите номер:");
                    contact.setPhoneNumber(scanner.nextLine());
                    System.out.println("Введите имя:");
                    contact.setFirstName(scanner.nextLine());
                    System.out.println("Введите фамилию:");
                    contact.setLastName(scanner.nextLine());
                    System.out.println("Введите тип (WORK, FAMILY, FRIENDS):");
                    contact.setType(ContactType.valueOf(scanner.nextLine()));
                    phoneBook.addContact(contact);
                    break;

                case "DEL":
                    System.out.println("Введите номер для удаления:");
                    phoneBook.deleteContact(scanner.nextLine());
                    break;

                case "ALL":
                    phoneBook.showAllContacts();
                    break;

                case "FIND":
                    System.out.println("Введите имя или фамилию:");
                    String inputFirstName = scanner.nextLine();
                    phoneBook.searchContact(inputFirstName);
                    break;

                case "EXIT":
                    exit = true;
            }
        } while(!exit);

        savePhoneBook(phoneBook);
    }

    static void savePhoneBook(PhoneBook phoneBook) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("phonebook.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        List<Contact> serializableList = new ArrayList<>(phoneBook.getContactSet());
        objectOutputStream.writeObject(serializableList);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }

    static PhoneBook loadPhoneBook() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("phonebook.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Collection<Contact> deserializedContacts = (Collection<Contact>) objectInputStream.readObject();
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setContactSet(deserializedContacts);
        return phoneBook;
    }
}
