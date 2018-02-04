package br.com.cucha.myself2.data;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by eduardocucharro on 04/02/18.
 */

public class DateConverter {
    @TypeConverter
    public static Date fromLong(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }
}
