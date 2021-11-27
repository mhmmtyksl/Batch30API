package com.techproed.pojos;

public class BookingDatesPojo {

    // "checkin": "2020-09-09",
    // "checkout": "2020-09-21"

    // 1) private degiskenler olusturduk
    private String checkin;
    private String checkout;

    // 2) getter ve setter olusturalim


    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // 3) parametreli ve parametresiz costructorlar olusturuyoruz

    public BookingDatesPojo() {
    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // toString methodu olusturalim

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
