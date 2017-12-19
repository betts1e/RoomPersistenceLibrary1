package com.example.betti.roompersistencelibrary;

/**
 * Created by Betti on 19.12.17.
 */

public class UserBooks extends User{

    int borrowe;

    @Override
    public boolean equals(Object obj) {
        UserBooks userBooks = (UserBooks) obj;
        return userBooks.firstName.equals(this.firstName) && userBooks.borrowe == this.borrowe && userBooks.address.equals(this.address);

    }

}
