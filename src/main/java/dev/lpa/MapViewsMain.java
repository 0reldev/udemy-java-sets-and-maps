package dev.lpa;

import java.util.*;

public class MapViewsMain {

    public static void main(String[] args) {

        Map<String, Contact> contacts = new HashMap<>();
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));

        Set<String> keysView = contacts.keySet();
        System.out.println(keysView);
//        [Linus Van Pelt, Minnie Mouse, Maid Marion, Charlie Brown, Robin Hood, Daffy Duck, Mickey Mouse]
//        → The list of all keys that are used in the Map can be got by the keySet() method.

        Set<String> copyOfKeys = new TreeSet<>(contacts.keySet());
        System.out.println(copyOfKeys);
//        [Charlie Brown, Daffy Duck, Linus Van Pelt, Maid Marion, Mickey Mouse, Minnie Mouse, Robin Hood]
//        → The keys in the TreeSet are in alphabetical order.

        if (contacts.containsKey("Linus Van Pelt")) {
            System.out.println("Linus and I go way back, so of course I have info");
        }
//        Linus and I go way back, so of course I have info

        keysView.remove("Daffy Duck");
        System.out.println(keysView);
//        [Linus Van Pelt, Minnie Mouse, Maid Marion, Charlie Brown, Robin Hood, Mickey Mouse]
//        → The key "Daffy Duck" has been removed from the Set.

        contacts.forEach((k, v) -> System.out.println(v));
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Minnie Mouse: [minnie@verizon.net] []
//        Maid Marion: [] [(123) 456-7890]
//        Charlie Brown: [] [(333) 444-5555]
//        Robin Hood: [rhood@gmail.Com] []
//        Mickey Mouse: [micky1@aws.com] []
//        → Then, the "Daffy Duck" Map.Entry doesn't exist anymore as the key has been removed.

        copyOfKeys.remove("Linus Van Pelt");
        System.out.println(copyOfKeys);
//        [Charlie Brown, Daffy Duck, Maid Marion, Mickey Mouse, Minnie Mouse, Robin Hood]
//        "Linus Van Pelt" key has been removed from the copyOyKeys.

        contacts.forEach((k, v) -> System.out.println(v));
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Minnie Mouse: [minnie@verizon.net] []
//        Maid Marion: [] [(123) 456-7890]
//        Charlie Brown: [] [(333) 444-5555]
//        Robin Hood: [rhood@gmail.Com] []
//        Mickey Mouse: [micky1@aws.com] []
//        → But this Map.Entry still exists in the original Map.

        keysView.retainAll(List.of("Linus Van Pelt", "Charlie Brown", "Robin Hood", "Mickey Mouse"));
        System.out.println(keysView);
//        [Linus Van Pelt, Charlie Brown, Robin Hood, Mickey Mouse]
        contacts.forEach((k, v) -> System.out.println(v));
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Charlie Brown: [] [(333) 444-5555]
//        Robin Hood: [rhood@gmail.Com] []
//        Mickey Mouse: [micky1@aws.com] []

        keysView.clear();
        System.out.println(contacts);
//        {}
//        → By removing all the keys, the map becomes empty.

//        keysView.add("Daffy Duck");
//        System.out.println(contacts);
//        Exception in thread "main" java.lang.UnsupportedOperationException
//        at java.base/java.util.AbstractCollection.add(AbstractCollection.java:253)
//        at dev.lpa.MapViewsMain.main(MapViewsMain.java:70)
//        → It isn't possible to add key in the key Set with a traditional add method.

        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        System.out.println(keysView);
//        [Linus Van Pelt, Minnie Mouse, Maid Marion, Robin Hood, Daffy Duck, Charlie Brown, Mickey Mouse]

        var values = contacts.values();
        values.forEach(System.out::println);
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Minnie Mouse: [] [(456) 780-5666]
//        Maid Marion: [] [(123) 456-7890]
//        Robin Hood: [] [(789) 902-8222]
//        Daffy Duck: [daffy@google.com] []
//        Charlie Brown: [] [(333) 444-5555]
//        Mickey Mouse: [] [(999) 888-7777]
//        → All values from a Map can be got with the values() method.

        values.retainAll(ContactData.getData("email"));
        System.out.println(keysView);
//        [Linus Van Pelt, Minnie Mouse, Robin Hood, Daffy Duck, Mickey Mouse]
        contacts.forEach((k, v) -> System.out.println(v));
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Minnie Mouse: [] [(456) 780-5666]
//        Robin Hood: [] [(789) 902-8222]
//        Daffy Duck: [daffy@google.com] []
//        Mickey Mouse: [] [(999) 888-7777]

        System.out.println("---");
        List<Contact> list = new ArrayList<>(values);
        list.sort(Comparator.comparing(Contact::getNameLastFirst));
        list.forEach(c -> System.out.println(c.getNameLastFirst() + ": " + c));
//        Duck, Daffy: Daffy Duck: [daffy@google.com] []
//        Hood, Robin: Robin Hood: [] [(789) 902-8222]
//        Mouse, Mickey: Mickey Mouse: [] [(999) 888-7777]
//        Mouse, Minnie: Minnie Mouse: [] [(456) 780-5666]
//        Van Pelt, Linus: Linus Van Pelt: [lvpelt2015@gmail.com] []

        System.out.println("---");
        Contact first = list.get(0);
        contacts.put(first.getNameLastFirst(), first);
        values.forEach(System.out::println);
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Daffy Duck: [daffy@google.com] []
//        Minnie Mouse: [] [(456) 780-5666]
//        Robin Hood: [] [(789) 902-8222]
//        Daffy Duck: [daffy@google.com] []
//        Mickey Mouse: [] [(999) 888-7777]
        keysView.forEach(System.out::println);
//        Linus Van Pelt
//        Duck, Daffy
//        Minnie Mouse
//        Robin Hood
//        Daffy Duck
//        Mickey Mouse
//        → There ara two different entries for Daffy Duck, but as the key is different the values are duplicated.

        System.out.println("--");
        HashSet<Contact> set = new HashSet<>(values);
        set.forEach(System.out::println);
//        Linus Van Pelt: [lvpelt2015@gmail.com] []
//        Robin Hood: [] [(789) 902-8222]
//        Mickey Mouse: [] [(999) 888-7777]
//        Daffy Duck: [daffy@google.com] []
//        Minnie Mouse: [] [(456) 780-5666]
        if (set.size() < contacts.keySet().size()) {
            System.out.println("Duplicate values are in my Map");
        }
//        Duplicate values are in my Map

        System.out.println("---");
        var nodeSet = contacts.entrySet();
        for (var node : nodeSet) {
            System.out.println(nodeSet.getClass().getName());
            if (!node.getKey().equals(node.getValue().getName())) {
                System.out.println(node.getClass().getName());
                System.out.println("Key doesn't match name: " + node.getKey() + ": " + node.getValue());
            }
        }
//            java.util.HashMap$EntrySet
//            java.util.HashMap$EntrySet
//            java.util.HashMap$Node
//            Key doesn't match name: Duck, Daffy: Daffy Duck: [daffy@google.com] []
//            java.util.HashMap$EntrySet
//            java.util.HashMap$EntrySet
//            java.util.HashMap$EntrySet
//            java.util.HashMap$EntrySet
    }
}
