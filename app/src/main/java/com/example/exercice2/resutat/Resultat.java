package com.example.exercice2.resutat;

public class Resultat {

    String module;
    Float Note;

    public Resultat() {
    }

    public Resultat(String module, Float note) {
        this.module = module;
        Note = note;
    }

    public String getModule() {
        return module;
    }

    public Float getNote() {
        return Note;
    }

    @Override
    public String toString() {
        return "Resultat{" +
                "module='" + module + '\'' +
                ", Note=" + Note +
                '}';
    }
}
