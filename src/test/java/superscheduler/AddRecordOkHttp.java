package superscheduler;

import dtosuper.DateDto;
import dtosuper.RecordDto;
import org.testng.annotations.Test;

public class AddRecordOkHttp {

    @Test
    public void addRecordSuccess(){
        RecordDto recordDto = RecordDto.builder()
                .breaks(2)
                .currency("Currency")
                .date(DateDto.builder().dayOfMonth(1).dayOfWeek("2").month(1).year(2020).build())
                .hours(4)
                .timeFrom("18:00")
                .timeTo("21:00")
                .title("Title")
                .type("type")
                .totalSalary(500)
                .wage(50)
                .build();

    }
}
