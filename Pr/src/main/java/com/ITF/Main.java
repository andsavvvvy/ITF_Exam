package com.ITF;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String order;
        Scanner scan = new Scanner(System.in);


        while (true) {
            System.out.print("Introduceti comanda: ");
            order = scan.nextLine();
            if (order.equals("0")) {
                System.out.println("Terminat");
                break;
            }
            switch (order) {
                case "+": {
                    String Nume, Prenume, Email;
                    int Id, Varsta;

                    System.out.print("Introduceti Id-ul ");
                    Id = scan.nextInt();

                    System.out.print("Introduceti Numele ");
                    Nume = scan.next();

                    System.out.print("Introduceti Preumele ");
                    Prenume = scan.next();

                    System.out.print("Introduceti Varsta ");
                    Varsta = scan.nextInt();

                    System.out.print("Introduceti Email ");
                    Email = scan.next();

                    Person newMember = new Person(Id, Nume, Prenume, Varsta, Email);
                    PersonService.insertPrs(newMember);
                    break;
                }

                case "-": {
                    System.out.print("Id-ul clientului care a renuntat ");
                    int id = scan.nextInt();
                    PersonService.delete_act(id);
                    break;
                }

                case "~N": {
                    System.out.print("Id-ul clientului care si-a schimbat numele ");
                    int id = scan.nextInt();
                    System.out.print("Noul nume ");
                    String NewName = scan.next();
                    PersonService.updatePrsName(id, NewName);
                    break;
                }

                case "~E": {
                    System.out.print("Id-ul clientului care si-a schimbat email-ul ");
                    int id = scan.nextInt();
                    System.out.print("Noul email ");
                    String NewMail = scan.next();
                    PersonService.updatePrsMail(id, NewMail);
                    break;
                }

                case "?": {
                    System.out.print("Id-ul clientului ");
                    int id = scan.nextInt();
                    PersonService.showById(id);
                    break;
                }

                case "??": {
                    PersonService.showAllCostumers();
                    break;
                }
                default: {
                    System.out.println("Comanda Invalida");
                    break;
                }
            }

        }

    }


}
