package org.example.controllers;

import org.example.entities.Plane;
import org.example.models.interfaces.IPlanesModel;

import java.util.Optional;

public class
PlanesController {
    private final IPlanesModel planesModel;

    public PlanesController(IPlanesModel planesModel) {
        this.planesModel = planesModel;
    }

    public Plane create(Plane basePlane) {
        return this.planesModel.create(basePlane);
    }

    public Optional<Plane> findById(int planeIdToFind) {
        return this.planesModel.findById(planeIdToFind);
    }

    public boolean delete(int planeId) {
        return this.planesModel.delete(planeId);
    }
}
