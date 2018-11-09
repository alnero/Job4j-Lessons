package alnero.chess;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import alnero.chess.figures.Cell;
import alnero.chess.figures.Figure;

import alnero.chess.figures.black.PawnBlack;
import alnero.chess.figures.black.BishopBlack;
import alnero.chess.figures.black.KingBlack;
import alnero.chess.figures.black.KnightBlack;
import alnero.chess.figures.black.QueenBlack;
import alnero.chess.figures.black.RookBlack;

import alnero.chess.figures.white.PawnWhite;
import alnero.chess.figures.white.BishopWhite;
import alnero.chess.figures.white.KingWhite;
import alnero.chess.figures.white.KnightWhite;
import alnero.chess.figures.white.QueenWhite;
import alnero.chess.figures.white.RookWhite;

/**
 *
 */
public class Chess extends Application {
    /**
     * Title of game play window.
     */
    private static final String STAGE_TITLE = "Chess";
    /**
     * Size of board.
     */
    private final int size = 8;
    /**
     * Instance of logic for figures moves.
     */
    private final Logic logic = new Logic();

    /**
     * Create rectangles.
     * @param x coordinate x
     * @param y coordinate y
     * @param size size of rectangle
     * @param white will it be white rectangle
     * @return rectangle with defined parameters
     */
    private Rectangle buildRectangle(int x, int y, int size, boolean white) {
        Rectangle rect = new Rectangle();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setHeight(size);
        rect.setWidth(size);
        if (white) {
            rect.setFill(Color.WHITE);
        } else {
            rect.setFill(Color.GRAY);
        }
        rect.setStroke(Color.BLACK);
        return rect;
    }

    /**
     * Create movable figures.
     * @param x coordinate x
     * @param y coordinate y
     * @param size size of rectangle for figure
     * @param image image of figure
     * @return rectangle with image of figure
     */
    private Rectangle buildFigure(int x, int y, int size, String image) {
        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setHeight(size);
        rect.setWidth(size);
        Image img = new Image(this.getClass().getClassLoader().getResource(image).toString());
        rect.setFill(new ImagePattern(img));
        final Rectangle momento = new Rectangle(x, y);
        rect.setOnDragDetected(
                event -> {
                    momento.setX(event.getX());
                    momento.setY(event.getY());
                }
        );
        rect.setOnMouseDragged(
                event -> {
                    rect.setX(event.getX() - size / 2);
                    rect.setY(event.getY() - size / 2);
                }
        );
        rect.setOnMouseReleased(
                event -> {
                    if (logic.move(this.findBy(momento.getX(), momento.getY()), this.findBy(event.getX(), event.getY()))) {
                        rect.setX(((int) event.getX() / 40) * 40 + 5);
                        rect.setY(((int) event.getY() / 40) * 40 + 5);
                    } else {
                        rect.setX(((int) momento.getX() / 40) * 40 + 5);
                        rect.setY(((int) momento.getY() / 40) * 40 + 5);
                    }
                }
        );
        return rect;
    }

    /**
     * Create grid of rectangles.
     * @return grid of rectangles
     */
    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != this.size; y++) {
            for (int x = 0; x != this.size; x++) {
                panel.getChildren().add(
                        this.buildRectangle(x, y, 40, (x + y) % 2 == 0)
                );
            }
        }
        return panel;
    }

    @Override
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        control.setPrefHeight(40);
        control.setSpacing(10.0);
        control.setAlignment(Pos.BASELINE_CENTER);
        Button start = new Button("Reset");
        start.setOnMouseClicked(
                event -> this.refresh(border)
        );
        control.getChildren().addAll(start);
        border.setBottom(control);
        border.setCenter(this.buildGrid());
        stage.setScene(new Scene(border, 400, 400));
        stage.setTitle(STAGE_TITLE);
        stage.setResizable(false);
        stage.show();
        this.refresh(border);
    }

    /**
     * Refresh board and reset figures for new game.
     * @param border layout to place grid of rectangles and figures
     */
    private void refresh(final BorderPane border) {
        Group grid = this.buildGrid();
        this.logic.clean();
        border.setCenter(grid);
        this.buildWhiteTeam(grid);
        this.buildBlackTeam(grid);
    }

    /**
     * Create and place team of black figures.
     * @param grid grid to which figures are added
     */
    private void buildBlackTeam(Group grid) {
        this.add(new PawnBlack(Cell.A7), grid);
        this.add(new PawnBlack(Cell.B7), grid);
        this.add(new PawnBlack(Cell.C7), grid);
        this.add(new PawnBlack(Cell.D7), grid);
        this.add(new PawnBlack(Cell.E7), grid);
        this.add(new PawnBlack(Cell.F7), grid);
        this.add(new PawnBlack(Cell.G7), grid);
        this.add(new PawnBlack(Cell.H7), grid);
        this.add(new RookBlack(Cell.A8), grid);
        this.add(new KnightBlack(Cell.B8), grid);
        this.add(new BishopBlack(Cell.C8), grid);
        this.add(new QueenBlack(Cell.D8), grid);
        this.add(new KingBlack(Cell.E8), grid);
        this.add(new BishopBlack(Cell.F8), grid);
        this.add(new KnightBlack(Cell.G8), grid);
        this.add(new RookBlack(Cell.H8), grid);
    }

    /**
     * Create and place team of white figures.
     * @param grid grid to which figures are added
     */
    public void buildWhiteTeam(Group grid) {
        this.add(new PawnWhite(Cell.A2), grid);
        this.add(new PawnWhite(Cell.B2), grid);
        this.add(new PawnWhite(Cell.C2), grid);
        this.add(new PawnWhite(Cell.D2), grid);
        this.add(new PawnWhite(Cell.E2), grid);
        this.add(new PawnWhite(Cell.F2), grid);
        this.add(new PawnWhite(Cell.G2), grid);
        this.add(new PawnWhite(Cell.H2), grid);
        this.add(new RookWhite(Cell.A1), grid);
        this.add(new KnightWhite(Cell.B1), grid);
        this.add(new BishopWhite(Cell.C1), grid);
        this.add(new QueenWhite(Cell.D1), grid);
        this.add(new KingWhite(Cell.E1), grid);
        this.add(new BishopWhite(Cell.F1), grid);
        this.add(new KnightWhite(Cell.G1), grid);
        this.add(new RookWhite(Cell.H1), grid);
    }

    /**
     * Add figure to the grid.
     * @param figure figure to add
     * @param grid used grid
     */
    public void add(Figure figure, Group grid) {
        this.logic.add(figure);
        Cell position = figure.position();
        grid.getChildren().add(
                this.buildFigure(
                        position.getX() * 40 + 5,
                        position.getY() * 40 + 5,
                        30,
                        figure.icon()
                )
        );
    }

    /**
     * Find cell by coordinates.
     * @param graphX coordinate x of layout
     * @param graphY coordinate y of layout
     * @return cell found by coordinates
     */
    private Cell findBy(double graphX, double graphY) {
        Cell rst = Cell.A1;
        int x = (int) graphX / 40;
        int y = (int) graphY / 40;
        for (Cell cell : Cell.values()) {
            if (cell.getX() == x && cell.getY() == y) {
                rst = cell;
                break;
            }
        }
        return rst;
    }
}
