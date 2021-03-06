package org.softclicker.server.gui;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.softclicker.server.entity.User;
import org.softclicker.server.exception.SoftClickerException;
import org.softclicker.server.gui.controllers.main.MainController;
import org.softclicker.server.manage.AnswerManager;
import org.softclicker.server.manage.ClazzManager;
import org.softclicker.server.manage.QuestionManager;
import org.softclicker.server.manage.UserManager;

/**
 * Created by chamika on 4/12/16.
 */
public class MainApplication extends Application {

    private final static Logger log = LogManager.getLogger(MainApplication.class);

    private static MainApplication instance;

    private AnswerManager answerManager;
    private QuestionManager questionManager;
    private UserManager userManager;
    private ClazzManager clazzManager;

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    public static MainApplication getInstance() {
        if (instance == null) {
            instance = new MainApplication();
        }
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {

        new Thread(() -> {
            try {
                //set gphyph font
                SVGGlyphLoader.loadGlyphsFont(getClass().getResourceAsStream("/font/icomoon.svg"), "icomoon.svg");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Flow flow = new Flow(MainController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);
        flow.createHandler(flowContext).start(container);

        Scene scene = new Scene(new JFXDecorator(stage, container.getView()), 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/jfoenix-fonts.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/jfoenix-design.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/jfoenix-main-demo.css").toExternalForm());
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();

    }

    public User getLoggedUser()
    {
        try {
            return  userManager.getAllUsers().get(0);
        } catch (SoftClickerException e) {
            log.error("Logged User not found",e);
            return null;
        }
    }

    public AnswerManager getAnswerManager() {
        return answerManager;
    }

    public void setAnswerManager(AnswerManager answerManager) {
        this.answerManager = answerManager;
    }

    public QuestionManager getQuestionManager() {
        return questionManager;
    }

    public void setQuestionManager(QuestionManager questionManager) {
        this.questionManager = questionManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public ClazzManager getClazzManager() {
        return clazzManager;
    }

    public void setClazzManager(ClazzManager clazzManager) {
        this.clazzManager = clazzManager;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
