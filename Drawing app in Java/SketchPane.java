/*
 *   Assignment #: 7
 *   Name: Siddhartha Pudasaini
 *   Student ID: 1222261339
 *   Lecture Time: T Th 4:30-5:45
 *   Description: SketchPane class extends Borderpane class. This class contains methods that layout
 * 					sketch canvas and control to the screen.
 * */


import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;


public class SketchPane extends BorderPane {
	
	//Task 1: Declare all instance variables listed in UML diagram
	ArrayList<Shape> shapeList;
	ArrayList<Shape> tempList;
	Button undoButton;
	Button eraseButton;
	Label fillColorLabel;
	Label strokeColorLabel;
	Label strokeWidthLabel;
	ComboBox fillColorCombo;
	ComboBox strokeWidthCombo;
	ComboBox strokeColorCombo;
	RadioButton radioButtonLine;
	RadioButton radioButtonRectangle;
	RadioButton radioButtonCircle;
	Pane sketchCanvas;
	Color[] colors;
	String[] strokeWidth;
	String[] colorLabels;
	Color currentStrokeColor;
	Color currentFillColor;
	int currentStrokeWidth;
	Line line;
	Circle circle;
	Rectangle rectangle;
	double x1;
	double y1;
	HBox topHBox;
	HBox bottomHBox;

	public SketchPane() {
		// Colors, labels, and stroke widths that are available to the user
		super();
		colors = new Color[] {Color.BLACK, Color.GREY, Color.YELLOW, Color.GOLD, Color.ORANGE, Color.DARKRED, Color.PURPLE, Color.HOTPINK, Color.TEAL, Color.DEEPSKYBLUE, Color.LIME} ;
        colorLabels = new String[] {"black", "grey", "yellow", "gold", "orange", "dark red", "purple", "hot pink", "teal", "deep sky blue", "lime"};
        fillColorLabel = new Label("Fill Color:");
        strokeColorLabel = new Label("Stroke Color:");
        strokeWidthLabel = new Label("Stroke Width:");
        strokeWidth = new String[] {"1", "3", "5", "7", "9", "11", "13"};
		currentFillColor=Color.BLACK;
		currentStrokeColor=Color.BLACK;
		currentStrokeWidth=1;
		shapeList=new ArrayList<Shape>();
		tempList=new ArrayList<Shape>();
		displaySketchCanvas();
		displayTopHBox();
		displayBottomHBox();

    }

