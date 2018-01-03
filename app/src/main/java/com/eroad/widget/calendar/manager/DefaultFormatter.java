package com.eroad.widget.calendar.manager;

import com.eroad.widget.calendar.manager.CalendarUnit.CalendarType;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 默认日期格式化
 * @author MaJian
 *
 */
public class DefaultFormatter implements Formatter {

	private final DateTimeFormatter dayFormatter;
	@SuppressWarnings("unused")
	private final DateTimeFormatter weekHeaderFormatter;
	private final DateTimeFormatter monthHeaderFormatter;

	public DefaultFormatter() {
		this("E", "'第' w '周'", "yyyy '年' M '月'");
	}

	public DefaultFormatter( String dayPattern,  String weekPattern,  String monthPattern) {
		dayFormatter = DateTimeFormat.forPattern(dayPattern);
		weekHeaderFormatter = DateTimeFormat.forPattern(weekPattern);
		monthHeaderFormatter = DateTimeFormat.forPattern(monthPattern);
	}

	@Override public String getDayName( LocalDate date) {
		return date.toString(dayFormatter);
	}

	@Override public String getHeaderText(@CalendarType int type,  LocalDate from,  LocalDate to , LocalDate selected) {
		if (from.isBefore(selected) && to.isAfter(selected) || from.isEqual(selected) || to.isEqual(selected)) {
			//如果选中日期在开始日期和结束日期之间，header返回选中日期月份
			return selected.toString(monthHeaderFormatter);
		}
		switch (type) {
		case CalendarUnit.TYPE_WEEK:
			//周视图默认显示结束日期月份
			return to.toString(monthHeaderFormatter);
		case CalendarUnit.TYPE_MONTH:
			//月视图默认显示开始日期月份
			return from.toString(monthHeaderFormatter);
		default:
			throw new IllegalStateException("Unknown calendar type");
		}
	}
}
