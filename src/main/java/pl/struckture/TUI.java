package pl.struckture;

public class TUI {

//    public void textBaserUser(ArrayList<Book> books,
//                              ArrayList<Borrows> borrowBooks, ArrayList<Author> author, ArrayList<Client> clients) {
//        int choose;
//        System.out.println("Witamy w bibliotece, co chcesz zrobic");
//        System.out.println("1 Zobaczyc liste wszytkich ksiazek");
//        System.out.println("2 Zobaczyc liste wszytkich dostępnych ksiazek");
//        System.out.println("3 Zobaczyc liste wszytkich wypozyczonych ksiazek");
//        System.out.println("4 Wypozyczyc ksiazke");
//        System.out.println("5 Zarejestrowac klienta");
//        System.out.println("6 Oddac ksiazke");
//        System.out.println("7 Przedluzyc wypozyczenia");
//        System.out.println("8 Zobaczyc liste wszytkich autorow");
//        System.out.println("9 Zobaczyc liste wszytkich klientow");
//        Scanner scan = new Scanner(System.in);
//        choose = scan.nextInt();
//        System.out.println();
//        {
//            switch (choose) {
//                case 1: {
//                    for (Book value : books) { //Prosta pętla for pokazujaca liste wszytkich ksiazek
//                        System.out.println(value);
//                    }
//                    break;
//                }
//                case 2: {
//                }
//                case 3: {
//                    for (Borrows value : borrowBooks) {
//                        System.out.println(value);//Prosta pętla for pokazujaca liste wszytkich wypozyczonych ksiazek
//                    }
//                    break;
//
//                }
//                case 4: {
//                    int chooserBooks;
//                    int chooserClient;
//                    System.out.println("Oto lista wszytkich dostepnych ksiazek, ktory numer chcesz wypozyczyc");
//                    for (int i = 0; i < books.size(); i++) { // Ta pętla pokazuje wszytkie dostępne książki
//                        if (books.get(i).isAvailable()) {
//                            System.out.println((i + 1) + " " + books.get(i));
//                        }
//                    }
//                    chooserBooks = scan.nextInt() - 1;
//                    System.out.println();
//                    System.out.println("Oto lista wszytkich klientow, ktory chce dokonac wypozyczenia");
//                    for (int i = 0; i < clients.size(); i++) {// Ta pętla pokazuje wszytkich klientow
//                        System.out.println((i + 1) + " " + clients.get(i));
//                    }
//                    chooserClient = scan.nextInt() - 1;
//                    System.out.println();
//                    borrowBooks.add(borrowing(books.get(chooserBooks), clients.get(chooserClient)));//Ta metoda wypozycza ksiazke ]
//                    // tworzac nowy obiekt który łączy klienta z ksiazką i czasem jej wypozyczenia i
//                    // umieszczając to wszytko w arayliscie BorrowsBokks
//                    break;
//                }
//                case 5: {
//                    System.out.println("Witamy w kreatorze nowego klienta");
//                    AddClient addClient = new AddClient();
//                   // clients.add(addClient.addClientMethod(clients));//Tutaj swtorzyłem obiket addclient tylko po o by
//                    //móc użyć jego metody addClientMethod która z pozycji konsoli pozwala tworzyc nowego klienta
//                    System.out.println("Oto lista wszytkich klientow");
//                    System.out.println(clients);
//                    break;
//                }
//                case 6: {
//                    System.out.println("Witamy w centrum zwrotu ksiazki");
//                    System.out.println("Oto lista wszytkich wypozyczonych ksiazek, ktora z nich chcesz zwrocic");
//                    System.out.println(" ");
//                    int chooserBooks;
//                    for (int i = 0; i < borrowBooks.size(); i++) { // Pętla pokazująca wszytkie wypozyczone ksiazki
//                        System.out.println((i + 1) + ")" + borrowBooks.get(i));
//                        System.out.println();
//                    }
//                    chooserBooks = scan.nextInt() - 1;
//                    if (borrowBooks.size() > 0) {
//                        ReturnigBooks returnigBooks=new ReturnigBooks();
//                        returnigBooks.returnBooks(borrowBooks.get(chooserBooks));
//                        //Tutaj tak samo swtorzyłem obiekt tylko po to by użyć jego metody
//                        //Która ustawia dostępnośc ksiązki na true i jednocześnie pokazuję ile czasu pozostało do oddania książki
//                        borrowBooks.remove(chooserBooks);//tuta usuwam oddaną ksiązkie z araylisty
//                    } else {
//                        System.out.println("Nie ma takiej pozycji");
//                        System.out.println();
//                    }
//                    break;
//                }
//                case 7: {
//                    System.out.println("Witamy w centrum przedluzenia wypozyczenia ksiazki");
//                    System.out.println("Oto lista wszytkich wypozyczonych ksiazek, wybierz ta ktora chcesz przedluzyc");
//                    System.out.println();
//                    int chooserBooks;
//                    for (int i = 0; i < borrowBooks.size(); i++) {
//                        System.out.println((i + 1) + ")" + borrowBooks.get(i));
//                        System.out.println();
//                    }
//                    chooserBooks = scan.nextInt() - 1;
//                    if (borrowBooks.size() > 0) {
//                        borrowBooks.get(chooserBooks).getDataBorrow().addTimeToDelivery();
//                        System.out.println(borrowBooks.get(chooserBooks));
//                    } else {
//                        System.out.println("Nie ma takiej pozycji");
//                        System.out.println();
//                    }
//                    break;
//                }
//                case 8: {
//                    System.out.println("Oto lista wszytkich autorow");
//                    for (int i = 0; i < author.size(); i++)
//                        System.out.println((i + 1) + ") " + author.get(i));
//                    break;
//                }
//                case 9: {
//                    System.out.println("Oto lista wszytkich klientow");
//                    for (int i = 0; i < clients.size(); i++)
//                        System.out.println((i + 1) + ") " + clients.get(i));
//                    break;
//                }
//                default:
//                    break;
//            }
//        }
//    }
}
