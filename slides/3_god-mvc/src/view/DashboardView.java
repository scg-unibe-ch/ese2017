package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;

public class DashboardView extends GridPane {

	private WebView webView;
	private TextField textField;
	private Button hellButton;
	private Button heavenButton;

	public DashboardView() {
		super();
		this.webView = new WebView();
		this.textField = new TextField();
		this.hellButton = new Button("hell");
		this.heavenButton = new Button("heaven");
		this.add(this.webView, 0, 0, 2, 1);
		this.add(this.textField, 0, 1, 2, 1);
		this.add(this.hellButton, 0, 2);
		this.add(this.heavenButton, 1, 2);
		this.initializeLayout();
		this.initializeStyle();
	}

	private void initializeLayout() {
		this.webView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.textField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.hellButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.heavenButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.getRowConstraints().clear();
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(50);
		this.getColumnConstraints().addAll(column1, column2);
		RowConstraints row1 = new RowConstraints();
		row1.setVgrow(Priority.ALWAYS);
		row1.setPercentHeight(80);
		RowConstraints row2 = new RowConstraints();
		row2.setVgrow(Priority.ALWAYS);
		row2.setPercentHeight(10);
		RowConstraints row3 = new RowConstraints();
		row3.setVgrow(Priority.ALWAYS);
		row3.setPercentHeight(10);
		this.getRowConstraints().addAll(row1, row2, row3);
	}

	private void initializeStyle() {
		this.webView.setZoom(1.5);
		this.textField.setFont(Font.font("Helvetica", FontWeight.BOLD, 48));
		this.hellButton.setBackground(
				new Background(new BackgroundFill(Color.rgb(0xff, 0, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
		this.heavenButton.setBackground(
				new Background(new BackgroundFill(Color.rgb(0, 0, 0xff, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
		this.hellButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 48));
		this.hellButton.setTextFill(Color.WHITE);
		this.heavenButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 48));
		this.heavenButton.setTextFill(Color.WHITE);
	}

	public WebView getWebView() {
		return this.webView;
	}

	public TextField getTextField() {
		return this.textField;
	}

	public Button getHellButton() {
		return this.hellButton;
	}

	public Button getHeavenButton() {
		return this.heavenButton;
	}

}
