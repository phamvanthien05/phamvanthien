import javax.swing.plaf.FileChooserUI;
import java.io.*;
import java.util.ArrayList;

public class textModel {
    private String text = "";

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}