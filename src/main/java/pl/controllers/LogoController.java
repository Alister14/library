package pl.controllers;

import javafx.scene.image.Image;

public class LogoController {
    private static final Image logo = new Image(LogoController.class.getResource("/logo/CUDA LOGO.png").toString());

    public static Image getLogoImage() {
        return logo;
    }

}
