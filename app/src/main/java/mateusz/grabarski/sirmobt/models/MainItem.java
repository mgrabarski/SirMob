package mateusz.grabarski.sirmobt.models;

/**
 * Created by MGrabarski on 30.11.2017.
 */

public class MainItem {

    private String text;
    private int drawable;
    private boolean progress;

    public MainItem(String text, int drawable, boolean progress) {
        this.text = text;
        this.drawable = drawable;
        this.progress = progress;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }
}
