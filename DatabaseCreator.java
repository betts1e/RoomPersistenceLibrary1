package com.example.betti.roompersistencelibrary;

/**
 * Created by Betti on 19.12.17.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DatabaseCreator {
    public static final String[] dob = new String[]{"1999-04-03", "1991-02-03", "2013-03-30"};

    private String[] firstName = new String[]{"Noah", "Emma", "Liam", "Olivia", "William", "Ava", "Mason", "Sophia", "James",
            "Isabella", "Benjamin", "Mia", "Jacob", "Charlotte", "Michael", "Abigail", "Elijah", "Emily", "Ethan", "Harper"};
    private String[] street = new String[]{
            "High Street", "Station Road", "Main Street", "Park Road", "Church Road", "Church Street", "London Road", "Victoria Road", "Green Lane",
            "The Avenue", "The Crescent", "Queens Road", "New Road", "Grange Road", "Kings Road", "Kingsway", "Windsor Road", "Highfield Road", "Mill Lane",
            "Alexander Road", "York Road", "St. John’s Road", "Manor Road", "Church Lane", "Park Avenue"};

    private String[] city = new String[]{
            "Ashland", "Aspen", "Atascadero", "Athens", "Atlanta", "Auburn", "Austin", "Ayer", "Babylon", "Bainbridge"
    };

    private String[] state = new String[]{
            "New Hampshire", "New Jersey", "New Mexico", "New York"};


    private String[] bookTitle = new String[]{
            "Two Year Eight Months and Twenty –Eight Night  –Salman Rushdi", "The Red Sari –Javier Moro",
            "Neither a Hawk nor a dove –Khurshid M Kasuari", "Faces and Places Professor –Deepak Nayyar", "Indian Parliamentary Diplomacy- Meira Kumar",
            "Farishta –Kapil Isapuari", "Super Economies –Raghav Bahal", "China :Confucius in the Shadow –Poonam Surie",
            "My country My Life ‐ L.K.Advani", "Joseph Anton ‐ Sulman Rushdie (Autobiography)",
            "The Sahara Testaments ‐ Tade Ipadeola", "Narendra Modi: A Political Biography ‐ Andy Marino",
            "My Unforgettable Memories ‐ Mamata Banerjee", "Rationalised Roman for Kashmiri ‐ Dr R L Bhat",
            "Strictly Personal, Manmohan and Gursharan ‐ Daman Singh", "The Wrong Enemy: America in Afghanistan, 2001‐ 2014 ‐ Carlotta Gall",
            "Lal Bahadur Shastri: Lessons in Leadership ‐ Pavan Choudary", "Walking With Giants ‐ G. Ramachandran(former Finance Secretary )",
            "Crusader or Conspirator? Coalgate and other Truths ‐ PC Parakh",
            "The Accidental Prime Minister: the making and unmaking of Manmohan Singh ‐ Sanjaya Baru",
            "God of Antarctica ‐ 13 year old Yashwardhan Shukla", "My Years with Rajiv and Sonia ‐ R.D.Pradhan",
            "Khushwantnama ‐The Lessons of My Life ‐ Khushwant singh",
            "Syntheism – Creating God in The Internet Age ‐ Alexander Bard", "One Life is Not Enough ‐ Natwar Singh",
            "The Lives of Others ‐ Neel Mukherjee", "My Music My Life ‐ Pt Ravi Shankar",
            "I am Malala ‐ Malala Yousufzai and Christina Lamb", "A Man and A Motorcycle, How Hamid Karzai Came to Power ‐ Bette Dam",
            "True Colours — Adam Gilchrist", "Assassination of Rajiv Gandhi: An Inside Job? ‐ Faraz Ahmad",

    };


    public List<User> getRandomUserList() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DOB_FORMAT);
        List<User> users = new ArrayList<>();
        User tempUser;
        Address address;
        int cityRange = city.length;
        int stateRange = state.length;
        int nameRange = firstName.length;
        int streetRange = street.length;
        int dobRange = dob.length;
        Random random = new Random();
        for (int i = 1; i <= 20; i++) {
            address = new Address();
            address.setState(state[random.nextInt(stateRange)]);
            address.setCity(city[random.nextInt(cityRange)]);
            address.setPostCode(String.valueOf(random.nextInt(99999)));
            address.setStreet(street[random.nextInt(streetRange)]);
            tempUser = new User();
            tempUser.userId = i;
            tempUser.address = address;
            tempUser.firstName = firstName[random.nextInt(nameRange)];
            tempUser.createDate = new Date();
            tempUser.dob = sdf.parse(dob[random.nextInt(dobRange)]);
            users.add(tempUser);
        }
        return users;
    }

    public List<Book> getRandomBookList() {
        Random random = new Random();
        int bookTitleRange = bookTitle.length;
        List<Book> books = new ArrayList<>();
        Book tempBook;
        for (int i = 0; i < 20; i++) {
            tempBook = new Book();
            tempBook.setUserId(random.nextInt(20) + 1);
            tempBook.setTitle(bookTitle[random.nextInt(bookTitleRange)]);
            books.add(tempBook);
        }
        return books;
    }

}
