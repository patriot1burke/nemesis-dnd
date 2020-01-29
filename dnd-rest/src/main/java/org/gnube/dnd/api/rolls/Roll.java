package org.gnube.dnd.api.rolls;

import org.gnube.dnd.api.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public interface Roll {
    public static final Random random = new Random();

    public class Result {
        int value;
        String description;
        List<Result> subResults = new ArrayList<>();

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Result> getSubResults() {
            return subResults;
        }

        public void setSubResults(List<Result> subResults) {
            this.subResults = subResults;
        }
    }

    public static final Comparator<Result> ROLL_RESULT_COMPARATOR = new Comparator<Result>() {
        @Override
        public int compare(Result result, Result t1) {
            return t1.value - result.value;
        }
    };



    Result roll(RollerContext roller);
}
