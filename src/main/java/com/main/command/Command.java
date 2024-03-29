package com.main.command;

import java.util.logging.Logger;

import com.main.interfaces.SmileVerificationInterface;
import com.main.smileit.domain.models.Molecule;
import com.main.smileit.interfaces.MoleculeDataFactoryInterface;

import java.util.ArrayList;

import java.util.List;

public abstract class Command {
    public static final Logger LOGGER = Logger.getLogger("Command Smile Generator");
    private List<String> args;
    private SmileVerificationInterface smileVerification;
    private MoleculeDataFactoryInterface moleculeFactory;
    private String name;
    private String smile;
    private Molecule molecule;
    private boolean hydrogensImplicit;

    protected Command(final SmileVerificationInterface verification, final MoleculeDataFactoryInterface factory) {
        this.args = new ArrayList<>();
        this.smileVerification = verification;
        this.moleculeFactory = factory;
        smile = null;
        name = null;
        hydrogensImplicit = true;
    }

    /**
     * @return molecule.
     */

    public Molecule molecule() {
        return molecule;
    }

    /**
     * @return is hydrogens implicit?
     *
     */
    public boolean isHydrogensImplicit() {
        return hydrogensImplicit;
    }

    /**
     * insert the arguments of the command.
     *
     * @param arg the arguments of the command.
     */
    public void setCommands(final String arg) {
        this.args.add(arg);
    }

    private void obtainName() {
        name = getArg("-name");
        if (name == null) {
            name = getArg("-n");
        }

    }

    private void obtainMolecule() {
        if (smiVerify(args.get(1))) {
            smile = args.get(1);
        } else {
            smile = getArg("-smile");
            if (!smiVerify(smile)) {
                return;
            }
        }
        if (name == null) {
            name = smile;
        }
        molecule = new Molecule(name, smile, "generated image", hydrogensImplicit, smileVerification, moleculeFactory);

    }

    /**
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * execute the command.
     */
    public void execute() {
        obtainName();
        obtainMolecule();

    }

    /**
     *
     * view implicit hydrogens.
     */
    public void viewHydrogensImplicit() {
        hydrogensImplicit = getArg("-explicit") == null;
    }

    /**
     * @param smileV
     * @return if the smile is correct
     */
    protected boolean smiVerify(final String smileV) {
        return smileVerification.isValid(smileV);
    }

    /**
     * @return the commands.
     */

    protected List<String> getArgs() {
        return args;
    }

    /**
     * @param stringSearch
     * @return parameters of the command.
     */
    protected String getArg(final String stringSearch) {
        String search = stringSearch.toLowerCase();
        for (int i = 0; i < args.size(); i++) {
            String lowerCase = args.get(i).toLowerCase();
            if (lowerCase.equals(search)) {
                return args.get(i + 1);
            }
        }
        return null;
    }

}
