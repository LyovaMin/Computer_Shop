package by.lyofchik.AppSpring.Model.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CommonResponse<T> {

    private String errorCode;
    private String errorDescription;

    private T data;

}
