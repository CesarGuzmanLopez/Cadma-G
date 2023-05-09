package com.main.shared.domain.smileit;

import java.util.ArrayList;
import java.util.List;

import com.main.cadma.interfaces.MoleculesGuiInterface;
import com.main.framework.cdk.VerifiedSmile;
import com.main.shared.domain.cadma.interfaces.EventComplete;
import com.main.smileit.views.SmileViewGenerator;
import com.main.smileit.domain.models.Molecule;
import com.main.smileit.domain.models.MoleculesList;
import com.main.smileit.framework.cdk.MoleculeDataFactory;
import com.main.smileit.framework.cdk.MoleculeGraphPainter;
import com.main.smileit.infrastructure.FirstSubstituent;


public class SmileIt implements MoleculesGuiInterface {

    private final SmileViewGenerator principalView;
    private final List<EventComplete> events;
    private boolean generated;

    private List<com.main.shared.domain.Molecule> moleculesList;
    private List<com.main.shared.domain.Molecule> substitutes;
    private com.main.shared.domain.Molecule moleculePrincipal;
    private String pathPrincipal;

    public SmileIt() {
        final MoleculeDataFactory moleculeFactory = new MoleculeDataFactory();
        final VerifiedSmile verifierSmile = new VerifiedSmile();
        final MoleculeGraphPainter moleculeGraphPainter = new MoleculeGraphPainter();
        final MoleculesList smiles = FirstSubstituent.getMoleculeListInitializer(verifierSmile, moleculeFactory);

        principalView = new SmileViewGenerator(smiles, verifierSmile, moleculeGraphPainter, moleculeFactory);
        principalView.initialize();
        principalView.dispose();
        generated = false;
        events = new ArrayList<>();
        principalView.addGenerateEvent(
                (String path, Molecule principal, List<Molecule> subs, List<Molecule> listSmileGenerated) -> {
                    generated = true;
                    this.pathPrincipal = path;
                    this.moleculePrincipal = new com.main.shared.domain.Molecule(principal.getName(),
                            principal.smile());
                    this.substitutes = new ArrayList<>();
                    for (final Molecule molecule : subs) {
                        this.substitutes.add(new com.main.shared.domain.Molecule(molecule.getName(),
                                molecule.smile()));
                    }
                    this.moleculesList = new ArrayList<>(listSmileGenerated.size());
                    for (final Molecule molecule : listSmileGenerated) {
                        this.moleculesList.add(new com.main.shared.domain.Molecule(principal.getName(),
                                molecule.smile()));
                    }
                    for (final EventComplete event : events) {
                        event.execute();
                    }
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showGenerate() {
        principalView.repaint();
        principalView.pack();
        principalView.setLocationByPlatform(true);
        principalView.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGenerateEvent(final EventComplete completeEvent) {
        if (principalView == null) {
            throw new IllegalStateException("The principal view is null");
        }
        events.add(completeEvent);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<com.main.shared.domain.Molecule> getMoleculesList() {
        if (!generated) {
            throw new IllegalStateException("The smiles are not generated");
        }
        return moleculesList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<com.main.shared.domain.Molecule> getSubstitutes() {
        if (!generated) {
            throw new IllegalStateException("The smiles are not generated");
        }
        return substitutes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public com.main.shared.domain.Molecule getMoleculePrincipal() {
        if (!generated) {
            throw new IllegalStateException("The smiles are not generated");
        }
        return moleculePrincipal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPathPrincipal() {
        if (!generated) {
            throw new IllegalStateException("The smiles are not generated");
        }
        return pathPrincipal;
    }
}
