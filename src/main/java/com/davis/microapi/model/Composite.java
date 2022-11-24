package com.davis.microapi.model;

import java.util.List;

import com.davis.microapi.model.Progress;

public class Composite {
    private Goal goal;
    private List<Progress> progress; 


    public Composite(Goal goal, List<Progress> progress) {
        this.goal = goal;
        this.progress = progress;
    }


    public Goal getGoal() {
        return this.goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public List<Progress> getProgress() {
        return this.progress;
    }

    public void setProgress(List<Progress> progress) {
        this.progress = progress;
    }


}
