package com.fffire.pagereplacementalgorithmsimulator;

import android.app.Application;

/**
 * Created by flicker on 16-1-4.
 */
public class ApplicationHelper extends Application {
    private String opt;
    private String fifo;
    private String lru;
    private String clock;

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getFifo() {
        return fifo;
    }

    public void setFifo(String fifo) {
        this.fifo = fifo;
    }

    public String getLru() {
        return lru;
    }

    public void setLru(String lru) {
        this.lru = lru;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }
}
