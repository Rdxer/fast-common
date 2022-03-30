package com.rdxer.common.utils.regular;

import com.rdxer.common.model.Range;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchItem {
    String allcontent;
    int start;
    int end;


    String content;

    public Range makeRange(){
        return Range.builder()
                .start(start)
                .end(end)
                .build();
    }
}
