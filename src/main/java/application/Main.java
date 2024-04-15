package application;

import javafx.geometry.*;
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
//db
import db.db;

public class Main extends Application {

    private ArrayList<ProductCard> productCardList = new ArrayList<>();
    private Button addProductBtn; // Declare addProductBtn variable at class level
  

    @Override
    public void start(Stage primaryStage) {
        try {
            GridPane root = new GridPane();
            root.setHgap(10);
            root.setVgap(10);
            Scene scene = new Scene(root, 400, 400);

            // Create some default product cards
            productCardList.add(new ProductCard(String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000)), "Rolex", "green", 100, "watch", 50, 75));
            productCardList.add(new ProductCard(String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000)), "Rolex", "green", 130, "watch", 50, 60));
            productCardList.add(new ProductCard(String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000)), "Rolex", "green", 150, "watch", 50, 30));

            // Create the "Add product" button
            addProductBtn = new AddProductButton(); // Initialize addProductBtn here
            addProductBtn.setOnAction(event -> showAddProductDialog("Add", 0));

            primaryStage.setScene(scene);
            primaryStage.setTitle("GESTION DE STOCK");
            primaryStage.show();

            displayProducts(root);

            scene.widthProperty().addListener((observable, oldValue, newValue) -> displayProducts(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAddProductDialog(String a, int index) {
        // Create a new dialog box
        Alert addProductDialog = new Alert(Alert.AlertType.NONE);
        addProductDialog.setTitle(a + " Product");

        // Add inputs for product name, category, selling price, quantity, description, and wholesale price
        TextField productNameField = new TextField();
        TextField categoryField = new TextField();
        TextField sellingPriceField = new TextField();
        TextField quantityField = new TextField();
        TextField descriptionField = new TextField();
        TextField wholeSalePriceField = new TextField();

        VBox content = new VBox();
        content.getChildren().addAll(
                new javafx.scene.control.Label("Product Name:"), productNameField,
                new javafx.scene.control.Label("Category:"), categoryField,
                new javafx.scene.control.Label("Selling Price:"), sellingPriceField,
                new javafx.scene.control.Label("Quantity:"), quantityField,
                new javafx.scene.control.Label("Description:"), descriptionField,
                new javafx.scene.control.Label("Wholesale Price:"), wholeSalePriceField
        );

        addProductDialog.getDialogPane().setContent(content);

        // Add confirmation and cancel buttons
        addProductDialog.getButtonTypes().clear();
        addProductDialog.getButtonTypes().addAll(javafx.scene.control.ButtonType.OK, javafx.scene.control.ButtonType.CANCEL);

        // Show the dialog box and wait for user choice
        addProductDialog.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                String name = productNameField.getText();
                String category = categoryField.getText();
                String price = sellingPriceField.getText();
                String wholeSalePrice = wholeSalePriceField.getText();
                String description = descriptionField.getText();
                String quantity = quantityField.getText();

                if (a.equals("Add")) {
                    productCardList.add(new ProductCard(
                            String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000)),
                            name,
                            description,
                            Float.parseFloat(price),
                            category,
                            Integer.parseInt(quantity),
                            Float.parseFloat(wholeSalePrice)
                    ));
                } else if (a.equals("Edit")) {
                    productCardList.set(index, new ProductCard(
                            String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000)),
                            name,
                            description,
                            Float.parseFloat(price),
                            category,
                            Integer.parseInt(quantity),
                            Float.parseFloat(wholeSalePrice)
                    ));
                }

                // Redisplay the products
                displayProducts((GridPane) addProductBtn.getParent());
                
                // Retain the inputs and perform any necessary actions (e.g., adding them to the product list)
                System.out.println("Product Name: " + name);
                System.out.println("Category: " + category);
            }
        });
    }

private void displayProducts(GridPane root) {
    root.getChildren().clear();
    int row = 1;
    int column = 0;
    int a = (int) (root.getWidth() / 200);
    System.out.println(a);

    // Display the products based on the new list
    for (ProductCard productCard : productCardList) {
        productCard.getDeleteButton().setOnAction((event) -> {
            productCardList.remove(productCard);
            displayProducts(root);
        });
        productCard.getEditButton().setOnAction((event) -> {
            int index = productCardList.indexOf(productCard);
            showAddProductDialog("Edit", index);
            displayProducts(root);
        });

        root.add(productCard, column, row);
        column++;
        if (column == a + 1) {
            column = 0;
            row++;
        }
    }

    // Set the position and alignment of the "Add product" button
    GridPane.setColumnIndex(addProductBtn, a);
    GridPane.setHalignment(addProductBtn, HPos.RIGHT);
    root.getChildren().add(addProductBtn); // Add the button back to the GridPane
}




  public static void main(String[] args) {
    //db init
    try {
      db DB=new db(
        "jdbc:mysql://localhost:3306/db2?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
        "user", "user");
      DB.see();
    }  catch (Exception e) {
            e.printStackTrace();
    }
    //gui
    launch(args);
  }
}

