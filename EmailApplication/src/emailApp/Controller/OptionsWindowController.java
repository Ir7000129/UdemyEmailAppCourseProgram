package emailApp.Controller;

import emailApp.EmailManager;
import emailApp.view.ColorTheme;
import emailApp.view.FontSize;
import emailApp.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsWindowController extends BaseController implements Initializable {
    @FXML
    private Slider fontSlider;

    @FXML
    private ChoiceBox<ColorTheme> themeChoice;

    public OptionsWindowController(ViewFactory viewFactory, EmailManager emailManager, String fxmlName) {
        super(viewFactory, emailManager, fxmlName);
    }

    @FXML
    void applyAction() {
        viewFactory.setColorTheme(themeChoice.getValue());
        viewFactory.setFontSize(FontSize.values()[(int)(fontSlider.getValue())]);
        viewFactory.updateStyles();
    }

    @FXML
    void cancelAction() {
    	viewFactory.setColorTheme(themeChoice.getValue());
        viewFactory.setFontSize(FontSize.values()[(int)(fontSlider.getValue())]);
        viewFactory.updateStyles();
        Stage stage = (Stage) fontSlider.getScene().getWindow();
        viewFactory.closeShowingStage(stage);
        System.out.println("Options window closed");
    }

    @FXML
    void okAction() {
        Stage stage = (Stage) fontSlider.getScene().getWindow();
        viewFactory.closeShowingStage(stage);
        System.out.println("Options applied and window closed");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUPThemePicker();
        setUpFontSize();
    }

    private void setUpFontSize() {
        fontSlider.setMin(0);
        fontSlider.setMax(FontSize.values().length - 1);
        fontSlider.setValue(viewFactory.getFontSize().ordinal());
        fontSlider.setMajorTickUnit(1);
        fontSlider.setMinorTickCount(0);
        fontSlider.setBlockIncrement(1);
        fontSlider.setSnapToTicks(true);
        fontSlider.setShowTickLabels(true);
        fontSlider.setShowTickMarks(true);
        fontSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double aDouble) {
                int i = aDouble.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String s) {
                return null;
            }

        });

    }

    private void setUPThemePicker() {
        themeChoice.setItems(FXCollections.observableArrayList(ColorTheme.values()));
        themeChoice.setValue(viewFactory.getColorTheme());
    }
}
