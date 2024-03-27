package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ProductCard extends VBox {
    private Label idLabel;
    private Label nameLabel;
    private Label descriptionLabel;
    private Label priceLabel;
    private Label categoryLabel;
    private Label quantityLabel;
    private Button deleteButton;
    private Button editButton;

    public ProductCard(String id, String name, String description, float price, String category, int quantity , float wholeSalePrice) {
        this.setId("product-card");
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
        this.setPadding(new Insets(10));
        this.setSpacing(5);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefWidth(200); // تعيين العرض الافتراضي للبطاقة

        idLabel = new Label("ID: " + id);
        nameLabel = new Label("Name: " + name);
        descriptionLabel = new Label("Description: " + description);
        priceLabel = new Label("Price: $" + price);
        categoryLabel = new Label("Category: " + category);
        quantityLabel = new Label("Quantity: " + quantity);

        idLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");
        nameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");
        descriptionLabel.setStyle("-fx-text-fill: #666666;");
        priceLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #007bff;");
        categoryLabel.setStyle("-fx-text-fill: #666666;");
        quantityLabel.setStyle("-fx-text-fill: #666666;");

       
        
        /*
        deleteButton.setOnAction(event -> {
        	System.out.println(this.getParent());//.remove(this);
            // getParent().getChildren().remove(this);
            // يمكنك هنا إضافة المزيد من الإجراءات مثل حذف المنتج من قاعدة البيانات أو أي عمليات أخرى تحتاجها
        //getParent().getChildrenUnmodifiable().remove(this);
        });
        */
        

        deleteButton = new Button("Delete");
        editButton = new Button("Edit");

        HBox buttonBox = new HBox(10); // تحديد التباعد بين الأزرار
        buttonBox.getChildren().addAll(deleteButton, editButton);

        this.getChildren().addAll(idLabel, nameLabel, descriptionLabel, priceLabel, categoryLabel, quantityLabel, buttonBox);
        
        // إضافة تأثير الظل للبطاقة
        this.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");

        
        

        }
    
    public Button getDeleteButton() {
        return deleteButton;
    }
    
    // طريقة للوصول إلى زر التحرير
    public Button getEditButton() {
        return editButton;
    }
    

}

