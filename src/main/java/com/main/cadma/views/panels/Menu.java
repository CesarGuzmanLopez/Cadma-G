package com.main.cadma.views.panels;

import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.main.cadma.interfaces.ActionImportFile;
import com.main.smileit.views.About;

/**
 * This class is the menu of the application.
 *
 * @author Cesar G. Guzman Lopez
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("java:S1948")
public class Menu extends JMenuBar {
    private List<ActionImportFile> actions;

    public Menu() {
        super();
        actions = new java.util.ArrayList<>();
        final JMenu menuFile = new JMenu("File");
        add(menuFile);
        final JMenu menuHelp = new JMenu("Help");
        add(menuHelp);

        final JMenuItem menuSaveImage = new JMenuItem("Import Process");
        menuFile.add(menuSaveImage);
        menuSaveImage.addActionListener(e -> uploadPath());

        final JMenuItem about = new JMenuItem("About");
        menuHelp.add(about);

        about.addActionListener(e -> showAbout());

    }

    public void setActions(ActionImportFile actions) {
        this.actions.add(actions);
    }

    /** Show the about dialog. */

    private void showAbout() {
        About about = new About();
        about.setVisible(true);
    }

    /** select the path principal process. */
    private void uploadPath() {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            actions.forEach(action -> action.importFile(path));
        }

    }

}
