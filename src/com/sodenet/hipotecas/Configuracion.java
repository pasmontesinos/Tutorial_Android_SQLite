package com.sodenet.hipotecas;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Configuracion extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.configuracion);
    }
}