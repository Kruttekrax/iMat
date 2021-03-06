package ListCells;

import BackendMediators.CustomerHandler;
import BackendMediators.StoreHandler;
import Controllers.CartController;
import Utility.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.IntSummaryStatistics;

import static java.lang.System.load;
import static java.lang.System.out;

/**
 * Created by gustav on 2017-02-24.
 */
public class CartElement extends ListCell<ShoppingItem>{
    @FXML Label productName;
    @FXML GridPane grid;
    @FXML Label totalPrice;
    @FXML Label amountTF;
    @FXML Label unitLabel;
    @FXML Button removeButton;
    FXMLLoader mLLoader;

    public CartElement(){
        if (mLLoader == null) {
            mLLoader = new FXMLLoader(getClass().getResource("/layouts/cart_element.fxml"));
            mLLoader.setController(this);

            try {
                mLLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setGraphic(grid);
    }

    @Override
    public void updateItem(ShoppingItem item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            removeButton.setOnAction(e -> StoreHandler.getInstance().removeFromCart(item));
            productName.setText(item.getProduct().getName());
            totalPrice.setText(Util.format(item.getTotal()) + " kr");
            amountTF.setText(Util.format(item.getAmount()) + " " + item.getProduct().getUnitSuffix());
            setGraphic(grid);
        }
    }

}
