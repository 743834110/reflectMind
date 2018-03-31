package cn.edu.lingnan.view;

import cn.edu.lingnan.sdk.controller.Controller;
import cn.edu.lingnan.utils.R;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by Administrator on 2018/3/30.
 * @author feng
 */
public class WindowView extends AbstractView {

    private Parent parent;

    /**
     * 显示填充人生阶段的对话窗口
     * @return
     */
    public Parent showFillPhaseContainerView(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getResourcesAsURL("layouts/FillPhaseContainer.fxml"));
        this.parent = null;
        try {
            this.parent = loader.load();
            Controller controller = loader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(this.parent);
            stage.setScene(scene);
            controller.setStage(stage);
            stage.initStyle(StageStyle.UTILITY);
            stage.initOwner(this.stage);
            stage.show();
            return parent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