	//This method creates hBox that contains combobox to select fill color, stroke color and stroke width and adds
	// them to top of border pane
	private void displayTopHBox(){
		fillColorCombo=new ComboBox<String>();
		fillColorCombo.getItems().addAll(colorLabels);
		fillColorCombo.getSelectionModel().selectFirst();
		fillColorCombo.setOnAction(new ColorHandler("fillColor"));

		strokeWidthCombo=new ComboBox<String>();
		strokeWidthCombo.getItems().addAll(strokeWidth);
		strokeWidthCombo.getSelectionModel().selectFirst();
		strokeWidthCombo.setOnAction(new WidthHandler());

		strokeColorCombo=new ComboBox<String>();
		strokeColorCombo.getItems().addAll(colorLabels);
		strokeColorCombo.getSelectionModel().selectFirst();
		strokeColorCombo.setOnAction(new ColorHandler("strokeColor"));

		topHBox=new HBox(20);
		topHBox.setMinSize(20,40);
		topHBox.setSpacing(30);
		topHBox.setPadding(new Insets(15));
		topHBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,CornerRadii.EMPTY,Insets.EMPTY)));
		topHBox.setAlignment(Pos.CENTER);
		topHBox.getChildren().addAll(fillColorLabel,fillColorCombo,strokeWidthLabel,strokeWidthCombo,strokeColorLabel,strokeColorCombo);
		super.setTop(topHBox);
	}

	//This method creates a sketch canvas and adds it to the borderpane
	private void displaySketchCanvas(){
		sketchCanvas=new Pane();
		sketchCanvas.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
		sketchCanvas.setOnMousePressed(new MouseHandler());
		sketchCanvas.setOnMouseExited(new MouseHandler());
		sketchCanvas.setOnMouseDragged(new MouseHandler());
		sketchCanvas.setOnMouseReleased(new MouseHandler());
		super.setCenter(sketchCanvas);

	}

	//This method creates hBox that contains radio button to select shape, and buttons to undo and erase and
	// them to bottom of border pane
	private void displayBottomHBox(){
		ToggleGroup radioGroup=new ToggleGroup();
		radioButtonRectangle=new RadioButton("Rectangle");
		radioButtonCircle=new RadioButton("Circle");
		radioButtonLine=new RadioButton("Line");

		radioButtonCircle.setToggleGroup(radioGroup);
		radioButtonRectangle.setToggleGroup(radioGroup);
		radioButtonLine.setToggleGroup(radioGroup);
		radioButtonLine.setSelected(true);

		undoButton=new Button("Undo");
		eraseButton=new Button("Erase");
		eraseButton.setOnAction(new ButtonHandler("erase"));
		undoButton.setOnAction(new ButtonHandler("undo"));

		bottomHBox=new HBox(20);
		bottomHBox.setMinSize(20,40);
		bottomHBox.setSpacing(30);
		bottomHBox.setPadding(new Insets(15));
		bottomHBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,CornerRadii.EMPTY,Insets.EMPTY)));
		bottomHBox.setAlignment(Pos.CENTER);
		bottomHBox.getChildren().addAll(radioButtonLine,radioButtonRectangle,radioButtonCircle,undoButton,eraseButton);
		super.setBottom(bottomHBox);
	}


	//Mouse handler
	private class MouseHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {

			//If rectangle is selected
			if (radioButtonRectangle.isSelected()) {
				//Mouse is pressed
				if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					rectangle = new Rectangle();
					x1 = event.getX();
					y1 = event.getY();
					rectangle.setX(x1);
					rectangle.setY(y1);
					rectangle.setFill(Color.WHITE);
					rectangle.setStroke(Color.BLACK);
					sketchCanvas.getChildren().add(rectangle);
				}
				//Mouse is dragged
				else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
					if (event.getX() - x1 <0) 
						rectangle.setX(event.getX());
					if (event.getY() - y1 <0) 
						rectangle.setY(event.getY());
					rectangle.setWidth(Math.abs(event.getX() - x1));
					rectangle.setHeight(Math.abs(event.getY() - y1));
				}

				//Mouse is released
				else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
					rectangle.setFill(currentFillColor);
					rectangle.setStroke(currentStrokeColor);
					rectangle.setStrokeWidth(currentStrokeWidth);
					shapeList.add(rectangle);
				}
			}

			//If circle is selected
			else if(radioButtonCircle.isSelected()){
				//Mouse pressed
				if(event.getEventType()==MouseEvent.MOUSE_PRESSED){
					x1= event.getX();
					y1= event.getY();
					circle=new Circle();
					circle.setCenterX(x1);
					circle.setCenterY(y1);
					circle.setFill(Color.WHITE);
					circle.setStroke(Color.BLACK);
					sketchCanvas.getChildren().add(circle);
				}

				//Mouse dragged
				else if(event.getEventType()==MouseEvent.MOUSE_DRAGGED){
					double eventX=event.getX();
					double eventY=event.getY();
					double radius=getDistance(x1,y1,eventX,eventY);
					circle.setRadius(radius);
				}

				//Mouse released
				else if (event.getEventType()==MouseEvent.MOUSE_RELEASED){
					circle.setFill(currentFillColor);
					circle.setStroke(currentStrokeColor);
					circle.setStrokeWidth(currentStrokeWidth);
					shapeList.add(circle);
				}
			}

			//If line is selected
			else if(radioButtonLine.isSelected()){

				//Mouse pressed
				if(event.getEventType()==MouseEvent.MOUSE_PRESSED){
					x1= event.getX();
					y1= event.getY();
					line=new Line();
					line.setStartX(x1);
					line.setStartY(y1);
					line.setEndX(x1);
					line.setEndY(y1);
					line.setStroke(Color.BLACK);
					sketchCanvas.getChildren().add(line);
				}

				//Mouse dragged
				else if(event.getEventType()==MouseEvent.MOUSE_DRAGGED){
					line.setEndX(event.getX());
					line.setEndY(event.getY());
				}

				//Mouse released
				else if(event.getEventType()==MouseEvent.MOUSE_RELEASED){
					line.setStroke(currentStrokeColor);
					line.setStrokeWidth(currentStrokeWidth);
					shapeList.add(line);
				}
			}
		}
	}
		
	private class ButtonHandler implements EventHandler<ActionEvent> {
		String buttonTriggered;

		public ButtonHandler(String buttonTriggered){
			this.buttonTriggered=buttonTriggered;
		}

		@Override
		public void handle(ActionEvent event) {

			try{
				//If undo button is triggered
				if(buttonTriggered.equals("undo")){
					int shapeListSize=shapeList.size();
					int tempListSize=tempList.size();

					//Is nothing is on the screen to remove
					if(shapeListSize==0 && tempListSize==0){
						System.out.println("Nothing to remove");
					}

					//If erased button was pressed early
					else if(shapeListSize==0 && tempListSize>0){
						for(Shape shape:tempList){
							shapeList.add(shape);
						}
						tempList.clear();
						sketchCanvas.getChildren().addAll(shapeList);
					}

					//If there are shapes to remove from screen
					else{
						sketchCanvas.getChildren().remove(shapeList.get(shapeListSize-1));
						shapeList.remove(shapeListSize-1);
					}

				}

				//If erase button is triggered then removing all elements from sketchpane and storing them in a
				//temporary list
				else if(buttonTriggered.equals("erase")){
					for(Shape shape:shapeList){
						tempList.add(shape);
					}
					sketchCanvas.getChildren().removeAll(shapeList);
					shapeList.clear();
				}

			}
			//Exception handling
			catch(Exception e){
				System.out.println(e);
			}
		}

	}

	private class ColorHandler implements EventHandler<ActionEvent> {
		String comboBoxType;
		public ColorHandler(String comboBoxType){this.comboBoxType=comboBoxType;}
		@Override
		public void handle(ActionEvent event) {
			ComboBox selectedComboBox=(ComboBox) event.getSource();
			int selectedColorIndex=selectedComboBox.getSelectionModel().getSelectedIndex();
			if(comboBoxType.equals("fillColor")){
				currentFillColor=colors[selectedColorIndex];
			}
			else {
				currentStrokeColor=colors[selectedColorIndex];
			}

		}
	}
	
	private class WidthHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event){
			ComboBox selectedComboBox=(ComboBox) event.getSource();
			int selectedWidthIndex=selectedComboBox.getSelectionModel().getSelectedIndex();
			currentStrokeWidth=Integer.parseInt(strokeWidth[selectedWidthIndex]);
		}
	}
	
		
	// Get the Euclidean distance between (x1,y1) and (x2,y2)
    private double getDistance(double x1, double y1, double x2, double y2)  {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

}