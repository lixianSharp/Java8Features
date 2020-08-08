package org.lixianyuan.lambda;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author lxy
 * @Date 2020/7/28
 * @Descript
 **/
public class NewMan {
    private Optional<Godness> godness = Optional.empty();

    public NewMan() {

    }

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }

    public Optional<Godness> getGodness() {
        return godness;
    }

    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "godness=" + godness +
                '}';
    }
}
