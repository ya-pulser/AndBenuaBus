package ru.fluffypulser.benuabus;

import android.widget.ListAdapter;

/**
 * Author: Yury Chuyko mrgrey@yandex-team.ru
 * Date: 07.09.11
 */
public interface DataProvider {
    String[] getData();
    ListAdapter getAdapter();
}
