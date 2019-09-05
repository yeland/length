package com.practice.length;

public class Length {
    private double number;
    private String unit;

    public Length(double number, String unit) {
        this.number = number;
        this.unit = unit;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Length convertToMm() {
        double mmNumber;
        if (this.unit.equals("mm")) {
            mmNumber = this.number;
        } else if (this.unit.equals("cm")) {
            mmNumber = this.number * 10;
        } else {
            mmNumber = this.number * 1000;
        }
        return new Length(mmNumber, "mm");
    }

    public Length convertToCm() {
        double cmNumber;
        if (this.unit.equals("cm")) {
            cmNumber = this.number;
        }else if (this.unit.equals("mm")) {
            cmNumber = this.number / 10;
        } else {
            cmNumber = this.number * 100;
        }
        return new Length(cmNumber, "cm");
    }

    public Length convertToM() {
        double mNumber;
        if (this.unit.equals("mm")) {
            mNumber = this.number / 1000;
        } else if (this.unit.equals("cm")) {
            mNumber = this.number / 100;
        } else {
            mNumber = this.number;
        }
        return new Length(mNumber, "m");
    }

    @Override
    public String toString() {
        if (this.unit.equals("mm")) {
            return (int) this.number + this.unit;
        }
        return this.number + this.unit;
    }
}
