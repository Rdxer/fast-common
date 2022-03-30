package com.rdxer.common.utils.regular;

import com.rdxer.common.ex.ObjectEx;
import com.rdxer.common.ex.StringEx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtils {
    /**
     *          String str = "续签|@{approval.context.objId}| |@{approval.context.v}|";
     *
     *         String pattern = "@\\{(.*?)}";
     *
     *         str = RegularUtils.replace(str, pattern, matchItem -> {
     *             return matchItem.getContent();
     *         });
     *
     *         System.out.println(str);
     *
     * @param str
     * @param pattern
     * @param mapTo
     * @return
     */
    public static String replace(String str, String pattern, ObjectEx.MapTo<MatchItem,String> mapTo) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        List<MatchItem> items = new ArrayList<>();

        while (m.find()) {
            if (m.groupCount() > 0) {
                items.add(MatchItem.builder()
                        .allcontent(m.group())
                        .content(m.group(1))
                        .start(m.start())
                        .end(m.end())
                        .build());

            }
        }

        Collections.reverse(items);


        for (MatchItem item : items) {
            str = StringEx.replace(str,item.getStart(),item.getEnd(), mapTo.r(item));
        }
        return str;
    }
}
