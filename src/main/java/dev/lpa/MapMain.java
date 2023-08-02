package dev.lpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {

    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
//        Charlie Brown: [] [(333) 444-5555]
//        Maid Marion: [] [(123) 456-7890]
//        Mickey Mouse: [] [(999) 888-7777]
//        Mickey Mouse: [] [(124) 748-9758]
//        Minnie Mouse: [] [(456) 780-5666]
//        Robin Hood: [] [(564) 789-3000]
//        Robin Hood: [] [(789) 902-8222]
//        Mickey Mouse: [] [(999) 888-7777]
//        Mickey Mouse: [mckmouse@gmail.com] []
//        Mickey Mouse: [micky1@aws.com] []
//        Minnie Mouse: [minnie@verizon.net] []
//        Robin Hood: [rhood@gmail.Com] []
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Daffy Duck: [daffy@google.com] []
        promptSeparator();

        Map<String, Contact> contacts = new HashMap<>();
        for (Contact contact : fullList) {
            contacts.put(contact.getName(), contact);
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
//        key = Linus Van Pelt, value = Linus Van Pelt: [lvpelt2015@gmail.com] []
//        key = Minnie Mouse, value = Minnie Mouse: [minnie@verizon.net] []
//        key = Maid Marion, value = Maid Marion: [] [(123) 456-7890]
//        key = Charlie Brown, value = Charlie Brown: [] [(333) 444-5555]
//        key = Robin Hood, value = Robin Hood: [rhood@gmail.Com] []
//        key = Daffy Duck, value = Daffy Duck: [daffy@google.com] []
//        key = Mickey Mouse, value = Mickey Mouse: [micky1@aws.com] []

        promptSeparator();
        System.out.println(contacts.get("Charlie Brown"));
//        Charlie Brown: [] [(333) 444-5555]

        System.out.println(contacts.get("Chuck Brown"));
//        null      // If a key doesn't exist, get returns null

        Contact defaultContact = new Contact("Chuck Brown");
        System.out.println(contacts.getOrDefault("Chuck Brown", defaultContact));
//        Chuck Brown: [] []

        promptSeparator();
        contacts.clear();
        for (Contact contact : fullList) {
            Contact duplicate = contacts.put(contact.getName(), contact);
            if (duplicate != null) {
//                System.out.println("duplicate = " +  duplicate);
//                System.out.println("current = " + contact);
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
//        key = Linus Van Pelt, value = Linus Van Pelt: [lvpelt2015@gmail.com] []
//        key = Minnie Mouse, value = Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
//        key = Maid Marion, value = Maid Marion: [] [(123) 456-7890]
//        key = Charlie Brown, value = Charlie Brown: [] [(333) 444-5555]
//        key = Robin Hood, value = Robin Hood: [rhood@gmail.Com] [(789) 902-8222, (564) 789-3000]
//        key = Daffy Duck, value = Daffy Duck: [daffy@google.com] []
//        key = Mickey Mouse, value = Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

        promptSeparator();
        contacts.clear();
        for (Contact contact : fullList) {
            Contact duplicate = contacts.putIfAbsent(contact.getName(), contact);
            if (duplicate != null) {
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
//        key = Linus Van Pelt, value = Linus Van Pelt: [lvpelt2015@gmail.com] []
//        key = Minnie Mouse, value = Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
//        key = Maid Marion, value = Maid Marion: [] [(123) 456-7890]
//        key = Charlie Brown, value = Charlie Brown: [] [(333) 444-5555]
//        key = Robin Hood, value = Robin Hood: [rhood@gmail.Com] [(789) 902-8222, (564) 789-3000]
//        key = Daffy Duck, value = Daffy Duck: [daffy@google.com] []
//        key = Mickey Mouse, value = Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

        promptSeparator();
        contacts.clear();
        fullList.forEach(contact -> contacts.merge(contact.getName(), contact, Contact::mergeContactData));
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
//        key = Linus Van Pelt, value = Linus Van Pelt: [lvpelt2015@gmail.com] []
//        key = Minnie Mouse, value = Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
//        key = Maid Marion, value = Maid Marion: [] [(123) 456-7890]
//        key = Daffy Duck, value = Daffy Duck: [daffy@google.com] []
//        key = Robin Hood, value = Robin Hood: [rhood@gmail.Com] [(789) 902-8222, (564) 789-3000]
//        key = Charlie Brown, value = Charlie Brown: [] [(333) 444-5555]
//        key = Mickey Mouse, value = Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

        promptSeparator();
        for (String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            contacts.computeIfAbsent(contactName, Contact::new);
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
//        key = Linus Van Pelt, value = Linus Van Pelt: [lvpelt2015@gmail.com] []
//        key = Daisy Duck, value = Daisy Duck: [] []
//        key = Minnie Mouse, value = Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
//        key = Maid Marion, value = Maid Marion: [] [(123) 456-7890]
//        key = Daffy Duck, value = Daffy Duck: [daffy@google.com] []
//        key = Robin Hood, value = Robin Hood: [rhood@gmail.Com] [(789) 902-8222, (564) 789-3000]
//        key = Charlie Brown, value = Charlie Brown: [] [(333) 444-5555]
//        key = Scrooge McDuck, value = Scrooge McDuck: [] []
//        key = Mickey Mouse, value = Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

        promptSeparator();
        for (String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            contacts.computeIfPresent(contactName, (k, v) -> {
                v.addEmail("Fun place");
                return v;
            });
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
//        Daisy Duck now has email DDuck@funplace.com
//        Daffy Duck now has email DDuck@funplace.com
//        Scrooge McDuck now has email SMcDuck@funplace.com
//                key = Linus Van Pelt, value = Linus Van Pelt: [lvpelt2015@gmail.com] []
//        key = Daisy Duck, value = Daisy Duck: [DDuck@funplace.com] []
//        key = Minnie Mouse, value = Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
//        key = Maid Marion, value = Maid Marion: [] [(123) 456-7890]
//        key = Daffy Duck, value = Daffy Duck: [daffy@google.com, DDuck@funplace.com] []
//        key = Robin Hood, value = Robin Hood: [rhood@gmail.Com] [(789) 902-8222, (564) 789-3000]
//        key = Charlie Brown, value = Charlie Brown: [] [(333) 444-5555]
//        key = Scrooge McDuck, value = Scrooge McDuck: [SMcDuck@funplace.com] []
//        key = Mickey Mouse, value = Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

        promptSeparator();
        contacts.replaceAll((k, v) -> {
            String newEmail = k.replaceAll(" ", "") + "@fundplace.com";
            v.replaceEmailIfExists("DDuck@funplace.com", newEmail);
            return v;
        });
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
//        key = Linus Van Pelt, value = Linus Van Pelt: [lvpelt2015@gmail.com] []
//        key = Daisy Duck, value = Daisy Duck: [DaisyDuck@fundplace.com] []
//        key = Minnie Mouse, value = Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
//        key = Maid Marion, value = Maid Marion: [] [(123) 456-7890]
//        key = Daffy Duck, value = Daffy Duck: [daffy@google.com, DaffyDuck@fundplace.com] []
//        key = Robin Hood, value = Robin Hood: [rhood@gmail.Com] [(789) 902-8222, (564) 789-3000]
//        key = Charlie Brown, value = Charlie Brown: [] [(333) 444-5555]
//        key = Scrooge McDuck, value = Scrooge McDuck: [SMcDuck@funplace.com] []
//        key = Mickey Mouse, value = Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

        promptSeparator();
        Contact daisy = new Contact("Daisy Jane Duck", "daisyj@duck.com");
        Contact replacedContact = contacts.replace("Daisy Duck", daisy);
        System.out.println("daisy = " + daisy);
        System.out.println("replacedContact = " + replacedContact);
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
//        daisy = Daisy Jane Duck: [daisyj@duck.com] []
//        replacedContact = Daisy Duck: [DaisyDuck@fundplace.com] []
//        key = Linus Van Pelt, value = Linus Van Pelt: [lvpelt2015@gmail.com] []
//        key = Daisy Duck, value = Daisy Jane Duck: [daisyj@duck.com] []
//        key = Minnie Mouse, value = Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
//        key = Maid Marion, value = Maid Marion: [] [(123) 456-7890]
//        key = Daffy Duck, value = Daffy Duck: [daffy@google.com, DaffyDuck@fundplace.com] []
//        key = Robin Hood, value = Robin Hood: [rhood@gmail.Com] [(789) 902-8222, (564) 789-3000]
//        key = Charlie Brown, value = Charlie Brown: [] [(333) 444-5555]
//        key = Scrooge McDuck, value = Scrooge McDuck: [SMcDuck@funplace.com] []
//        key = Mickey Mouse, value = Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

        promptSeparator();
        Contact updatedDaisy = replacedContact.mergeContactData(daisy);
        System.out.println("updatedDaisy = " + updatedDaisy);
        boolean success = contacts.replace("Daisy Duck", daisy, updatedDaisy);
        if (success) {
            System.out.println("Successfully replaced element");
        } else {
            System.out.printf("Did not match on both key: %s and value: %s %n".formatted("Daisy Duck", replacedContact));
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
//        updatedDaisy = Daisy Duck: [daisyj@duck.com, DaisyDuck@fundplace.com] []
//        Did not match on both key: Daisy Duck and value: Daisy Duck: [DaisyDuck@fundplace.com] []
//        key = Linus Van Pelt, value = Linus Van Pelt: [lvpelt2015@gmail.com] []
//        key = Daisy Duck, value = Daisy Jane Duck: [daisyj@duck.com] []
//        key = Minnie Mouse, value = Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
//        key = Maid Marion, value = Maid Marion: [] [(123) 456-7890]
//        key = Daffy Duck, value = Daffy Duck: [daffy@google.com, DaffyDuck@fundplace.com] []
//        key = Robin Hood, value = Robin Hood: [rhood@gmail.Com] [(789) 902-8222, (564) 789-3000]
//        key = Charlie Brown, value = Charlie Brown: [] [(333) 444-5555]
//        key = Scrooge McDuck, value = Scrooge McDuck: [SMcDuck@funplace.com] []
//        key = Mickey Mouse, value = Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

        success = contacts.replace("Daisy Duck", daisy, updatedDaisy);
        if (success) {
            System.out.println("Successfully replaced element");
        } else {
            System.out.printf("Did not match on both key: %s and value: %s %n".formatted("Daisy Duck", replacedContact));
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
//        updatedDaisy = Daisy Duck: [daisyj@duck.com, DaisyDuck@fundplace.com] []
//        Successfully replaced element
//        key = Linus Van Pelt, value = Linus Van Pelt: [lvpelt2015@gmail.com] []
//        key = Daisy Duck, value = Daisy Duck: [daisyj@duck.com, DaisyDuck@fundplace.com] []
//        key = Minnie Mouse, value = Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
//        key = Maid Marion, value = Maid Marion: [] [(123) 456-7890]
//        key = Daffy Duck, value = Daffy Duck: [daffy@google.com, DaffyDuck@fundplace.com] []
//        key = Robin Hood, value = Robin Hood: [rhood@gmail.Com] [(789) 902-8222, (564) 789-3000]
//        key = Charlie Brown, value = Charlie Brown: [] [(333) 444-5555]
//        key = Scrooge McDuck, value = Scrooge McDuck: [SMcDuck@funplace.com] []
//        key = Mickey Mouse, value = Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

        promptSeparator();
        success = contacts.remove("Daisy Duck", daisy);
        if (success) {
            System.out.println("Successfully removed element");
        } else {
            System.out.printf("Did not match on both key: %s and value: %s %n".formatted("Daisy Duck", daisy));
        }
        contacts.forEach(((k, v) -> System.out.println("key = " + k + ", value = " + v)));
//        Did not match on both key: Daisy Duck and value: Daisy Jane Duck: [daisyj@duck.com] []
//        key = Linus Van Pelt, value = Linus Van Pelt: [lvpelt2015@gmail.com] []
//        key = Daisy Duck, value = Daisy Duck: [daisyj@duck.com, DaisyDuck@fundplace.com] []
//        key = Minnie Mouse, value = Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
//        key = Maid Marion, value = Maid Marion: [] [(123) 456-7890]
//        key = Daffy Duck, value = Daffy Duck: [daffy@google.com, DaffyDuck@fundplace.com] []
//        key = Robin Hood, value = Robin Hood: [rhood@gmail.Com] [(789) 902-8222, (564) 789-3000]
//        key = Charlie Brown, value = Charlie Brown: [] [(333) 444-5555]
//        key = Scrooge McDuck, value = Scrooge McDuck: [SMcDuck@funplace.com] []
//        key = Mickey Mouse, value = Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
    }

    private static void promptSeparator() {
        System.out.println("-".repeat(20));
    }
}
