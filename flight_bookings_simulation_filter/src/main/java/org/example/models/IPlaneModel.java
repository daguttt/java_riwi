package org.example.models;

import org.example.entities.Plane;

public interface IPlaneModel {

    Plane create(Plane basePlane);

    boolean delete(int planeIdToRemove);

}
