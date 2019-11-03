module Part04.Lesson03 {
    requires javafx.controls;
    requires javafx.fxml;

    opens alnero.tictactoe to javafx.fxml;
    exports alnero.tictactoe;
}