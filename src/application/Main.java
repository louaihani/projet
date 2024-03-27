package application;

import javafx.application.Application;


import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
public class Main extends Application {

    private ArrayList<ProductCard> productCardList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        try {
        	double width1=0;
            GridPane root = new GridPane();
            root.setHgap(10);
            root.setVgap(10);
            Scene scene = new Scene(root, 400, 400);

            
            
            // إنشاء بعض البطاقات الافتراضية
            productCardList.add(new ProductCard(String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000)), "Rolex", "green", 100, "watch", 50 ,75));
            productCardList.add(new ProductCard(String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000)), "Rolex", "green", 130, "watch", 50 ,60));
            productCardList.add(new ProductCard(String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000)), "Rolex", "green", 150, "watch", 50 ,30));

            // إنشاء زر "Add product"
            Button addProductBtn = new AddProductButton();
            
            addProductBtn.setOnAction(event -> {
            	showAddProductDialog("Add" ,0);
            displayProducts(root , addProductBtn , (double)scene.getWidth());
            });
            

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setTitle("GESTION DE STOCK");
            displayProducts(root , addProductBtn , (double)scene.getWidth() );

            
            scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            	displayProducts(root,addProductBtn ,(double)newValue  );
            	//width1= (double) newValue;
            	});
           
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
           // root.add(addProductBtn, 0, 0);

            

          
    }

    private void showAddProductDialog(String a , int index) {
        // إنشاء مربع حوار جديد
        Alert addProductDialog = new Alert(Alert.AlertType.NONE);
        addProductDialog.setTitle(a + " Product");

        // إضافة مدخلات لاسم المنتج والفئة (Category)
        TextField productNameField = new TextField();
        TextField categoryField = new TextField();

        TextField sellingPriceField = new TextField();
        TextField quantityField = new TextField();

        TextField discriptionField = new TextField();
        TextField wholeSalePriceField = new TextField();

        VBox content = new VBox();
        content.getChildren().addAll(
                new javafx.scene.control.Label("Product Name:"), productNameField,
                new javafx.scene.control.Label("Category:"), categoryField , 

                new javafx.scene.control.Label("sellingPriceField:"), sellingPriceField ,
                new javafx.scene.control.Label("discriptionField:"), discriptionField ,
                new javafx.scene.control.Label("wholeSalePriceField:"), wholeSalePriceField , 

                
                new javafx.scene.control.Label("quantity:"), quantityField 
        );

         addProductDialog.getDialogPane().setContent(content);

        // إضافة أزرار تأكيد وإلغاء
        addProductDialog.getButtonTypes().clear();
        addProductDialog.getButtonTypes().addAll(javafx.scene.control.ButtonType.OK, javafx.scene.control.ButtonType.CANCEL);

        // عرض مربع الحوار وانتظار اختيار المستخدم
        addProductDialog.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                String name = productNameField.getText();
                String category = categoryField.getText();
                String price = sellingPriceField.getText();
                String wholeSalePrice = wholeSalePriceField.getText();
                String description = discriptionField.getText();
                String quantity = quantityField.getText();
               
                //  إضافة المنتج الجديد إلى القائمة
                if(a=="Add") {
                productCardList.add(new ProductCard(
                		
                        String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000)),
                        name,
                        description,
                        Float.parseFloat(price),
                        category,
                        Integer.parseInt(quantity),
                        Float.parseFloat(wholeSalePrice)
                    ));
                }
                else if(a=="Edit") {
                	productCardList.set(index,new ProductCard(
                            String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000)),
                            name,
                            description,
                            Float.parseFloat(price),
                            category,
                            Integer.parseInt(quantity),
                            Float.parseFloat(wholeSalePrice)
                        ) );
                }
                // إعادة عرض المنتجات
                
                
                // احتفظ بالمدخلات وقم بما تريد بها (مثل إضافتها إلى قائمة المنتجات)
                System.out.println("Product Name: " + name);
                System.out.println("Category: " + category);
            }
        });
    }

    private void displayProducts(GridPane root , Button addProductBtn , double width ) {
        // مسح المربع الحالي
       // root.getChildren().clear();
    	 int row = 1;
         int column = 0;
         int a = (int)width/200;
         System.out.println(a);
         root.getChildren().clear();
         root.add(addProductBtn, a,0);
         //GridPane.setColumnIndex(addProductBtn, a-1);
         // إعادة عرض المنتجات بناءً على القائمة الجديدة
        for (ProductCard productCard : productCardList) {
        	productCard.getDeleteButton().setOnAction((event)->{
        		
        		 productCardList.remove(productCard);
        		 displayProducts(root , addProductBtn , width);
        		 //root.getChildren().remove(productCard);
        	     });
        	productCard.getEditButton().setOnAction((event)->{
        	/*	
       		 productCardList.remove(productCard);
       		 displayProducts(root , addProductBtn);
       		 */
        		int index = productCardList.indexOf(productCard);

        		showAddProductDialog("Edit" , index);
        		displayProducts(root , addProductBtn , width);
        		 
        		 
        		 
       		 //root.getChildren().remove(productCard);
       		
       		 
       	});
        	
        	
        	
            root.add(productCard, column, row);
            column++;
            if (column == a+1) {
                column = 0;
                row++;
            }
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);

        // تعطيل التمرير الأفقي وتمكين التمرير الرأسي
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        }
    

    public static void main(String[] args) {
        launch(args);
    }
}

