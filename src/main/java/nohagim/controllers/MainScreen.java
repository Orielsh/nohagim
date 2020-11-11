package nohagim.controllers;

import javafx.animation.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import nohagim.App;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ResourceBundle;

import static javafx.scene.layout.Priority.ALWAYS;

public class MainScreen implements Initializable {
    /*Fxml components*/
    @FXML
    BorderPane borderPane;
    /*monthGridView - represent month table display*/
    @FXML
    private GridPane monthGridView;
    @FXML
    Button datePickTrayButton;
    @FXML
    Button menuButton;
    @FXML
    AnchorPane datePickTrayPanel;
    @FXML
    VBox monthList;
    @FXML
    Label selectYearLabel;


    /*Help data-members*/
    private boolean isSlideOpen = false;
    private SimpleIntegerProperty year;
    private LocalDate dateToDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateToDisplay = LocalDate.now();
        year = new SimpleIntegerProperty();
        year.set(LocalDate.now().getYear());
        selectYearLabel.textProperty().bind(year.asString());
        setWeekDayNames();
        fillDateNumbers(dateToDisplay);
    }

    /*Set weekday names labels on monthGridView.
    Can implemented inside fxml doc but take weekday names from
    * system so no need language translation*/
    private void setWeekDayNames(){
        /*Week days in java are 1-base and not 0-base.
        means that sunday will be in days[1] and not days[0]*/
        String[] days = new DateFormatSymbols().getShortWeekdays();
        for (int i = 1; i <= 7; i++) {
            Label weekDayLabel = new Label(days [i]);
            weekDayLabel.setAlignment(Pos.CENTER);
            weekDayLabel.getStyleClass().add("week_day_name_label");
            GridPane.setHgrow(weekDayLabel, ALWAYS);
            GridPane.setHalignment(weekDayLabel, HPos.CENTER);
            monthGridView.add(weekDayLabel, i-1, 0);

        }
    }

    private void fillDateNumbers(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonth().getValue();
        int monthLength = YearMonth.of(year,month).lengthOfMonth();
        /*Days in Java are 1-base and not 0-base. Also, default first day is monday.
        * '%7' mapping: sunday=0, monday=1,..*/
        int firstDayOfMonth = ((date.withDayOfMonth(1).getDayOfWeek().getValue())%7);

        int row = 0;
        int col = 0;


        /*remove prev display*/
        monthGridView.getChildren().removeIf(node -> GridPane.getRowIndex(node)>0);
        /*Graying out early-out-of-range days*/
        LocalDate prevMonth = date.minusMonths(1);
        int prevMonthLength = YearMonth.of(prevMonth.getYear(),prevMonth.getMonth()).lengthOfMonth();
        for (int i = firstDayOfMonth-1; i > -1 ; i--) {
            monthGridView.add(createGrayedOutDayVBox((prevMonthLength--) +""), i, 1);
        }

        int currentDay = LocalDate.now().getDayOfMonth();
        boolean isCurrentMonth = (year == LocalDate.now().getYear() && //indication whether showing current month
                month == LocalDate.now().getMonth().getValue());

        /*//Setup current month days*/
        for (int i = 1; i <= monthLength ;i++){
            Label title = new Label(i+"");
            VBox vBox = new VBox();
            vBox.getStyleClass().add("valid_day_vBox");
            vBox.setAlignment(Pos.TOP_CENTER);
            GridPane.setHgrow(vBox, ALWAYS);
            GridPane.setVgrow(vBox,ALWAYS);

            if (isCurrentMonth && i == currentDay) {
                title.getStyleClass().add("month_current_day_number_label");

                Circle circle = new Circle();
                circle.getStyleClass().add("month_current_day_label_circled_background");
                circle.radiusProperty().set((title.getFont().getSize()) * 0.8);

                StackPane pane = new StackPane();
                pane.getChildren().addAll(circle,title);

                vBox.getChildren().add(pane);
            }else {
                title.getStyleClass().add("month_days_number_labels");
                vBox.getChildren().add(title);
            }

            col = (i + firstDayOfMonth - 1) % 7;         // '-2' translate firstDayOfWeek and monthLength to 0-base.
            row = ((i + firstDayOfMonth - 1) / 7) + 1;   // '+1' first row belong to weekDayLabels
            monthGridView.add(vBox, col, row);
        }

        /*Graying out late-out-of-range days*/
        for (int i = 1; i < 8-(col+1) ; i++) {
            monthGridView.add(createGrayedOutDayVBox(i+""), col+i, row);
        }
    }

    /*Fxml Doc Methods:*/
    @FXML
    private void slide(){
        if(isSlideOpen)
            slideOut();
        else
            slideIn();
    }
    @FXML
    private void increaseYear(){
        dateToDisplay = dateToDisplay.plusYears(1);
        year.set(dateToDisplay.getYear());
        fillDateNumbers(dateToDisplay);
    }
    @FXML
    private void decreaseYear(){
        dateToDisplay = dateToDisplay.minusYears(1);
        year.set(dateToDisplay.getYear());
        fillDateNumbers(dateToDisplay);
    }

    @FXML
    private void setMonth(Event e){
        String labelText = ((Label)e.getSource()).getText();
        int monthNumber = 0;
        switch (labelText) {
            case "ינואר":
                monthNumber = 1;
                break;
            case "פברואר":
                monthNumber = 2;
                break;
            case "מרץ":
                monthNumber = 3;
                break;
            case "אפריל":
                monthNumber = 4;
                break;
            case "מאי":
                monthNumber = 5;
                break;
            case "יוני":
                monthNumber = 6;
                break;
            case "יולי":
                monthNumber = 7;
                break;
            case "אוגוסט":
                monthNumber = 8;
                break;
            case "ספטמבר":
                monthNumber = 9;
                break;
            case "אוקטובר":
                monthNumber = 10;
                break;
            case "נובמבר":
                monthNumber = 11;
                break;
            case "דצמבר":
                monthNumber = 12;
                break;
        }

        dateToDisplay = LocalDate.of(year.getValue(),monthNumber,1);
        fillDateNumbers(dateToDisplay);
    }

    /*Help methods*/
    private void slideIn(){
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(datePickTrayPanel.prefWidthProperty(), 150, Interpolator.LINEAR);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.2), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        timeline.setOnFinished(t -> {
            isSlideOpen = true;
            datePickTrayButton.setRotate(180);
        });
    }

    private void slideOut() {
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(datePickTrayPanel.prefWidthProperty(), 0, Interpolator.LINEAR);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.2), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        timeline.setOnFinished(t -> {
            isSlideOpen = false;
            datePickTrayButton.setRotate(0);
        });
    }

    private VBox createGrayedOutDayVBox(String text) {
        Label title = new Label(text);
        title.getStyleClass().add("month_days_number_labels");
        VBox vBox = new VBox();
        vBox.getStyleClass().add("grayedOut_day_vBox");
        vBox.setAlignment(Pos.TOP_CENTER);
        GridPane.setHgrow(vBox, ALWAYS);
        GridPane.setVgrow(vBox,ALWAYS);
        vBox.getChildren().add(title);
        return vBox;
    }

    @FXML
    private void displayMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),250,300);
        Stage stage = new Stage();
        stage.setTitle("תפריט");
        stage.setScene(scene);
        stage.setResizable(false);
        //TODO make stage.setX() to left of the main stage or use sliding menu.
        stage.setX(150);
        stage.setY(150);    //todo fix this later - using sliding menu. Now only for setup.
        //END to-do
        menuButton.disableProperty().bind(stage.showingProperty());
        stage.show();
    }
}
