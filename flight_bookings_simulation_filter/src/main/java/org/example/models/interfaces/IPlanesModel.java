package org.example.models.interfaces;

import org.example.entities.Plane;

public interface IPlanesModel {

    Plane create(Plane basePlane);

    Plane findById(int planeIdToFind);

    boolean delete(int planeIdToRemove);

}
