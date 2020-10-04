package nohagimb;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ResourceBundle;

import static javafx.scene.layout.Priority.ALWAYS;
import static javafx.scene.layout.Priority.max;

public class MainScreenController implements Initializable {

    /*represent month table display*/
    @FXML
    private GridPane monthGridView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setWeekDayNames();
        fillDateNumbers(LocalDate.now());
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

        /*'+1' set first day of the week to be sunday instead of monday*/
        int firstDayOfMonth = (date.withDayOfMonth(1).getDayOfWeek().getValue()+1)%7;

        int row = 0;
        int col = 0;

        /*Graying out early-out-of-range days*/
        LocalDate prevMonth = date.minusMonths(1);
        int prevMonthLength = YearMonth.of(prevMonth.getYear(),prevMonth.getMonth()).lengthOfMonth();
        for (int i = firstDayOfMonth; i > 1 ; i--) {
            monthGridView.add(createGrayedOutDayVBox((prevMonthLength--) +""), i-2, 1);
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

            col = (i + firstDayOfMonth - 2) % 7;         // '-2' translate firstDayOfWeek and monthLength to 0-base.
            row = ((i + firstDayOfMonth - 2) / 7) + 1;   // '+1' first row belong to weekDayLabels

            monthGridView.add(vBox, col, row);
        }

        /*Graying out late-out-of-range days*/
        for (int i = 1; i < 7-(col+2) ; i++) {
            monthGridView.add(createGrayedOutDayVBox(i+""), col+i, row);
        }
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
}
