package application;
	
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
	    try { 
	    	GridPane root = new GridPane(); 
	    	root.setHgap(10); // تحديد التباعد الأفقي بين العناصر في الشبكة
	        root.setVgap(10);
	        
	    	Button AddProductBtn = new Button("add product");
	    	AddProductBtn.setPrefWidth(100); // تعيين العرض المفضل للزر
	    	AddProductBtn.setPrefHeight(50);
	    	root.add(AddProductBtn, 0, 0);



	    	
	    	ProductCard productCard1 = new ProductCard("1", "Rolex", "green", 100, "watch", 50);
	    	//productCard1.setStyle(" -fx-background-color:red;");
	    	
	    	ProductCard productCard2 = new ProductCard("2", "Rolex", "green", 100, "watch", 50);
	    	//productCard2.setStyle(" -fx-background-color:red;");
	    	ProductCard productCard3 = new ProductCard("3", "Rolex", "green", 100, "watch", 50);
	    //	productCard3.setStyle(" -fx-background-color:red;");
	    	ProductCard[] productCardArray = new ProductCard[3];
	    	productCardArray[0] = productCard1;
	    	productCardArray[1] = productCard2;
	    	productCardArray[2] = productCard3;
	    	int row = 1;
	    	int column =0;
	    	for (int i = 0; i < productCardArray.length; i++) {
	    	    root.add(productCardArray[i], column, row);
	    	    column++;
	    	    if(column==3) {
	    	    	column =0;
	    		row++;
	    	}
	    	}
	    	
	    	/*
	    	root.add(productCard1, 0, 1);
	    	root.add(productCard2,1,1);
	    	root.add(productCard3 ,2,1);*/
 	    	Scene scene = new Scene(root, 400, 400);
	    	primaryStage.setScene(scene);
	        primaryStage.show();
	        primaryStage.setTitle("GESTION DE STOCK");
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}

	
}
