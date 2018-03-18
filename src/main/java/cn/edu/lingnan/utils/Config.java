package cn.edu.lingnan.utils;

import cn.edu.lingnan.pojo.Vocab;
import cn.edu.lingnan.sdk.Container.PhaseContainer;
import cn.edu.lingnan.sdk.Container.PhaseContainerImpl;
import cn.edu.lingnan.sdk.algorithms.ahoCorasick.AhoCorasick;
import cn.edu.lingnan.sdk.algorithms.ahoCorasick.AhoCorasickImpl;
import cn.edu.lingnan.sdk.overlay.AudioPlayer;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.IndexRange;
import javafx.util.Pair;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayer;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayerException;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 * 本地工程配置文件
 * 负责桌面环境的一些选择配置
 *
 */
public class Config implements Serializable{

    private static Config config = null;
    private AhoCorasick ahoCorasick = new AhoCorasickImpl();
    //文本区域字数限制
    private int restrictLength = 30;

    //音频文件类
    private File audio = null;
    //流媒体播放类
    private AudioPlayer audioPlayer = new AudioPlayer();
    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    //获取ac自动机实例
    public AhoCorasick getAhoCorasick() {
        return ahoCorasick;
    }

    public int getRestrictLength() {
        return restrictLength;
    }
    public void setRestrictLength(int restrictLength) {
        this.restrictLength = restrictLength;
    }

    public void setAudio(File audio) {
        this.audio = audio;
    }

    //每次匹配到的单词
    private List<String> words = new ArrayList<>();
    public List<String> getWords() {
        return words;
    }
    // 受：
    private ObservableList<Pair<Integer, IndexRange>> answers = FXCollections.observableArrayList();
    public ObservableList<Pair<Integer, IndexRange>> getAnswers() {
        return answers;
    }
    // 访：
    private ObservableList<Pair<Integer, IndexRange>> asks = FXCollections.observableArrayList();
    public ObservableList<Pair<Integer, IndexRange>> getAsks() {
        return asks;
    }
    //用于基调分析的人生四大阶段: 小学、初中、大学、工作。
    PhaseContainer<Integer> phaseContainer = new PhaseContainerImpl();
    public PhaseContainer<Integer> getPhaseContainer() {
        return phaseContainer;
    }

    //文本字符串
    private StringProperty textProperty = new SimpleStringProperty();
    public String getTextProperty() {
        return textProperty.get();
    }
    public StringProperty textPropertyProperty() {
        return textProperty;
    }

    //展示某段落，用于导航区域的点击指向相应的段落。(同时用于行号)
    private IntegerProperty showParagraph = new SimpleIntegerProperty();
    public IntegerProperty showParagraphProperty() {
        return showParagraph;
    }

    //当前列号属性
    private IntegerProperty currentColumn = new SimpleIntegerProperty();
    public IntegerProperty currentColumnProperty() {
        return currentColumn;
    }

    //当前段落属性
    private IntegerProperty currentParagraph = new SimpleIntegerProperty();
    public IntegerProperty currentParagraphProperty() {
        return currentParagraph;
    }

    //是否标记信息词汇属性
    private BooleanProperty markVocabs = new SimpleBooleanProperty(true);
    public BooleanProperty markVocabsProperty() {
        return markVocabs;
    }

    /**
     * 为桌面环境配置初始化
     * 获取当前项目名称
     * 一个config对象
     */
    private Config(){}
    static Config getInstance(){
        if (config == null){
            String localProjectName = (String) PreferencesUtils.getParametersAsString("localProject");
            String basePath = PreferencesUtils.getParametersAsString("basePath");
            String path = basePath + "/" + localProjectName;
            config = SerializableUtils.getLastState(Config.class, path);
            //当再次为空时表明此时尚未创建工程
            if (config == null)
                config = new Config();
        }
        return config;
    }
}
