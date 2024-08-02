package org.example.models.interfaces;

import org.example.entities.Plane;

import java.util.Optional;

public interface IPlanesModel {

    Plane create(Plane basePlane);

    Optional<Plane> findById(int planeIdToFind);

    boolean delete(int planeIdToDelete);

}
