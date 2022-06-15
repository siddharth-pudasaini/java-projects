/*
Name: Siddhartha Pudasaini
Assignment #: 6
ASU ID: 1222261339
Lecture: T,Th 4:30-5:45
Description: The HeroPane class creates a VBox that contains an input grid which allows user to input
			hero data and a text area that displays information about heroes added to hero list.
*/


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class HeroPane extends HBox {
	// COMPLETED: contains a list of heroes
	ArrayList<Hero> heroList;

	// COMPLETED: Save current Hero Type
	String selectedHeroType;

	// COMPLETED: Main layout components
	TextArea rightTextArea;
	VBox leftVBox;
	ComboBox<String> heroTypeComboBox;
	ImageView imageView;

	//Declaring the objects to be displayed on the screen
	// ONE GridPane to hold
	GridPane inputPane;

	// FOUR Labels (Name, Strength, Charisma, Damage),
	Label nameLabel;
	Label strengthLabel;
	Label charismaLabel;
	Label damageLabel;

	// FOUR corresponding TextFields
	TextField nameTextField;
	TextField strengthTextField;
	TextField charismaTextField;
	TextField damageTextField;

	// ONE "Random" Button
	Button randomButton;

	// ONE "Add New Hero!!!" Button
	Button addNewHeroButton;

	// ONE red Label to display add hero status.
	Label heroStatusLabel;

	// COMPLETED: Define window size
	public static final int WINSIZE_X = 950, WINSIZE_Y = 600;

	// Constructor - what to do when HeroPane is first created
	public HeroPane(ArrayList<Hero> heroList) {

		// COMPLETED: Assign the heroList passed into this constructor
		this.heroList = heroList;

		// COMPLETED: Initialize main layout components
		this.leftVBox = new VBox();
		this.rightTextArea = new TextArea();
		
		// COMPLETED: Setting up ComboBox
		String[] heroType = { "Mage", "Fighter", "Unicorn", "Zombie" };
		heroTypeComboBox = new ComboBox<String>();
		heroTypeComboBox.setValue("Hero Type");
		heroTypeComboBox.getItems().addAll(heroType);
		heroTypeComboBox.setOnAction(new HeroTypeComboBoxHandler());
		leftVBox.getChildren().add(heroTypeComboBox);

		//Initialize the instance variables

		//Grid pane
		inputPane=new GridPane();

		//Labels
		nameLabel=new Label("Name");
		strengthLabel=new Label("Strength");
		charismaLabel=new Label("Charisma");
		damageLabel=new Label("Damage");

		//Text fields
		nameTextField=new TextField();
		strengthTextField=new TextField();
		charismaTextField=new TextField();
		damageTextField=new TextField();
		damageTextField.setEditable(false);

		//Buttons
		randomButton=new Button("Random");
		addNewHeroButton=new Button("Add New Hero!!!");

		// Initialize the instance variables and set Label color to RED
		heroStatusLabel=new Label("Hero status label");
		heroStatusLabel.setTextFill(Color.web("#FF0000"));

		// Organize Labels, TextFields, and Button onto the GridPane
		inputPane = new GridPane();
		inputPane.add(nameLabel,0,0,1,1);
		inputPane.add(nameTextField,2,0,1,1);
		inputPane.add(strengthLabel,0,1,1,1);
		inputPane.add(strengthTextField,2,1,1,1);
		inputPane.add(charismaLabel,0,2,1,1);
		inputPane.add(charismaTextField,2,2,1,1);
		inputPane.add(damageLabel,0,3,1,1);
		inputPane.add(damageTextField,2,3,1,1);
		inputPane.add(randomButton,3,3,1,1);

		// Bind buttons to their handlers (RandomButtonHandler and AddNewHeroButtonHandler)
		randomButton.setOnAction(new RandomButtonHandler());
		addNewHeroButton.setOnAction(new AddNewHeroButtonHandler());

		//Add GridPane, “Add Hero” Button, and red Label to leftVBox
		leftVBox.getChildren().add(inputPane);
		leftVBox.getChildren().add(addNewHeroButton);
		leftVBox.getChildren().add(heroStatusLabel);

		// COMPLETED: VBox layout alignment
		inputPane.setHgap(20);
		inputPane.setVgap(20);
		leftVBox.setPadding(new Insets(40, 50, 0, 50));
		leftVBox.setSpacing(40);
		leftVBox.setAlignment(Pos.TOP_CENTER);
		leftVBox.setPrefWidth(WINSIZE_X / 2);

		// COMPLETED: Setting up ImageView
		imageView = new ImageView();
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(100);
		leftVBox.getChildren().add(imageView);
		FileInputStream input;

		//Reading image to put in the display
		try {
			input = new FileInputStream("unicorn.png");
			Image image = new Image(input);
			imageView.setImage(image);
		}
		//If the image is not found displaying no image
		catch (FileNotFoundException e) {
			imageView.setImage(null);
		}
		// COMPLETED: Add main components to "Add Hero" tab
		this.getChildren().addAll(leftVBox, rightTextArea);
	}

	private class RandomButtonHandler implements EventHandler<ActionEvent> {

		//Method to handle when the random button is clicked
		public void handle(ActionEvent event) {
			if(damageTextField.getText().isEmpty()){
				int randomNumber=generateRandomNumber();
				damageTextField.setText(String.valueOf(randomNumber));
			}
			else{
				heroStatusLabel.setText("Damage already generated");
			}
		}

		//Method to generate random number
		private int generateRandomNumber(){
			int randomNumber=50+(int)(Math.random()*50);
			return randomNumber;
		}
	}

	// TODO 3. "Add New Hero" button handler - Check for valid input before adding the new Hero to the list
	private class AddNewHeroButtonHandler implements EventHandler<ActionEvent> {

		// This method will be called once we click the button
		public void handle(ActionEvent event) {

			// Creating 4 String variables and assign them to the values retrieved from TextFields using .getText()
			String name=nameTextField.getText();
			String strength=strengthTextField.getText();
			String charisma=charismaTextField.getText();
			String damage=damageTextField.getText();

			//Code block trying to add new hero to the hero list
			try {
				if (selectedHeroType == null) {
					throw new Exception("Hero type is not yet selected");
				}

				// If one of the TextFields is blank, throw exception
				if (name.isBlank()|| strength.isBlank()|| charisma.isBlank()|| damage.isBlank()){
					throw new Exception("At least one of the text fields is empty");
				}

				// Looping through heroList to check for hero that has the same name; throw exception
				for(Hero hero:heroList){
					String heroName=hero.getName().toLowerCase();
					if(heroName.equals(name.toLowerCase())){
						throw new Exception("Hero existed!");
					}
				}

				//Parsing Strength, Charisma, and Damage to integers
				int numCharisma=Integer.parseInt(charisma);
				int numStrength=Integer.parseInt(strength);
				int numDamage=Integer.parseInt(damage);

				//Checking if Strength or Charisma is a negative number, if yes then throwing an error
				if(numCharisma<0||numStrength<0){
					throw new Exception("Both Strength and Charisma must be positive numbers");
				}

				//Check if the sum of Strength and Charisma exceeds 100. If yes then throwing error
				if(numStrength+numCharisma>100){
					throw new Exception("The sum of strength and charisma must be less or equal to 100");
				}

				//If the input is valid,creating new Hero object and adding it to herolist
				selectedHeroType = heroTypeComboBox.getSelectionModel().getSelectedItem();
				Hero newHero=new Hero(name,selectedHeroType,numStrength,numCharisma,numDamage);
				heroList.add(newHero);

				// Setting the Red Label to "Hero added successfully" and empty all TextFields after adding the hero in the list
				heroStatusLabel.setText("Hero added successfully");
				nameTextField.setText("");
				strengthTextField.setText("");
				charismaTextField.setText("");
				damageTextField.setText("");

				//Call updateTextArea() to update heroes list in the text ares
				updateTextArea();
			}
			//Catching the error caused by invalid input data type by the user
			catch (NumberFormatException exception) {
				heroStatusLabel.setText("At least one of the text fields is in the incorrect format");

			}
			//Catching all other exceptions caused while adding the hero to herolist
			catch (Exception exception) {
				//Displaying the message that we passed in "throw new Exception(MESSAGE);" above
				heroStatusLabel.setText(exception.getMessage());
			}
		}
	}
	// Creating a String containing all hero information
	// and loop through heroList to add all heroes' data together
	private void updateTextArea() {
		String allHeroData="";
		for(Hero hero:heroList){
			allHeroData=allHeroData+hero.toString()+"\n";
		}
		rightTextArea.setText(allHeroData);
	}

	// Completed: HeroTypeComboBoxHandler - You don't need to modify this handler
	private class HeroTypeComboBoxHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			selectedHeroType = heroTypeComboBox.getSelectionModel().getSelectedItem();
			FileInputStream input;
			try {
				input = new FileInputStream(selectedHeroType.toLowerCase() + ".png");
				Image image = new Image(input);
				imageView.setImage(image);
			} catch (FileNotFoundException e) {
				imageView.setImage(null);
			}
		}
	}
}
