package application;
/*
import javafx.scene.control.Button;

public class AddProductButton extends Button {
    public AddProductButton() {
        super("Add product");
        setPrefWidth(100);
        setPrefHeight(50);
    }
}
*/
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AddProductButton extends Button {
    public AddProductButton() {
        super("+");
        setPrefWidth(200);
        setPrefHeight(70);
        setStyle("-fx-background-color: #4CAF50;-fx-text-fill:white; -fx-font-size: 30px;");

        // إضافة رمز أيقوني
        /*
        Image icon = new Image(getClass().getResourceAsStream("add_icon.png"));
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        setGraphic(imageView);*/

        // تغيير التصميم عند تمرير المؤشر فوق الزر
        setOnMouseEntered(event -> {
            setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-font-size: 30px;");
        });

        // استعادة التصميم الافتراضي عندما يغادر المؤشر الزر
        setOnMouseExited(event -> {
            setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;-fx-font-size: 30px;");
        });
    }
}
