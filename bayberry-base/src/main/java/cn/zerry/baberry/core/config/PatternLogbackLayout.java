package cn.zerry.baberry.core.config;

import ch.qos.logback.classic.PatternLayout;
import cn.zerry.baberry.core.constant.TrackConsts;
import cn.zerry.baberry.core.utils.TrackConverter;

public class PatternLogbackLayout extends PatternLayout {
	static {
		defaultConverterMap.put(TrackConsts.TRACK_KEY, TrackConverter.class.getName());
	}
}