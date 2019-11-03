package alnero.tictactoe;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * TicTacToe application.
 */
public class TicTacToe extends Application {
    /** Name of application window. */
    private static final String NAME_OF_THE_GAME = "Крестики-нолики";
    /** Size of the game matrix and game field. */
    private final int size = 3;
    /** Main matrix of the game. */
    private final Figure3T[][] table = new Figure3T[size][size];
    /** Game logic. */
    private final Logic3T logic = new Logic3T(table);

    /**
     * Create main figure of the game - rectangle.
     * @param x x coordinate of the upper left corner
     * @param y y coordinate of the upper left corner
     * @param size height and width are equal, rectangle is a square
     * @return black, white filled rectangle with defined coordinates and size
     */
    private Figure3T buildRectangle(int x, int y, int size) {
        Figure3T rect = new Figure3T();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setHeight(size);
        rect.setWidth(size);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    /**
     * Create main grid of the game filled with rectangles.
     * Add rectangle objects to main matrix of the game.
     * Bind mouse click events.
     * @return group of rectangles, main field of the game
     */
    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != this.size; y++) {
            for (int x = 0; x != this.size; x++) {
                Figure3T rect = this.buildRectangle(x, y, 50);
                this.table[y][x] = rect;
                panel.getChildren().add(rect);
                rect.setOnMouseClicked(this.buildMouseEvent(panel));
            }
        }
        return panel;
    }


    /**
     * Create black, white filled O mark, using circle object.
     * @param x horizontal position of center of mark
     * @param y vertical position of center of mark
     * @param size size to be used in calculation of radius and position of mark
     * @return group presenting O mark
     */
    private Group buildMarkO(double x, double y, int size) {
        Group group = new Group();
        int radius = size / 2;
        Circle circle = new Circle(x + radius, y + radius, radius - 10);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        group.getChildren().add(circle);
        return group;
    }

    /**
     * Create X mark, using two crossing line objects.
     * @param x x coordinate used to calculate start and end of lines
     * @param y y coordinate used to calculate start and end of lines
     * @param size size used to calculate length of lines
     * @return group presenting X mark
     */
    private Group buildMarkX(double x, double y, int size) {
        Group group = new Group();
        group.getChildren().addAll(
                new Line(
                        x + 10, y  + 10,
                        x + size - 10, y + size - 10
                ),
                new Line(
                        x + size - 10, y + 10,
                        x + 10, y + size - 10
                )
        );
        return group;
    }

    /**
     * Create event handler for mouse clicks inside rectangles.
     * Left mouse click - draws X, right mouse click - draws O.
     * After every event game checks for winner and presence of empty rectangles.
     * @param panel group of elements to bind event
     * @return mouse event handler
     */
    private EventHandler<MouseEvent> buildMouseEvent(Group panel) {
        return event -> {
            Figure3T rect = (Figure3T) event.getTarget();
            if (this.checkState()) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    rect.take(true);
                    panel.getChildren().add(
                            this.buildMarkX(rect.getX(), rect.getY(), 50)
                    );
                } else {
                    rect.take(false);
                    panel.getChildren().add(
                            this.buildMarkO(rect.getX(), rect.getY(), 50)
                    );
                }
                this.checkWinner();
                this.checkState();
            }
        };
    }

    /**
     * Show alerts of the game.
     * @param message message shown in alert window
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(NAME_OF_THE_GAME);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Check presence of empty rectangles on the game field.
     * @return are all rectangles filled with marks
     */
    private boolean checkState() {
        boolean gap = this.logic.hasGap();
        if (!gap) {
            this.showAlert("Все поля запонены! Начните новую Игру!");
        }
        return gap;
    }

    /**
     * Check that there is a winner of the game, show appropriate message.
     */
    private void checkWinner() {
        if (this.logic.isWinnerX()) {
            this.showAlert("Победили Крестики! Начните новую Игру!");
        } else if (this.logic.isWinnerO()) {
            this.showAlert("Победили Нолики! Начните новую Игру!");
        }
    }

    @Override
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        control.setPrefHeight(40);
        control.setSpacing(10.0);
        control.setAlignment(Pos.BASELINE_CENTER);
        Button start = new Button("Начать");
        start.setOnMouseClicked(
                event -> border.setCenter(this.buildGrid())
        );
        control.getChildren().addAll(start);
        border.setBottom(control);
        border.setCenter(this.buildGrid());
        stage.setScene(new Scene(border, 300, 300));
        stage.setTitle(NAME_OF_THE_GAME);
        stage.setResizable(false);
        stage.show();
    }
}
