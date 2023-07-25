package dev.lpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");

        printData("Phone list", phones);
//        --------------------
//        Phone list
//        --------------------
//        Charlie Brown: [] [(333) 444-5555]
//        Maid Marion: [] [(123) 456-7890]
//        Mickey Mouse: [] [(999) 888-7777]
//        Mickey Mouse: [] [(124) 748-9758]
//        Minnie Mouse: [] [(456) 780-5666]
//        Robin Hood: [] [(564) 789-3000]
//        Robin Hood: [] [(789) 902-8222]
//        Mickey Mouse: [] [(999) 888-7777]

        printData("Email list", emails);
//        --------------------
//        Email list
//        --------------------
//        Mickey Mouse: [mckmouse@gmail.com] []
//        Mickey Mouse: [micky1@aws.com] []
//        Minnie Mouse: [minnie@verizon.net] []
//        Robin Hood: [rhood@gmail.Com] []
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Daffy Duck: [daffy@google.com] []

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);
        printData("Phone contacts", phoneContacts);
//        --------------------
//        Phone contacts
//        -------------------
//        Charlie Brown: [] [(333) 444-5555]
//        Maid Marion: [] [(123) 456-7890]
//        Robin Hood: [] [(564) 789-3000]
//        Mickey Mouse: [] [(999) 888-7777]
//        Minnie Mouse: [] [(456) 780-5666]

        printData("Email contacts", emailContacts);
//        --------------------
//        Email contacts
//        --------------------
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Robin Hood: [rhood@gmail.Com] []
//        Mickey Mouse: [mckmouse@gmail.com] []
//        Daffy Duck: [daffy@google.com] []
//        Minnie Mouse: [minnie@verizon.net] []*

        int index = emails.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmail("Sherwood Forest");
//        Robin Hood now has email RHood@sherwoodforest.com
        robinHood.addEmail("Sherwood Forest");
//        Robin Hood already has email RHood@sherwoodforest.com
        robinHood.replaceEmailIfExists("RHood@sherwoodforest.com", "RHood@sherwoodforest.org");
        System.out.println(robinHood);
//        Robin Hood: [RHood@sherwoodforest.org, rhood@gmail.Com] []

        Set<Contact> unionAB = new HashSet<>();
        unionAB.addAll(emailContacts);
        unionAB.addAll(phoneContacts);
        printData("(A ∪ B) Union of emails (A) with phones (B)", unionAB);
//        --------------------
//        (A ∪ B) Union of emails (A) with phones (B)
//        --------------------
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Charlie Brown: [] [(333) 444-5555]
//        Maid Marion: [] [(123) 456-7890]
//        Robin Hood: [RHood@sherwoodforest.org, rhood@gmail.Com] []
//        Mickey Mouse: [mckmouse@gmail.com] []
//        Daffy Duck: [daffy@google.com] []
//        Minnie Mouse: [minnie@verizon.net] []

        Set<Contact> intersectAB = new HashSet<>(emailContacts);
        intersectAB.retainAll(phoneContacts);
        printData("(A ∩ B) intersect emails (A) and phones (B)", intersectAB);
//        --------------------
//        (A ∩ B) intersect emails (A) and phones (B)
//        --------------------
//        Robin Hood: [RHood@sherwoodforest.org, rhood@gmail.Com] []
//        Mickey Mouse: [mckmouse@gmail.com] []
//        Minnie Mouse: [minnie@verizon.net] []

        Set<Contact> intersectBA = new HashSet<>(phoneContacts);
        intersectBA.retainAll(emailContacts);
        printData("(B ∩ A) intersect phones (B) and emails (A)", intersectBA);
//        --------------------
//        (B ∩ A) intersect phones (B) and emails (A)
//        --------------------
//        Robin Hood: [] [(564) 789-3000]
//        Mickey Mouse: [] [(999) 888-7777]
//        Minnie Mouse: [] [(456) 780-5666]


        Set<Contact> AMinusB = new HashSet<>(emailContacts);
        AMinusB.removeAll(phoneContacts);
        printData("(A - B) emails (A) - phones (B)", AMinusB);
//        --------------------
//        (A - B) intersect emails (A) - phones (B)
//        --------------------
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Daffy Duck: [daffy@google.com] []

        Set<Contact> BMinusA = new HashSet<>(phoneContacts);
        BMinusA.removeAll(emailContacts);
        printData("(B - A) phones (B) - emails (A)", BMinusA);
//        --------------------
//        (B - A) phones (B) - emails (A)
//        --------------------
//        Charlie Brown: [] [(333) 444-5555]
//        Maid Marion: [] [(123) 456-7890]

        Set<Contact> symmetricDiff = new HashSet<>(AMinusB);
        symmetricDiff.addAll(BMinusA);
        printData("Symmetric difference: phones and emails", symmetricDiff);
//        --------------------
//        Symmetric difference: phones and emails
//        --------------------
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Charlie Brown: [] [(333) 444-5555]
//        Maid Marion: [] [(123) 456-7890]
//        Daffy Duck: [daffy@google.com] []

        Set<Contact> symmetricDiff2 = new HashSet<>(unionAB);
        symmetricDiff2.removeAll(intersectAB);
        printData("Symmetric difference: phones and emails", symmetricDiff2);
//        --------------------
//        Symmetric difference: phones and emails
//        --------------------
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Charlie Brown: [] [(333) 444-5555]
//        Maid Marion: [] [(123) 456-7890]
//        Daffy Duck: [daffy@google.com] []
    }

    public static void printData(String header, Collection<Contact> contacts) {

        System.out.println("-".repeat(20));
        System.out.println(header);
        System.out.println("-".repeat(20));
        contacts.forEach(System.out::println);
    }
}
