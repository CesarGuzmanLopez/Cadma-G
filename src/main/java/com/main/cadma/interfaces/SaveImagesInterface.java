package com.main.cadma.interfaces;

import java.util.List;

import com.main.shared.domain.Molecule;

public interface SaveImagesInterface {
    void saveImage(final String principalName, final String saveImagesPath,final List<Molecule> listOfSmiles);
}
