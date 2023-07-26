package dev.lpa;

import java.util.*;

public class TreeSetMain {

    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

//        NavigableSet<Contact> sorted = new TreeSet<>(phones);
        Comparator<Contact> mySort = Comparator.comparing(Contact::getName);
        NavigableSet<Contact> sorted = new TreeSet<>(mySort);
        sorted.addAll(phones);
        sorted.forEach(System.out::println);
//        Charlie Brown: [] [(333) 444-5555]
//        Maid Marion: [] [(123) 456-7890]
//        Mickey Mouse: [] [(999) 888-7777]
//        Minnie Mouse: [] [(456) 780-5666]
//        Robin Hood: [] [(564) 789-3000]

        NavigableSet<String> justNames = new TreeSet<>();
        phones.forEach(c -> justNames.add(c.getName()));
        System.out.println(justNames);
//        [Charlie Brown, Maid Marion, Mickey Mouse, Minnie Mouse, Robin Hood]

        NavigableSet<Contact> fullSet = new TreeSet(sorted);
        fullSet.addAll(emails);
        fullSet.forEach(System.out::println);
//        Charlie Brown: [] [(333) 444-5555]
//        Daffy Duck: [daffy@google.com] []
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Maid Marion: [] [(123) 456-7890]
//        Mickey Mouse: [] [(999) 888-7777]
//        Minnie Mouse: [] [(456) 780-5666]
//        Robin Hood: [] [(564) 789-3000]

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.sort(sorted.comparator());
        System.out.println("-".repeat(20));
        fullList.forEach(System.out::println);
//        --------------------
//        Charlie Brown: [] [(333) 444-5555]
//        Daffy Duck: [daffy@google.com] []
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Maid Marion: [] [(123) 456-7890]
//        Mickey Mouse: [] [(999) 888-7777]
//        Mickey Mouse: [] [(124) 748-9758]
//        Mickey Mouse: [] [(999) 888-7777]
//        Mickey Mouse: [mckmouse@gmail.com] []
//        Mickey Mouse: [micky1@aws.com] []
//        Minnie Mouse: [] [(456) 780-5666]
//        Minnie Mouse: [minnie@verizon.net] []
//        Robin Hood: [] [(564) 789-3000]
//        Robin Hood: [] [(789) 902-8222]
//        Robin Hood: [rhood@gmail.Com] []

        Contact min = Collections.min(fullSet, fullSet.comparator());
        Contact max = Collections.max(fullSet, fullSet.comparator());

        Contact first = fullSet.first();
        Contact last = fullSet.last();

        System.out.println("-".repeat(20));
        System.out.printf("mix = %s, first = %s%n", min.getName(), first.getName());
        System.out.printf("max = %s, last = %s%n", max.getName(), last.getName());
        System.out.println("-".repeat(20));
//        --------------------
//        mix = Charlie Brown, first = Charlie Brown
//        max = Robin Hood, last = Robin Hood
//        --------------------

        NavigableSet<Contact> copiedSet = new TreeSet<>(fullSet);
        System.out.println("First element = " + copiedSet.pollFirst());
//        First element = Charlie Brown: [] [(333) 444-5555]
        System.out.println("Last element = " + copiedSet.pollLast());
//        Last element = Robin Hood: [] [(564) 789-3000]
        copiedSet.forEach(System.out::println);
//        Daffy Duck: [daffy@google.com] []
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Maid Marion: [] [(123) 456-7890]
//        Mickey Mouse: [] [(999) 888-7777]
//        Minnie Mouse: [] [(456) 780-5666]
        System.out.println("-".repeat(20));
    }
}
