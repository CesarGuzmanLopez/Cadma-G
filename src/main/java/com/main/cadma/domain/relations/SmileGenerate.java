package com.main.cadma.domain.relations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.main.cadma.domain.models.AttributeAbstract;
import com.main.cadma.domain.models.smileit.GenerateSmiles;
import com.main.cadma.domain.models.smileit.MoleculePrincipal;
import com.main.cadma.domain.models.smileit.Substitutes;
import com.main.cadma.interfaces.ActionsCadma;
import com.main.cadma.interfaces.EventComplete;
import com.main.cadma.interfaces.MoleculesGuiInterface;
import com.main.cadma.interfaces.SaveImagesInterface;
import com.main.cadma.interfaces.SmilesUploadInterface;
import com.main.cadma.interfaces.StatusProcess;
import com.main.cadma.views.ViewSmileIt;
import com.main.shared.domain.Molecule;

public class SmileGenerate implements ActionsCadma {
    public static final String FILE_SMILES = "output.txt";
    public static final String FILE_INFO = "CadmaInfo.txt";
    public static final String FILE_JSON = "CadmaJson.json";
    private MoleculesGuiInterface smilesGui;
    private SmilesUploadInterface smilesUpload;
    private MoleculePrincipal smilePrincipal;
    private Substitutes substitutes;
    private GenerateSmiles generateSmiles;
    private String parentPath;
    private String principalName;
    private List<EventComplete> importProcessEvent;
    private StatusProcess statusProcess;
    private ViewSmileIt viewSmileIt;
    private SaveImagesInterface saveImage;

    public SmileGenerate(final MoleculesGuiInterface smilesGui, final SmilesUploadInterface smilesUpload,
            ViewSmileIt viewSmileIt, SaveImagesInterface saveImage) {
        this.smilesGui = smilesGui;
        this.smilesUpload = smilesUpload;
        this.importProcessEvent = new ArrayList<>();
        this.viewSmileIt = viewSmileIt;

        this.smilesUpload.addUploadEvent(this::uploadSmiles);
        this.smilesGui.addGenerateEvent(this::definedGenerated);

        this.smilesGui.addGenerateEvent(this::generateCadmaInfo);
        this.smilesUpload.addUploadEvent(this::generateCadmaInfo);

        statusProcess = StatusProcess.EMPTY;
        this.saveImage = saveImage;
    }

    /**
     * importProcessEvent
     */
    private void generateCadmaInfo() {
        if (smilePrincipal == null || generateSmiles == null || substitutes == null) {
            throw new IllegalArgumentException("SmilePrincipal, GenerateSmiles or Substitutes not defined");
        }
        try (FileWriter myWriter = new FileWriter(parentPath + System.getProperty("file.separator") + FILE_INFO)) {
            myWriter.write(smilePrincipal.toString());
            myWriter.write("=== Substitutes ===\n");
            myWriter.write(substitutes.toString());
            myWriter.write("=== GenerateSmiles ===\n");
            myWriter.write(generateSmiles.toString());
        } catch (IOException e) {
            statusProcess = StatusProcess.ERROR;
            throw new IllegalArgumentException("Error writing file" + e.getMessage());
        }
    }

    /**
     * @return parent path
     */
    public String getParentPath() {
        if (parentPath == null || parentPath.isEmpty()) {
            throw new NullPointerException("Parent path is null");
        }
        return parentPath;
    }

    @Override
    public void upload() {
        smilesUpload.showUpload();

    }

    public StatusProcess getStatusProcess() {
        return statusProcess;
    }

    @Override
    public void importCadmaProcess(final String path) {

        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Path is null");
        }
        parentPath = path;
        File directory = new File(parentPath);
        if (!directory.exists()) {
            statusProcess = StatusProcess.ERROR;
            throw new IllegalArgumentException("Path not exists");
        }
        File fileInfo = new File(parentPath + System.getProperty("file.separator") + FILE_INFO);
        if (!fileInfo.exists()) {
            statusProcess = StatusProcess.ERROR;
            throw new IllegalArgumentException(FILE_INFO + ": File  not exists");
        }
        File fileSmiles = new File(parentPath + System.getProperty("file.separator") + FILE_SMILES);
        if (!fileSmiles.exists()) {
            statusProcess = StatusProcess.ERROR;
            throw new IllegalArgumentException(FILE_SMILES + ": File not exists");
        }

