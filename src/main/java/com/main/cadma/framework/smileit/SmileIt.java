package com.main.cadma.framework.smileit;


import java.util.ArrayList;
import java.util.List;

import com.main.cadma.interfaces.EventComplete;
import com.main.cadma.interfaces.SmilesGuiInterface;
import com.main.smileit.views.SmileViewGenerator;
import com.main.smileit.domain.models.MoleculesList;
import com.main.smileit.framework.cdk.MoleculeDataFactory;
import com.main.smileit.framework.cdk.MoleculeGraphPainter;
import com.main.smileit.framework.cdk.VerifiedSmile;
import com.main.smileit.infrastructure.FirstSubstituent;
import com.main.smileit.interfaces.SmilesHInterface;

public class SmileIt implements SmilesGuiInterface {

    private final SmileViewGenerator principalView;
    private final List<EventComplete> events;
    private boolean generated;

    private List<SmilesHInterface> moleculesList;
    private List<SmilesHInterface> substitutes;
    private SmilesHInterface MoleculePrincipal;
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
        principalView.addGenerateEvent((String path, SmilesHInterface principal, List<SmilesHInterface> subs,
                List<SmilesHInterface> listSmileGenerated) -> {
            generated = true;
            this.pathPrincipal = path;
            this.MoleculePrincipal = principal;
            this.substitutes = subs;
            this.moleculesList = listSmileGenerated;
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
    public List<SmilesHInterface> getMoleculesList() {
        if (!generated) {
            throw new IllegalStateException("The smiles are not generated");
        }
        return moleculesList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SmilesHInterface> getSubstitutes() {
        if (!generated) {
            throw new IllegalStateException("The smiles are not generated");
        }
        return substitutes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SmilesHInterface getMoleculePrincipal() {
        if (!generated) {
            throw new IllegalStateException("The smiles are not generated");
        }
        return MoleculePrincipal;
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
