package by.lyofchik.AppSpring.Controller.Rest;

import by.lyofchik.AppSpring.CustomException.CustomException;
import by.lyofchik.AppSpring.Model.DTO.CommonResponse;
import by.lyofchik.AppSpring.Model.DTO.EmailRequest;
import by.lyofchik.AppSpring.Model.DTO.EmailResponseDto;
import by.lyofchik.AppSpring.Service.MailService.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class EmailSenderRestController {

    private final MailService mailService;

    @GetMapping
    public void sendEmail(@RequestParam String email, @RequestParam String message) {
        mailService.send(email, message);
    }
}

//    @PostMapping
//    public CommonResponse<EmailResponseDto> send(@RequestBody EmailRequest email) {
////        try {
////            mailService.sendEmail(email)
////                    .map(emailResponseDto -> CommonResponse.builder()
////                            .data(emailResponseDto)
////                            .errorCode("0")
////                            .errorDescription("Успешно"))
////                    .orElseThrow(() -> new CustomException("нЕ УДАЛОСЬ ОТПРАВИТЬ СООЩЕНИЕ"));
////
////
////            mailService.sendEmail(email)
////                    .map(emailResponseDto -> CommonResponse.builder()
////                            .data(emailResponseDto)
////                            .errorCode("0")
////                            .errorDescription("Успешно"))
////                    .orElseThrow(() -> new CustomException("нЕ УДАЛОСЬ ОТПРАВИТЬ СООЩЕНИЕ"));
////
////
////            Arrays.asList(1,2,3,4,2).stream().filter(t->t.equals(2)).map()
////            mailService.sendEmail(email)
////                    .map(emailResponseDto -> CommonResponse.builder()
////                            .data(emailResponseDto)
////                            .errorCode("0")
////                            .errorDescription("Успешно"))
////                    .orElseThrow(() -> new CustomException("нЕ УДАЛОСЬ ОТПРАВИТЬ СООЩЕНИЕ"));
////
////            sdgdfgdfg
////        } catch (CustomException e) {
////            throw new RuntimeException(e);
////        }catch (E)}
//
//}
