package com.origin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Barlist6 implements Serializable {
    private List<Double> rate1;
    private List<Double> rate2;
    private List<Double> rate3;
    private List<Double> rate4;
    private List<Double> rate5;
    private List<Double> rate6;
}
