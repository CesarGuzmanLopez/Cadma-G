package com.main.smileit.views.panels;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.main.smileit.views.About;

public class Menu extends JMenuBar {

    public Menu() {
        super();
        final JMenu menuFile = new JMenu("File");
        add(menuFile);
        final JMenu menuHelp = new JMenu("Help");
        add(menuHelp);

        final JMenuItem menuSaveImage = new JMenuItem("Save Image");
        menuFile.add(menuSaveImage);
        menuSaveImage.addActionListener(e -> savePrincipal());

        final JMenuItem about = new JMenuItem("About");
        menuHelp.add(about);
        about.addActionListener(e -> showAbout());

    }
    /** Show the about dialog. */

    private void showAbout() {
        About about = new About();
        about.setVisible(true);
    }
    /** Save the principal view. */
    private void savePrincipal() {
        //TODO: save the principal view.
    }

}
