package org.example.controllers;

import org.example.models.interfaces.IBookingsModel;

public class BookingsController {
    private final IBookingsModel bookingsModel;

    public BookingsController(IBookingsModel bookingsModel) {
        this.bookingsModel = bookingsModel;
    }



}
