package org.example.employeedb;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Photos {
    @FXML
    private ImageView imageViewExample;

    @FXML
    private void initialize() {
        Image image = new Image(getClass().getResourceAsStream("https://externlabs.com/blogs/wp-content/uploads/2022/02/erp.png"));
        imageViewExample.setImage(image);
        Image image2 = new Image(getClass().getResourceAsStream("https://www.timechamp.io/blogs/wp-content/uploads/2023/12/employee-mangement-systems.webp"));
        imageViewExample.setImage(image2);
    }
}