        try (FileReader lectorInfo = new FileReader(fileInfo);
                FileReader lectorSmiles = new FileReader(fileSmiles);
                BufferedReader lector1 = new BufferedReader(lectorInfo);
                BufferedReader lector2 = new BufferedReader(lectorSmiles);

        ) {
            String line;
            smilePrincipal = new MoleculePrincipal();
            substitutes = new Substitutes();
            while ((line = lector1.readLine()) != null) {
                smilePrincipal.lineAnalyze(line);
                substitutes.lineAnalyze(line);
            }
            generateSmiles = new GenerateSmiles(smilePrincipal.getValue().getName());
            while ((line = lector2.readLine()) != null) {
                generateSmiles.lineAnalyze(line);
            }
            generateSmiles.found();
        } catch (IOException e) {
            statusProcess = StatusProcess.ERROR;
            throw new IllegalArgumentException("Error reading file" + e.getMessage());

        }
        statusProcess = StatusProcess.FINISH;

        if (substitutes.getValue().isEmpty()) {
            statusProcess = StatusProcess.INCOMPLETE;
        }
        principalName = smilePrincipal.getValue().getName();
        for (EventComplete event : importProcessEvent) {
            event.execute();
        }
    }

    private void uploadSmiles() {
        principalName = smilesUpload.getNamePrincipalMolecule();
        parentPath = smilesUpload.getPathPrincipal() + System.getProperty("file.separator") + principalName;

        String fileToUploadable = smilesUpload.getFileToUpload();
        File directory = new File(parentPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        generateSmiles = new GenerateSmiles(principalName);
        try (FileWriter myWriter = new FileWriter(parentPath + System.getProperty("file.separator") + FILE_SMILES)) {
            File uploadFile = new File(fileToUploadable);
            if (!uploadFile.exists()) {
                throw new NullPointerException("File to upload is null");
            }
            List<String> lines = Files.readAllLines(uploadFile.toPath());
            for (String line : lines) {
                generateSmiles.lineAnalyze(line);
            }
            if (generateSmiles.getValue().isEmpty())
                throw new NullPointerException("No smiles found");
            if(saveImage!=null) {
                saveImage.saveImage(principalName, directory.getAbsolutePath()
                + System.getProperty("file.separator") + "Structures-png" + System.getProperty("file.separator"),generateSmiles.getValue());
            }

            generateSmiles.found();
            for (Molecule smile : generateSmiles.getValue()) {
                myWriter.write(smile.getSmile() + "\n");
            }
        } catch (IOException e) {
            statusProcess = StatusProcess.ERROR;
            throw new UnsupportedOperationException("Error to read file: " + e.getMessage());
        }

        substitutes = new Substitutes();
        smilePrincipal = new MoleculePrincipal(
                new Molecule(principalName, generateSmiles.getValue().get(0).getSmile()));
        statusProcess = StatusProcess.INCOMPLETE;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generate() {
        smilesGui.showGenerate();

    }

    private void definedGenerated() {
        try {
            smilePrincipal = new MoleculePrincipal(smilesGui.getMoleculePrincipal());
            substitutes = new Substitutes(smilesGui.getSubstitutes());
            generateSmiles = new GenerateSmiles(smilesGui.getMoleculesList(),
                    smilesGui.getMoleculePrincipal().getName());
            parentPath = smilesGui.getPathPrincipal();
            principalName = smilesGui.getMoleculePrincipal().getName();
        } catch (Exception e) {// NOSONAR
            statusProcess = StatusProcess.ERROR;
            throw new IllegalArgumentException("Error to generate smiles: " + e.getMessage());
        }
        statusProcess = StatusProcess.FINISH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void view() {
        if (statusProcess == StatusProcess.EMPTY || statusProcess == StatusProcess.ERROR
                || generateSmiles.getValue().isEmpty()) {
            throw new IllegalArgumentException("Please generate or import smiles");
        }
        if (principalName == null || principalName.isEmpty()) {
            throw new IllegalArgumentException("Principal name is null");
        }
        String[][] smilesGenerated = new String[generateSmiles.getValue().size()][2];
        for (int i = 0; i < generateSmiles.getValue().size(); i++) {
            smilesGenerated[i][1] = parentPath + System.getProperty("file.separator") + "Structures-png"
                    + System.getProperty("file.separator") + principalName + "_" + i + ".png";
            smilesGenerated[i][0] = generateSmiles.getValue().get(i).getSmile();
        }
        String[] label = { "Smiles" };
        viewSmileIt.setInfo(smilesGenerated, label);
        viewSmileIt.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObtainEvent(final EventComplete completeEvent) {
        smilesGui.addGenerateEvent(completeEvent);
        smilesUpload.addUploadEvent(completeEvent);
        importProcessEvent.add(completeEvent);
    }

    public MoleculePrincipal getSmilePrincipal() {
        return smilePrincipal;
    }

    public Substitutes getSubstitutes() {
        return substitutes;
    }

    public GenerateSmiles getGenerateSmiles() {
        return generateSmiles;
    }
}
