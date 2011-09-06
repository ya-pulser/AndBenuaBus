package ru.fluffypulser.benuabus;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

/**
 * Author: Yury Chuyko mrgrey@yandex-team.ru
 * Date: 07.09.11
 */
public class StaticDataProvider implements DataProvider {

    public String[] data;
    public ArrayAdapter<String> adapter;

    public StaticDataProvider(final String[] data, final ArrayAdapter<String> adapter) {
        this.adapter = adapter;
        this.data = data;
    }


    public String[] getData() {
        return data;
    }

    public ListAdapter getAdapter() {
        return adapter;
    }
}
