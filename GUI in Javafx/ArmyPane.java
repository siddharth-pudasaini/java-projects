/*
Name: Siddhartha Pudasaini
Assignment #: 6
ASU ID: 1222261339
Lecture: T,Th 4:30-5:45
Description: The ArmyPane class creates a VBox that displays the list of the army
 			when load heroes/clear section button is clicked. Each hero in the list
 			can be selected by checking the checkbox to create an army. Changes in selections change the
 			total damage, charisma and strength of the army.
*/

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ArmyPane extends BorderPane {

	//Arraylist containing list of heroes
	ArrayList<Hero> heroList;
	
	//Variables containing army Damage, Strength, and Charisma
	int totalDamage;
	int totalStrength;
	int totalCharisma;

	//Variables holding display objects
	Label armyInformation;	//Label for displaying army stats
	VBox armyDatViewer;		//Heroes list viewer
	Button loadButton;		//Button to load heroes


	//Constructor class that takes in hero list as an input
	public ArmyPane(ArrayList<Hero> heroList) {
		this.heroList = heroList;

		//Instantiating display objects.
			loadButton=new Button("Load Heroes/Clear Selection");
			armyInformation=new Label("");
			armyDatViewer=new VBox();

		//Binding "Load Heroes/Clear Selection" Button to its handler
		loadButton.setOnAction(new LoadHeroesButtonHandler());

		// Organizing components to their positions on BorderPane
		super.setTop(armyInformation);
		super.setCenter(armyDatViewer);
		super.setBottom(loadButton);

	}
	
	private class LoadHeroesButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			//Clearing the army list from VBox (1 line)
			armyInformation.setText("Select heroes to add to your army");
			armyDatViewer.getChildren().removeAll(armyDatViewer.getChildren());
			totalDamage=0;
			totalCharisma=0;
			totalStrength=0;

			//Adding heroes from the hero list to the VBox
			for(Hero hero:heroList){
				CheckBox checkBox=new CheckBox(hero.toString());
				checkBox.setOnAction(new CheckBoxHandler(hero));
				armyDatViewer.getChildren().add(checkBox);
			}
		}
	}

	//Handler to handle checkbox events
	private class CheckBoxHandler implements EventHandler<ActionEvent> {

		//Declaring hero object that will hold data about the hero passed when new checkbox handler object is created
		Hero hero;
		
		// When creating a new CheckBoxHandler, passing in a Hero object so that it can be accessed later
		public CheckBoxHandler(Hero _hero) {
			this.hero = _hero;
		}

		@Override
		//Method to handle checkbox event
		public void handle(ActionEvent event) {

			//Using event.getSource() to get the CheckBox that triggered the event, cast it to CheckBox
			CheckBox selectedCheckBox= (CheckBox) event.getSource();
			
			// If the CheckBox was selected, add the current hero scores to totalStrength
			// totalCharisma, and totalDamge. Otherwise, subtract the current hero scores

			if(selectedCheckBox.isSelected()){
				totalDamage+=hero.getDamage();
				totalCharisma+=hero.getCharisma();
				totalStrength+=hero.getStrength();
			}
			else{
				totalDamage-=hero.getDamage();
				totalCharisma-=hero.getCharisma();
				totalStrength-=hero.getStrength();
			}

			// Changing the lable to update armyInformation
			armyInformation.setText("Total Damage: " + totalDamage + "\t\tTotal Strength: " + totalStrength + "\tTotal Charisma: " + totalCharisma);


		}
	}

}
