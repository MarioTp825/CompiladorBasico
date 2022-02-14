module edu.uni.compiladorbasico {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens edu.uni.compiladorbasico to javafx.fxml;
    exports edu.uni.compiladorbasico;
}